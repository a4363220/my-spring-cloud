package com.spring.cloud.zk.loadbalance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 描述：
 * 在controller写负载均衡以及更新太过复杂 每个地方都要写
 * 所以选择在restTemplate中的拦截器实现
 *
 * @author 小谷
 * @Date 2020/3/21 15:28
 */
public class LoadBalanceRequestInterceptors implements ClientHttpRequestInterceptor {

    //Map Key service Name，Value Urls
    private volatile Map<String, Set<String>> targetUrlsCache = new HashMap<>();

    @Autowired
    private DiscoveryClient discoveryClient;

    @Scheduled(fixedDelay = 10 * 1000)// 十秒钟更新缓存
    public void updateTargetUrlsCache() {// 更新目标urls
        Map<String, Set<String>> newTargetUrlsCache = new HashMap<>();
        discoveryClient.getServices().forEach(serviceName -> {
            // 获取迭代器中当前应用的机器列表
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);

            // 拼接方式：http://localhost:8080
            Set<String> newTargetUrls = serviceInstances.stream().map(serviceInstance -> {

                return "http://" + serviceInstance.getHost()
                        + ":" + serviceInstance.getPort();

            }).collect(Collectors.toSet());

            newTargetUrlsCache.put(serviceName, newTargetUrls);
        });
        this.targetUrlsCache = newTargetUrlsCache;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        System.out.println("进入rest拦截器");
        // 当前url以应用名称拼接端口
        URI requestUri = request.getURI();
        // 获取请求路径
        String path = requestUri.getPath();
        // 进行分段处理
        String[] paths = path.substring(1).split("/");

        String serviceName = paths[0];
        String uri = paths[1];
        // 快照
        List<String> targetUrls = new LinkedList<>(targetUrlsCache.get(serviceName));

        int size = targetUrls.size();

        int index = new Random().nextInt(size);

        String targetUrl = targetUrls.get(index);

        // 最终服务地址
        String actunalURL = targetUrl + "/" + uri + "?" + requestUri.getQuery();

        // 执行请求
        URL url = new URL(actunalURL);

        URLConnection urlConnection = url.openConnection();

        // 头
        HttpHeaders httpHeaders = new HttpHeaders();
        // 主体
        InputStream inputStream = urlConnection.getInputStream();

        return new SimpleClientHttpResponse(httpHeaders, inputStream);
    }


    // 处理返回
    private static class SimpleClientHttpResponse implements ClientHttpResponse {

        private HttpHeaders headers;

        private InputStream body;

        public SimpleClientHttpResponse(HttpHeaders httpHeaders, InputStream inputStream) {
            this.headers = httpHeaders;
            this.body = inputStream;
        }


        @Override
        public HttpStatus getStatusCode() throws IOException {
            return HttpStatus.OK;
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return 200;
        }

        @Override
        public String getStatusText() throws IOException {
            return "200";
        }

        @Override
        public void close() {

        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }
}
