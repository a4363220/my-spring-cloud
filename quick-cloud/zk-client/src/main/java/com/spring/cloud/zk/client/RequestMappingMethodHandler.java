package com.spring.cloud.zk.client;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/3/22 22:18
 */
public class RequestMappingMethodHandler implements InvocationHandler {

    private final ParameterNameDiscoverer parameterNameDiscoverer
            = new DefaultParameterNameDiscoverer();

    private final String serviceName;

    private BeanFactory beanFactory;

    public RequestMappingMethodHandler(String serviceName, BeanFactory beanFactory) {
        this.serviceName = serviceName;
        this.beanFactory = beanFactory;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 过滤@RequestMapping方法
        GetMapping requestMapping = AnnotationUtils.findAnnotation(method, GetMapping.class);
        if (requestMapping != null) {

            String[] uri = requestMapping.value();// 得到uri
            // http://${serviceName}/${uri}
            StringBuilder urlBuilder = new StringBuilder("http://").append(serviceName)
                    .append("/").append(uri[0]);

            // 获取方法参数数量
            int count = method.getParameterCount();
            // 方法参数是有顺序的 todo
            String[] paramsNames = parameterNameDiscoverer.getParameterNames(method);
            // 方法参数的类型
            Class<?>[] paramsTypes = method.getParameterTypes();
            // 方法注解集合
            Annotation[][] annotations = method.getParameterAnnotations();

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < count; i++) {
                Annotation[] paramAnnotation = annotations[i];

                RequestParam requestParam = (RequestParam) paramAnnotation[0];

                if (requestParam != null) {
                    String paramName = "";
                            //paramsNames[i];
                    Class<?> paramType = paramsTypes[i];
                    // 获得Http请求参数
                    String requestParamName =
                            StringUtils.hasText(requestParam.value()) ? requestParam.value() : paramName;
                    String requestParamValue =
                            String.class.equals(paramType) ? (String) args[i] : String.valueOf(args[i]);
                    // uri?name=value&n2=v2&n3=v3
                    sb.append("&").append(requestParamName).append("=").append(requestParamValue);
                }

            }

            String queryString = sb.toString();
            if (StringUtils.hasText(queryString)) {
                urlBuilder.append("?").append(queryString);
            }
            // http://${serviceName}/${uri}?${queryString}
            String url = urlBuilder.toString();
            // 获取RestTemplate，Bean名称 "loadbalanceRestTemplate"
            // 获得BeanFactory
            RestTemplate restTemplate = beanFactory.getBean("loadbalanceRestTemplate", RestTemplate.class);

            return restTemplate.getForObject(url, method.getReturnType());
        }
        return null;
    }
}
