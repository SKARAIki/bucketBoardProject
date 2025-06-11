package com.example.springboardproject.Config;

import com.example.springboardproject.Filter.LoginFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean loginFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        // Filter 등록
        filterRegistrationBean.setFilter(new LoginFilter());
        /**
         * 필터의 우선순위 적용
         * 0순위 - 인코딩 (요청과 응답에 올바른 문자셋 적용)
         * 1순위 - 보안 (악성코드검사)
         * 2순위 - 로그인
         */
        filterRegistrationBean.setOrder(2);
        /**
         *  전체 URL에 Filter 적용
         *  "/api/*" -> /api 이후 모든 url에 필터적용
         */
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
