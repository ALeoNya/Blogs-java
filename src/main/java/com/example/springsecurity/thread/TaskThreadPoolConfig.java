package com.example.springsecurity.thread;

import org.simpleframework.xml.core.Validate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


@Configuration
@EnableAsync  //允许开启多线程
public class TaskThreadPoolConfig {
    @Value("${spring.task.pool.corePoolSize}")
    private int corePoolSize;
    @Value("${spring.task.pool.maxPoolSize}")
    private int maxPoolSize;
    @Value("${spring.task.pool.keepAliveSeconds}")
    private int keepAliveSeconds;
    @Value("${spring.task.pool.queueCapacity}")
    private int queueCapacity;

    @Bean("firstThread")
    public Executor ThreadServiceExecutor() {
        ThreadPoolTaskExecutor exceutor = new ThreadPoolTaskExecutor();
        exceutor.setCorePoolSize(corePoolSize);
        exceutor.setMaxPoolSize(maxPoolSize);
        exceutor.setKeepAliveSeconds(keepAliveSeconds);
        exceutor.setQueueCapacity(queueCapacity);
        exceutor.setWaitForTasksToCompleteOnShutdown(true);  //线程执行完自动销毁
        exceutor.initialize();  //初始化线程池
        return exceutor;
    }
}
