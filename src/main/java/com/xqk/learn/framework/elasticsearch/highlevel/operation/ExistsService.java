package com.xqk.learn.framework.elasticsearch.highlevel.operation;

import com.xqk.learn.framework.common.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author 熊乾坤
 * @since 2020-07-04 23:15
 */
@Slf4j
@Service
public class ExistsService {
    private final RestHighLevelClient client;

    public ExistsService(RestHighLevelClient client) {
        this.client = client;
    }

    /**
     * 判断id对应的文档是否存在
     *
     * @param id id
     * @return ResponseMessage
     * @throws IOException
     */
    public ResponseMessage documentExists(String id) throws IOException {
        GetRequest getRequest = new GetRequest("megecrop", "employee", id);

        //Disable fetching _source.
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        //Disable fetching stored fields.
        getRequest.storedFields("_none_");
        return ResponseMessage.ok(client.exists(getRequest, RequestOptions.DEFAULT));
    }
}
