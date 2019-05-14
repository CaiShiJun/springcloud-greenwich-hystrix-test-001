package org.github.caishijun.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.github.caishijun.springcloud.http.HttpClientUtils;
import org.github.caishijun.springcloud.service.FeignProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    @Resource
    private FeignProducerService feignProducerService;

    @GetMapping("/feignProducerHystrixFallbackService/{name}")
    public String feignProducerHystrixFallbackService(@PathVariable("name") String name) throws Exception {
        return feignProducerService.helloFeignProducerFallBack(name);
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/restTemplateFallback/{name}")
    @HystrixCommand(fallbackMethod = "fallbackCustomMessage")
    public String restTemplateFallback(@PathVariable("name") String name) {
        return this.restTemplate.getForObject("http://错误的地址/hello?name=" + name, String.class);
    }

    public String fallbackCustomMessage(String name) {
        HttpClientUtils.sendGetRequest("https://www.qq.com/");
        return "fallbackCustomMessage";
    }
}