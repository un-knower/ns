package com.newsee.fastdfs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.stereotype.Component;

import com.github.tobato.fastdfs.FdfsClientConfig;

/**
 * @Description: 导入FastDFS-Client组件
 * @author: 胡乾亮
 * @date: 2017年8月23日下午2:33:57
 */

@Configuration
@Import(FdfsClientConfig.class)
// 解决jmx重复注册bean的问题
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@Component
public class FastDFSComponetImport {
	//导入依赖组件
}