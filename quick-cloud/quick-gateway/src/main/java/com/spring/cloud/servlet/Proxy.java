package com.spring.cloud.servlet;

import com.spring.cloud.gateway.ZookeeperLoadBalance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 描述：
 * 服务网关的路由规则
 * /{service-name}/{service-uri}
 *
 * @author 小谷
 * @Date 2020/3/23 16:52
 */
@WebServlet(name = "gateway", urlPatterns = "/gateway/*")
public class Proxy extends HttpServlet {

    private Logger log = LoggerFactory.getLogger(Proxy.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    private ServiceInstance randomServiceInstance(String serviceName) {
        // 获取服务实例列表(服务ip、端口、是否为https)
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
        // 获得服务实例总数
        int size = serviceInstances.size();
        // 随机获取数组下标
        int index = new Random().nextInt(size);
        return serviceInstances.get(index);
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        log.info("请求进来");
        String pathInfo = req.getPathInfo();
        String[] parts = StringUtils.split(pathInfo.substring(1), "/");
        // 获取服务名称
        String serviceName = parts[0];
        // 获取服务url
        String serviceUrl = "/" + parts[1];
        // 随机选择一台实例
        ServiceInstance serviceInstance = randomServiceInstance(serviceName);
        // 构建目标服务uri -》 schema://ip:port/serviceURI
        String targetURL = buildTargetURL(serviceInstance, serviceUrl, req);

        // 创建转发客户端
        RestTemplate restTemplate = new RestTemplate();
        log.info("uri：" + targetURL);

        // 构造Request实体
        RequestEntity<byte[]> requestEntity = null;
        try {
            requestEntity = createRequestEntity(req, targetURL);
            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(requestEntity, byte[].class);
            writeHeaders(responseEntity, res);
            writeBody(responseEntity, res);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String buildTargetURL(ServiceInstance serviceInstance, String serviceURI, HttpServletRequest request) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(serviceInstance.isSecure() ? "https://" : "http://")
                .append(serviceInstance.getHost()).append(":").append(serviceInstance.getPort())
                .append(serviceURI);
        String queryString = request.getQueryString();
        if (StringUtils.hasText(queryString)) {
            urlBuilder.append("?").append(queryString);
        }
        return urlBuilder.toString();
    }

    private RequestEntity<byte[]> createRequestEntity(HttpServletRequest request, String url) throws URISyntaxException, IOException {
        // 获取当前请求方法
        String method = request.getMethod();
        // 装换 HttpMethod
        HttpMethod httpMethod = HttpMethod.resolve(method);
        byte[] body = createRequestBody(request);
        MultiValueMap<String, String> headers = createRequestHeaders(request);
        RequestEntity<byte[]> requestEntity = new RequestEntity<byte[]>(body, headers, httpMethod, new URI(url));
        return requestEntity;
    }

    private MultiValueMap<String, String> createRequestHeaders(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        List<String> headerNames = Collections.list(request.getHeaderNames());
        for (String headerName : headerNames) {
            List<String> headerValues = Collections.list(request.getHeaders(headerName));
            for (String headerValue : headerValues) {
                headers.add(headerName, headerValue);
            }
        }
        return headers;
    }

    private byte[] createRequestBody(HttpServletRequest request) throws IOException {
        InputStream inputStream = request.getInputStream();
        return StreamUtils.copyToByteArray(inputStream);
    }


    /**
     * 输出 Body 部分
     *
     * @param responseEntity
     * @param response
     * @throws IOException
     */
    private void writeBody(ResponseEntity<byte[]> responseEntity, HttpServletResponse response) throws IOException {
        if (responseEntity.hasBody()) {
            byte[] body = responseEntity.getBody();
            // 输出二进值
            ServletOutputStream outputStream = response.getOutputStream();
            // 输出 ServletOutputStream
            outputStream.write(body);
            outputStream.flush();
        }
    }

    private void writeHeaders(ResponseEntity<byte[]> responseEntity, HttpServletResponse response) {
        // 获取相应头
        HttpHeaders httpHeaders = responseEntity.getHeaders();
        // 输出转发 Response 头
        for (Map.Entry<String, List<String>> entry : httpHeaders.entrySet()) {
            String headerName = entry.getKey();
            List<String> headerValues = entry.getValue();
            for (String headerValue : headerValues) {
                response.addHeader(headerName, headerValue);
            }
        }
    }


}
