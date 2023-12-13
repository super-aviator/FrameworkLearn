package com.xqk.learn.framework.elasticsearch.lowlevel.service;/**
 * @author 熊乾坤
 * @since 2020-09-12 18:03
 */

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.*;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.elasticsearch.ElasticsearchException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

@Slf4j
@Component
public class ElasticsearchSearchService {
    private final RestClient restClient;
    private final RestHighLevelClient highLevelClient;

    public ElasticsearchSearchService(RestClient restClient, RestHighLevelClient highLevelClient) {
        this.restClient = restClient;
        this.highLevelClient = highLevelClient;
    }

    /**
     * 通过GET方法返回的状态码判断索引是否存在
     *
     * @param index 索引名称
     * @return true-索引存在或者索引名称不正确，false-索引不存在
     * @throws IOException 网络IO异常
     */
    public boolean indexExists(String index) throws IOException {
        if (StringUtils.isEmpty(index)) {
            log.warn("非法的索引名称[{}]", index);
            return true;
        }
        try {
            Request request = new Request(HttpMethod.GET.name(), "/" + index);
            Response response = restClient.performRequest(request);
            return response.getStatusLine().getStatusCode() != HttpStatus.NOT_FOUND.value();
        } catch (ResponseException responseException) {
            return responseException.getResponse().getStatusLine().getStatusCode() != HttpStatus.NOT_FOUND.value();
        }
    }

    /**
     * 手动创建索引以及其映射
     *
     * @param index   索引名称
     * @param mapping 索引映射Map
     * @throws IOException 网络IO异常
     */
    public void createIndex(String index, JSONObject mapping) throws IOException {
        if (StringUtils.isEmpty(index) || CollectionUtils.isEmpty(mapping)) {
            log.warn("非法的索引或映射，将忽略该索引映射的创建！");
            return;
        }
        if (indexExists(index)) {
            log.warn("索引[{}]已存在，请确认是否需要手动删除索引后再启动项目", index);
        }
        Request request = new Request(HttpMethod.PUT.name(), "/" + index);
        request.setJsonEntity(mapping.toJSONString());
        try {
            Response response = restClient.performRequest(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                log.info("索引[{}]创建成功", index);
            } else {
                log.error("索引[{}]创建失败", index);
            }
        } catch (Exception e) {
            log.warn("创建索引异常", e);
        }
    }

    /**
     * 手动创建索引
     *
     * @param index           索引名称
     * @param mappingFilePath 索引映射地址(classpath相对路径)
     */
    public void createIndex(String index, String mappingFilePath) {
        if (StringUtils.isEmpty(mappingFilePath)) {
            log.error("非法的映射地址[{}]", mappingFilePath);
            return;
        }
        Resource mappingResource = new ClassPathResource(mappingFilePath);
        if (!mappingResource.exists()) {
            log.error("映射文件地址[{}]不存在！", mappingFilePath);
            return;
        }
        try (InputStream is = mappingResource.getInputStream()) {
            createIndex(index, JSONObject.parseObject(IoUtil.read(new InputStreamReader(is))));
        } catch (IOException e) {
            log.error("映射文件读取异常", e);
        }
    }

    /**
     * 索引一个文档
     *
     * @param index    索引名称
     * @param id       文档id
     * @param document 文档内容
     */
    public void indexDocument(String index, String id, Map<String, Object> document) {
        IndexRequest indexRequest = new IndexRequest(index, "doc");
        indexRequest.id(id);
        indexRequest.source(document, XContentType.JSON);
        try {
            highLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            log.error("文档索引失败", e);
        } catch (IOException e) {
            log.error("文档索引异常", e);
        }
    }
}
