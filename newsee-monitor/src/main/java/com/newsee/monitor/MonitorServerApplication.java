package com.newsee.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import de.codecentric.boot.admin.config.EnableAdminServer;

/**
 * 程序启动
 */

@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
@RefreshScope
public class MonitorServerApplication {
	public static void main(String[] args) {
        SpringApplication.run(MonitorServerApplication.class, args);
        
    }
}
