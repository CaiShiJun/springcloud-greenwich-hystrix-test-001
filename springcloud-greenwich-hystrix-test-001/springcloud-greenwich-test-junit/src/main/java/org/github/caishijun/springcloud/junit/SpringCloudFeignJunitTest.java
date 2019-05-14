package org.github.caishijun.springcloud.junit;

import org.github.caishijun.springcloud.http.HttpClientUtils;
import org.junit.Test;

import java.util.HashMap;

public class SpringCloudFeignJunitTest {

    private static String HOST = "localhost";
    private static int PORT = -1;

    private static int FOR_TIMES = 1;
    private static int SLEEP_TIME = 0;

    public static String getUrl(String uri, String host, int port) {
        return "http://" + host + ":" + port + uri;
    }

    /**
     * 1、Feign 的 Hystrix
     */
    @Test
    public void feignProducerHystrixFallbackService() throws Exception {
        PORT = 8080;

        for (int i = 0; i < FOR_TIMES; i++) {
            String result = HttpClientUtils.sendGetRequest(getUrl("/feignProducerHystrixFallbackService/XXXXXX-NAME-XXXXX", HOST, PORT));
            System.out.println("result ： " + result);
            Thread.sleep(SLEEP_TIME);
        }
    }

    /**
     * 2、Zuul 的 Hystrix
     */
    @Test
    public void zuulFallback() throws Exception {
        PORT = 8105;

        for (int i = 0; i < FOR_TIMES; i++) {
            String result = HttpClientUtils.sendGetRequest(getUrl("/fallback/XXXXXX-NAME-XXXXX", HOST, PORT));
            System.out.println("result ： " + result);
            Thread.sleep(SLEEP_TIME);
        }
    }

    /**
     * 3、Gateway 的 Hystrix
     */
    @Test
    public void gatewayFallback() throws Exception {
        PORT = 8104;

        for (int i = 0; i < FOR_TIMES; i++) {
            String result = HttpClientUtils.sendGetRequest(getUrl("/fallback/XXXXXX-NAME-XXXXX", HOST, PORT));
            System.out.println("result ： " + result);
            Thread.sleep(SLEEP_TIME);
        }
    }
}

