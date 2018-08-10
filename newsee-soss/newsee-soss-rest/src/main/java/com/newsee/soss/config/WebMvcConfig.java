package com.newsee.soss.config;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.newsee.common.utils.CommonUtils;


/**
 * Created by Administrator on 2017/8/19.
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{

    /**
     * 时间格式转换
     * @return
     */
    /*@Bean
    public MappingJackson2HttpMessageConverter jsonConverter(){
        return new MappingJackson2HttpMessageConverter(
                new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
        );
    }*/

	@Bean  
    @Primary  
    @ConditionalOnMissingBean(ObjectMapper.class)  
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {  
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();  
  
        // 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化  
        // Include.Include.ALWAYS 默认  
        // Include.NON_DEFAULT 属性为默认值不序列化  
        // Include.NON_EMPTY 属性为 空（""） 或者为 NULL 都不序列化，则返回的json是没有这个字段的。这样对移动端会更省流量  
        // Include.NON_NULL 属性为NULL 不序列化  
        objectMapper.setSerializationInclusion(Include.NON_NULL);        
        // 字段保留，将null值转为""  
        /*objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>(){  
        @Override  
        public void serialize(Object o, JsonGenerator jsonGenerator,  
                              SerializerProvider serializerProvider)  
                throws IOException, JsonProcessingException{  
            	jsonGenerator.writeString("");  
        	}  
        });*/  
        return objectMapper;  
    }  
	
    /**
     * String 时间格式转换
     * @return
     */
    @Bean
    public Converter<String, Date> dateConvert(){
        return new Converter<String, Date>() {
            SimpleDateFormat simpleDateFormat = null;
            @Override
            public Date convert(String value) {
                Date date = null;
                if (!CommonUtils.isNullOrBlank(value)) {
                    if (value.trim().length() == 10) {
                        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    } else if(value.trim().length() == 16){
                        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    } else if (value.trim().length() == 19) {
                        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    }                    
                }else {
                    return date;
                }
                try {
                    
                    date = simpleDateFormat.parse(value);
                } catch (ParseException e) {
                	e.printStackTrace();
                }
                return date;
            }
        };
    }

    /**
     * 对应spring mvc配置，返回格式类型，编码
     * @return
     */
    /*@Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        List<MediaType> mediaTypes = new ArrayList<MediaType>();
        mediaTypes.add(MediaType.TEXT_HTML);
        mediaTypes.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(mediaTypes);

        return converter;
    }*/

    /**
     * 添加数据格式化
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
        registry.addConverter(dateConvert());
    }

    /**
     * 添加自定义拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }

    /**
     * 返回前端页面
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
    }

    /**
     * 请求，返回信息转换
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
//        converters.add(responseBodyConverter());
        //converters.add(jsonConverter());
    }
    
    /**
     * 处理跨域问题
     */
/*  @Override
    public void addCorsMappings(CorsRegistry registry) {
           registry.addMapping("/**")
           .allowedHeaders("*")
           .allowedOrigins("*")
           .allowedMethods("*")
           .allowCredentials(false).maxAge(3600);
    }*/
    
}
