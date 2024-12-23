package com.xqk.lean.framework.springboot.elasticsearch.highlevel.contorller;

import com.xqk.lean.framework.springboot.common.ResponseMessage;
import com.xqk.lean.framework.springboot.elasticsearch.highlevel.dto.Employee;
import com.xqk.lean.framework.springboot.elasticsearch.highlevel.operation.IndexService;
import org.springframework.context.annotation.Profile;
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
@Profile("elasticsearch")
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
