package com.newsee.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务启动入口
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class RegistryServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(RegistryServerApplication.class, args);
	}
}