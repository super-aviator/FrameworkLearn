package com.xqk.lean.framework.springboot.mvc.controller;

import com.xqk.lean.framework.springboot.common.ResponseMessage;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 表单校验测试
 *
 * @author 熊乾坤
 * @since 2019-10-28 17:56
 */
@RestController
@RequestMapping("/formValid")
public class FormValidController {
    @GetMapping("/test1")
    public ResponseMessage validTest1(@Validated FormValidVO formValidVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseMessage.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResponseMessage.ok("表单校验通过");
    }

    @GetMapping("/test2")
    public ResponseMessage validTest2(@Validated(GroupValidVO.TEST1.class) GroupValidVO groupValidVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseMessage.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResponseMessage.ok("表单校验通过");
    }

    @GetMapping("/test3")
    public ResponseMessage validTest3(@Validated({GroupValidVO.TEST2.class}) GroupValidVO groupValidVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseMessage.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResponseMessage.ok("表单校验通过");
    }

    @Data
    private static class FormValidVO {
        @NotNull
        private String notNull;

        @NotBlank
        private String notBlank;

        @NotEmpty
        private Integer[] notEmpty;

        @Size(min = 2, max = 5)
        private String sizeString;

        @Size(min = 2, max = 5)
        private List<String> sizeCollection;

        @Size(min = 2, max = 5)
        private String[] sizeArray;

        /**
         * 矩阵参数的请求方式：http://localhost:8080/hello;a=1;b=2;c=3
         */
        //@Size(min = 2,max = 5)
        //private Map<String,String> sizeMap;
    }

    @Data
    private static class GroupValidVO {
        @NotBlank(groups = TEST1.class, message = "name不能为空")
        private String name;

        @Max(value = 100, groups = TEST2.class, message = "年龄不能大于100岁")
        private Long age;

        @Size(min = 2, groups = TEST2.class, message = "朋友id不能为空")
        private List<Long> friendIds;

        private interface TEST1 {
        }

        private interface TEST2 {
        }
    }
}
