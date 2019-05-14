package org.github.caishijun.springcloud.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * HttpClient工具类
 */
public class HttpClientUtils {


    /**
     * 发送GET请求
     *
     * @param url 请求URL
     * @return 响应结果
     */
    public static String sendGetRequest(String url) {
        return sendGetRequest(url, null);
    }

    /**
     * 发送GET请求
     *
     * @param url     请求URL
     * @param headers 请求头
     * @return 响应结果
     */
    public static String sendGetRequest(String url, Map<String, String> headers) {
        String httpResponse = null;

        HttpClient httpclient = null;
        InputStream is = null;
        BufferedReader br = null;

        try {
            // 发送GET请求
            httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url);
            setHeader(httpget, headers);
            HttpResponse response = httpclient.execute(httpget);

            // 处理响应
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                is = entity.getContent();
                br = new BufferedReader(new InputStreamReader(is));

                StringBuffer buffer = new StringBuffer("");
                String line = null;

                while ((line = br.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                httpResponse = buffer.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return httpResponse;
    }


    /**
     * 发送post请求
     *
     * @param url URL
     * @return
     */
    public static String sendPostRequest(String url) {
        return sendPostRequest(url, null, null);
    }

    /**
     * 发送post请求
     *
     * @param url    URL
     * @param params 参数Map
     * @return
     */
    public static String sendPostRequest(String url, Map<String, String> params) {
        return sendPostRequest(url, params, null);
    }

    /**
     * 发送post请求
     *
     * @param url     URL
     * @param params  参数Map
     * @param headers 请求头
     * @return
     */
    public static String sendPostRequest(String url, Map<String, String> params, Map<String, String> headers) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;

        try {
            httpClient = new DefaultHttpClient();
            httpPost = new HttpPost(url);
            setHeader(httpPost, headers);

            //设置参数
            if (params != null) {
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                Iterator iterator = params.entrySet().iterator();
                while (iterator.hasNext()) {
                    Entry<String, String> elem = (Entry<String, String>) iterator.next();
                    list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
                }
                if (list.size() > 0) {
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "utf-8");
                    httpPost.setEntity(entity);
                }
            }

            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "utf-8");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }

        return result;
    }

    public static void setHeader(HttpMessage httpMessage, Map<String, String> headers) {
        if (headers != null) {
            Set<String> keys = headers.keySet();
            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                httpMessage.setHeader(key, headers.get(key));
            }
        }
    }

}