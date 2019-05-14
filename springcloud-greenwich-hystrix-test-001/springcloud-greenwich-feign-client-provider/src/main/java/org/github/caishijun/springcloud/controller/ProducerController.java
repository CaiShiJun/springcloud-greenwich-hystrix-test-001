package org.github.caishijun.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @RequestMapping("/hello")
    public String hello(@RequestParam String name) throws Exception{

        Thread.sleep(300);

        return "hello "+name+"，this is new world";
    }

}
