package com.spring.cloud.zk.client;

import ch.qos.logback.core.net.server.Client;
import com.spring.cloud.zk.loadbalance.LoadBalanceRequestInterceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/3/20 20:34
 */
@RestController
public class ClientController {

    @Value("${spring.application.name}")// 当前应用名称
    private String currentServiceName;

    @Autowired// 自定义的restTemplate bean
    private RestTemplate restTemplate;

    @Autowired// 依赖注入 Ribbon RestTemplate Bean
    @LoadBalanced// 利用注解来做过滤，注入方和声明方同时使用
    private RestTemplate ribboRestTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    SayingService sayingService;

    //@Autowired
    //NotSayingService notSayingService;


    // 缓存service,线程安全
    //private Set<String> serviceNames = new ConcurrentSkipListSet<>();
    // 另外一种交换式做法
    //private volatile Set<String> targetUrls = new HashSet<>();

    //Map Key service Name，Value Urls
    private volatile Map<String, Set<String>> targetUrlsCache = new HashMap<>();

//    @Scheduled(fixedDelay = 10 * 1000)// 十秒钟更新缓存
//    public void updateTargetUrls() {// 更新目标urls
//        Set<String> oldTargetUrls = this.targetUrls;
//
//        // 获取当前应用的机器列表
//        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(currentServiceName);
//
//        // 拼接方式：http://localhost:8080
//        Set<String> newTargetUrls = serviceInstances.stream().map(serviceInstance -> {
//
//            return "http://" + serviceInstance.getHost()
//                    + ":" + serviceInstance.getPort();
//
//        }).collect(Collectors.toSet());
//
//        this.targetUrls = newTargetUrls;
//        oldTargetUrls.clear();
//    }

//    @Scheduled(fixedDelay = 10 * 1000)// 十秒钟更新缓存
//    public void updateTargetUrlsCache() {// 更新目标urls
//        Map<String, Set<String>> newTargetUrlsCache = new HashMap<>();
//        discoveryClient.getServices().forEach(serviceName -> {
//            // 获取迭代器中当前应用的机器列表
//            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
//
//            // 拼接方式：http://localhost:8080
//            Set<String> newTargetUrls = serviceInstances.stream().map(serviceInstance -> {
//
//                return "http://" + serviceInstance.getHost()
//                        + ":" + serviceInstance.getPort();
//
//            }).collect(Collectors.toSet());
//
//            newTargetUrlsCache.put(serviceName, newTargetUrls);
//        });
//        System.out.println("长度："+targetUrlsCache.size());
//        this.targetUrlsCache = newTargetUrlsCache;
//    }

//    @GetMapping("/invoke/{serviceName}/say")
//    public String invokeSay(@PathVariable String serviceName,@RequestParam String message) {
//        // 服务器列表
//        // 轮询列表
//        // 选择其中一台服务器
//        // RestTemplate 发送请求到服务器
//        // 输出响应
//
//        // 快照
//        List<String> targetUrls = new LinkedList<>(targetUrlsCache.get(serviceName));
//
//        int size = targetUrls.size();
//
//        int index = new Random().nextInt(size);
//
//        String targetUrl = targetUrls.get(index);
//        System.out.println("测试" + targetUrl);
//        // 发送请求
//        return restTemplate.getForObject(targetUrl + "/say?message=" + message, String.class);
//
//    }


    // 自定义
    @GetMapping("/invoke/{serviceName}/say")
    public String invokeSay(@PathVariable String serviceName, @RequestParam String message) {
        // 服务器列表
        // 轮询列表
        // 选择其中一台服务器
        // RestTemplate 发送请求到服务器
        // 输出响应
        // 发送请求
        return restTemplate.getForObject("/" + serviceName + "/say?message=" + message, String.class);

    }

    // rb方式
    @GetMapping("/loadbalance/invoke/{serviceName}/say")
    public String loadBalanceInvokeSay(@PathVariable String serviceName, @RequestParam String message) {
        // 服务器列表
        // 轮询列表
        // 选择其中一台服务器
        // RestTemplate 发送请求到服务器
        // 输出响应
        // 发送请求
        return restTemplate.getForObject("http://" + serviceName + "/say?message=" + message, String.class);

    }

    // feigin方式
    @GetMapping("/feigin/say")
    public String feiginSay(@RequestParam String message) {
        return sayingService.say(message);
    }

    // 自定义feigin方式
//    @GetMapping("/zidingyi/say")
//    public String ziDingYiSay(@RequestParam String message) {
//        return notSayingService.say(message);
//    }

//    @GetMapping("/invoke/say")
//    public String invokeSay(@RequestParam String message) {
//        // 服务器列表
//        // 轮询列表
//        // 选择其中一台服务器
//        // RestTemplate 发送请求到服务器
//        // 输出响应
//
//        // 快照
//        List<String> targetUrls = new ArrayList<>(this.targetUrls);
//
//        int size = targetUrls.size();
//
//        int index = new Random().nextInt(size);
//
//        String targetUrl = targetUrls.get(index);
//        System.out.println("测试" + targetUrl);
//        // 发送请求
//        return restTemplate.getForObject(targetUrl + "/say?message=" + message, String.class);
//
//    }


    @Bean
    public ClientHttpRequestInterceptor interceptor() {
        return new LoadBalanceRequestInterceptors();
    }

    // ribbon RestTemplate Bean
    @LoadBalanced// 利用注解来做过滤区分不同的restTemplate，注入方和声明方同时使用
    @Bean
    public RestTemplate loadBalancedRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Autowired
    @Qualifier// 使用Qualifier后影响了ribbon的loadbalance，因为@LoadBalanced注解是Qualifier的派生注解之一
    // 并且范围太广所有影响到了，Qualifier也可以起到类型过滤的效果
    public RestTemplate restTemplate(ClientHttpRequestInterceptor interceptor) {// 依赖注入
        //RestTemplate restTemplate = new RestTemplate();
        //System.out.println("增加拦截器："+interceptor);
        // 增加拦截器
        //restTemplate.setInterceptors(Arrays.asList(interceptor));
        //return restTemplate;
        return new RestTemplate();
    }

    @Bean// 得到当前应用所有的restTemplate
    @Autowired
    public Object customizer(@Qualifier Collection<RestTemplate> restTemplates,
                             ClientHttpRequestInterceptor interceptor) {
        restTemplates.forEach(r -> {
            r.setInterceptors(Arrays.asList(interceptor));
        });

        return new Object();
    }
}
