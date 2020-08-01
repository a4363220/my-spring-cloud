package com.spring.cloud.zookeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin.com.DispatchClient;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/3/20 16:30
 */
@RestController
public class ServiceControoler {

    @Autowired
    private DiscoveryClient discoveryClient;


    /**
     * 返回所有的服务名称
     * */
    @GetMapping("/services")
    public List<String> getAllService(){
        // 获取所有
        return discoveryClient.getServices();
    }

    /**
     *
     * */
    @GetMapping("/services/{serviceName}")
    public List<String> getAllServiceInstances(@PathVariable String serviceName){
        return discoveryClient.getInstances(serviceName)
                .stream().map(serviceInstance -> {
                    return serviceInstance.getServiceId()+"-"+serviceInstance.getHost()
                            +":"+serviceInstance.getPort();
                }).collect(Collectors.toList());
    }




}
