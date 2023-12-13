package com.xqk.learn.framework.elasticsearch.highlevel.operation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xqk.learn.framework.common.ResponseMessage;
import com.xqk.learn.framework.elasticsearch.highlevel.dto.Employee;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author 熊乾坤
 * @since 2020-07-04 23:15
 */
@Slf4j
@Service
public class GetService {
    private final RestHighLevelClient client;
    private final ObjectMapper objectMapper;

    public GetService(RestHighLevelClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public ResponseMessage getEmployeeById(String id, Long version) throws IOException {
        GetRequest getRequest = new GetRequest("megecrop111", "employee", id);
        //指定version
        if (version != null) {
            getRequest.version(version);
        }

        GetResponse getResponse;
        try {
            getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            log.error("ES发生异常", e);
            //如果指定了Version并且有冲突，则抛出异常，状态为409
            if (e.status() == RestStatus.CONFLICT) {
                log.error("version：{}冲突", version);
                return ResponseMessage.error("版本号冲突");
            }
            return ResponseMessage.error(e.status().toString());
        }


        if (getResponse.isExists()) {
            //如果文档存在，直接序列化为对象
            return ResponseMessage.ok(objectMapper.readValue(getResponse.getSourceAsString(), Employee.class));
        } else {
            //如果想要获取的文档不存在，不会抛出异常，而是返回一个GetResponse,其isExists方法为false,source字段为空。
            return ResponseMessage.error("文档不存在");
        }
    }

    public ResponseMessage getNotExistsIndexById(String id) throws IOException {
        //特意写一个不存在的索引
        GetRequest getRequest = new GetRequest("not_exists_index", "employee", id);

        try {
            GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
            return ResponseMessage.ok(objectMapper.readValue(getResponse.getSourceAsString(), Employee.class));
        } catch (ElasticsearchException e) {
            log.error("ES发生异常", e);
            //如果索引不存在，则会抛出异常，状态为404
            if (e.status() == RestStatus.NOT_FOUND) {
                log.error("索引：{}不存在", e.getIndex());
                return ResponseMessage.error("索引不存在");
            }
            return ResponseMessage.error(e.status().toString());
        }
    }
}
