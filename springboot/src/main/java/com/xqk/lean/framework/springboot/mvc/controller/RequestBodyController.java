package com.xqk.lean.framework.springboot.mvc.controller;// package com.xqk.learn.framework.springboot.mvc.controller;
//
// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.http.HttpEntity;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;
//
// /**
//  * 测试@RequestBody注解
//  * <p>
//  * - @RequestBody用于需要触发HttpMessageConverter的场景：
//  * 1. 当表单提交的请求的Content-Type头部为application/json时，需要加上@RequestBody注解，并使用默认的`HttpMessageConverter`
//  * 或者自定义的`HttpMessageConverter`对请求的body中的json字符串转换为java对象。
//  * 2. 当Content-Type头部的值为application/x-www-form-urlencoded或者multipart/form-data时，表名此请求是一个常规的表单请求，
//  * 不能使用@RequestBody注解。
//  *
//  * @author 熊乾坤
//  * @since 2019-9-2
//  */
// @RestController
// @RequestMapping("/requestBody")
// @Slf4j
// public class RequestBodyController {
//     @NoArgsConstructor
//     @AllArgsConstructor
//     @Getter
//     @Setter
//     private static class Request {
//         private String name;
//         private Integer age;
//         @JSONField(serialize = false, deserialize = false)
//         private MultipartFile avatar;
//     }
//
//     @PostMapping("/postRequest")
//     public HttpEntity<Request> generalRequest(Request request) {
//         log.info("generalRequest-{}", JSON.toJSONString(request));
//         return new HttpEntity<>(request);
//     }
//
//     @PostMapping("/postRequestBody")
//     public HttpEntity<Request> generalRequestBody(@RequestBody Request request) {
//         log.info("generalRequest-{}", JSON.toJSONString(request));
//         return new HttpEntity<>(request);
//     }
//
//     @GetMapping("/getRequest")
//     public HttpEntity<Request> generalGetRequest(Request request) {
//         log.info("generalRequest-{}", JSON.toJSONString(request));
//         return new HttpEntity<>(request);
//     }
//
//     @GetMapping("/getRequestBody")
//     public HttpEntity<Request> generalGetRequestBody(@RequestBody Request request) {
//         log.info("generalRequest-{}", JSON.toJSONString(request));
//         return new HttpEntity<>(request);
//     }
// }