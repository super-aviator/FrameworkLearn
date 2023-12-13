package com.xqk.learn.framework.elasticsearch.highlevel.contorller;

import com.xqk.learn.framework.common.ResponseMessage;
import com.xqk.learn.framework.elasticsearch.highlevel.operation.GetService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author 熊乾坤
 * @since 2020-07-04 23:36
 */
@RestController
@RequestMapping("es/get")
public class GetController {
    private final GetService getService;

    public GetController(GetService getService) {
        this.getService = getService;
    }

    @GetMapping("/{id}")
    public ResponseMessage getById(@PathVariable("id") String id, @RequestParam(value = "version", required = false) Long version) throws IOException {
        return getService.getEmployeeById(id, version);
    }

    @GetMapping("/notExistsIndex/{id}")
    public ResponseMessage getNotExistsIndex(@PathVariable("id") String id) throws IOException {
        return getService.getNotExistsIndexById(id);
    }
}
