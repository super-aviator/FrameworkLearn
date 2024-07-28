package com.xqk.learn.framework.elasticsearch.highlevel.operation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xqk.learn.framework.common.ResponseMessage;
import com.xqk.learn.framework.elasticsearch.highlevel.dto.Employee;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicatedWriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 熊乾坤
 * @since 2020-06-28 20:28
 */
@Slf4j
@Component
@Profile("elasticsearch")
public class IndexService {
    private final RestHighLevelClient client;
    private final ObjectMapper objectMapper;

    public IndexService(RestHighLevelClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public ResponseMessage<String> indexEmployee(Employee employee) throws IOException {
        IndexRequest request = new IndexRequest("megecrop2222", "employee", employee.getId());
        //需要手动指定ContentType
        request.source(objectMapper.writeValueAsString(employee), XContentType.JSON);
        //设置Request参数
        customRequest(request);
        //指定version
        request.version(3);
        //指定操作类型
        request.opType(DocWriteRequest.OpType.INDEX);

        //执行索引操作
        try {
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            return ResponseMessage.ok("操作结果：" + response.getResult().toString());
        } catch (ElasticsearchException e) {
            log.error("文档索引异常", e);
            if (e.status() == RestStatus.CONFLICT) {
                log.error("保存数据时冲突");
            }
            return ResponseMessage.error(e.status().toString());
        }
    }

    public ResponseMessage<String> indexAsyncEmployee(Employee employee) throws IOException {
        IndexRequest request = new IndexRequest("megecrop", "employee", employee.getId());
        //需要手动指定ContentType
        request.source(objectMapper.writeValueAsString(employee), XContentType.JSON);
        //指定version
        //request.version(3);
        //指定操作类型
        request.opType(DocWriteRequest.OpType.INDEX);
        //设置Request参数
        customRequest(request);

        //异步执行操作
        client.indexAsync(request, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                log.info("id为{}的数据插入成功 !", employee.getId());
            }

            @Override
            public void onFailure(Exception e) {
                throw new RuntimeException(e);
            }
        });
        return ResponseMessage.ok("执行成功");
    }

    /**
     * 对es请求进行定制化
     *
     * @param request request
     */
    private void customRequest(ReplicatedWriteRequest request) {
        //设置超时时间，默认一分钟
        request.timeout("1s");
        request.timeout(TimeValue.timeValueSeconds(1));
    }
}
