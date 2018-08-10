package com.newsee.soss;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class })
@ComponentScan("com.newsee")
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
public class SossApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SossApplication.class);
		app.setBannerMode(Banner.Mode.OFF); //关闭banner
		app.run(args);
	}
}
