package org.leobackend.configuration;

import org.leobackend.interceptor.LeoBackendInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created on 2020-12-26.
 * mvc配置类
 *
 * @author Leo
 */
@Configuration
public class LeoBackendMvcConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LeoBackendInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/api/auth/**");
    }
}
