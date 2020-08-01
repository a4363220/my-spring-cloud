package com.spring.cloud.zk.client;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/3/22 19:48
 */
public class RestClientsRegistrar implements ImportBeanDefinitionRegistrar, BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {
        ClassLoader classLoader = importingClassMetadata.getClass().getClassLoader();

        // 将@RestClient接口代理实现注册为Bean，因为需要用到@Autowired
        Map<String, Object> attributes = importingClassMetadata.
                getAnnotationAttributes(EnableRestClient.class.getName());

        Class<?>[] clients = (Class<?>[]) attributes.get("clients");
        // 接口类对象数组
        // 筛选所有接口
        Stream.of(clients)
                .filter(Class::isInterface)// 仅选择接口
                .filter(interfaceClass -> {
                    return AnnotationUtils.findAnnotation(interfaceClass, RestClient.class) != null;
                })// 仅选择标注@RestClient
                .forEach(interfaceClass -> {
                    // 获取 @RestClient 元信息
                    RestClient restClient = AnnotationUtils.findAnnotation(interfaceClass, RestClient.class);
                    // 获取应用名称
                    String serviceName = restClient.name();

                    // RestTemplate -> serviceName/uri?params...

                    // @RestClient 接口变成jdk动态代理
                    Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{interfaceClass},
                            new RequestMappingMethodHandler(serviceName, beanFactory));
                    // 将 @ RestClient接口代理实现注册为Bean(@Autowired)
                    // BeanDefinitionRegistry registry

                    String beanName = "RestClient." + serviceName;
                    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RestClientsRegistrar.class);

                    // 增加第一个构造器参数引用
                    beanDefinitionBuilder.addConstructorArgValue(proxy);
                    // 增加第二个构造器参数引用
                    beanDefinitionBuilder.addConstructorArgValue(restClient);

                    BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

                    registry.registerBeanDefinition(beanName, beanDefinition);


                });
    }

    private static class RestClientClassFactoryBean implements FactoryBean {

        private final Object proxy;
        private final Class<?> restClientClass;

        private RestClientClassFactoryBean(Object proxy, Class<?> restClientClass) {
            this.proxy = proxy;
            this.restClientClass = restClientClass;
        }

        @Override
        public Object getObject() throws Exception {
            return null;
        }

        @Override
        public Class<?> getObjectType() {
            return restClientClass;
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
