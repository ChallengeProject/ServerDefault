package me.hackathon.root.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import me.hackathon.root.support.interceptor.CorsInterceptor;
import me.hackathon.root.support.interceptor.HttpReqFilterInterceptor;
import me.hackathon.root.support.interceptor.HttpReqLoggingInterceptor;
import me.hackathon.root.support.json.MappingJacksonHttpMessageConverter;

@EnableWebMvc
@Configuration
@EnableAspectJAutoProxy
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private HttpReqLoggingInterceptor httpReqLoggingInterceptor;
    @Autowired
    private HttpReqFilterInterceptor httpReqFilterInterceptor;

    @Autowired
    private CorsInterceptor corsInterceptor;

    /* Interceptor */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpReqLoggingInterceptor);
        registry.addInterceptor(corsInterceptor);
        registry.addInterceptor(httpReqFilterInterceptor);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJacksonHttpMessageConverter());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}