package org.github.caishijun.springcloud.controller;

import org.github.caishijun.springcloud.service.FeignProducerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    @Resource
    private FeignProducerService feignProducerService;

    @GetMapping("/feignProducerHystrixFallbackService/{name}")
    public String feignProducerHystrixFallbackService(@PathVariable("name") String name) throws Exception {
        return feignProducerService.helloFeignProducerFallBack(name);
    }

}