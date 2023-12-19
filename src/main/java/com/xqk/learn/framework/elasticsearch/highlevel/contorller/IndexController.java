package com.xqk.learn.framework.elasticsearch.highlevel.contorller;

import com.xqk.learn.framework.common.ResponseMessage;
import com.xqk.learn.framework.elasticsearch.highlevel.dto.Employee;
import com.xqk.learn.framework.elasticsearch.highlevel.operation.IndexService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author 熊乾坤
 * @since 2020-06-28 20:42
 */
@RestController
@RequestMapping("es/index")
public class IndexController {
    private final IndexService indexService;

    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @PostMapping
    public ResponseMessage indexSource(@RequestBody Employee employee) throws IOException {
        return indexService.indexEmployee(employee);
    }

    @PostMapping("/async")
    public ResponseMessage indexSourceAsync(@RequestBody Employee employee) throws IOException {
        return indexService.indexAsyncEmployee(employee);
    }
}
