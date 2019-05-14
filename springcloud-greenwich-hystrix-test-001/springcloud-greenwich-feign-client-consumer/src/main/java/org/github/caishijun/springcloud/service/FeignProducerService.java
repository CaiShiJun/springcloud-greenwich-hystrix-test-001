package org.github.caishijun.springcloud.service;

import org.github.caishijun.springcloud.http.HttpClientUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-producer",fallback = FeignProducerFallBack.class)
public interface FeignProducerService {

    @GetMapping("/helloFeignProducer")
    String helloFeignProducer(@RequestParam(value = "name") String name);

    @GetMapping("/helloFeignProducerFallBack")
    String helloFeignProducerFallBack(@RequestParam(value = "name") String name);
}

@Component
class FeignProducerFallBack implements FeignProducerService {

    @Override
    public String helloFeignProducer(String name) {
        return "FeignProducerService.FeignProducerFallBack.helloFeignProducer() :" + name;
    }

    @Override
    public String helloFeignProducerFallBack(String name) {
        HttpClientUtils.sendGetRequest("https://www.qq.com/");
        return "FeignProducerService.FeignProducerFallBack.helloFeignProducerFallBack() :" + name;
    }
}