package com.xqk.learn.framework.elasticsearch.highlevel.contorller;

import com.xqk.learn.framework.common.ResponseMessage;
import com.xqk.learn.framework.elasticsearch.highlevel.operation.ExistsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author 熊乾坤
 * @since 2020-07-04 23:36
 */
@RestController
@RequestMapping("es/exists")
public class ExistsController {
    private final ExistsService existsService;

    public ExistsController(ExistsService existsService) {
        this.existsService = existsService;
    }

    @GetMapping("/{id}")
    public ResponseMessage getById(@PathVariable("id") String id) throws IOException {
        return existsService.documentExists(id);
    }
}
