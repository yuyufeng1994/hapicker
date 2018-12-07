package com.hapicker.web.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * https://blog.csdn.net/liyanan21/article/details/81867113
 * @author yuyufeng
 * @date 2018/12/7.
 */

//@Component
public class FeignBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition bd = beanFactory.getBeanDefinition("feignContext");
        bd.setDependsOn("eurekaServiceRegistry", "inetUtils");
    }
}