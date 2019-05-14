package org.github.caishijun.springcloud.fallback;

import org.github.caishijun.springcloud.http.HttpClientUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public String fallback() {
        HttpClientUtils.sendGetRequest("https://www.qq.com/");
        return "Hello World!\nfrom gateway";
    }

}