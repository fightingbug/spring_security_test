package com.example.spring_security_11_dynamicauthorize.securiy.config;

import com.example.spring_security_11_dynamicauthorize.securiy.metasource.CustomerSecurityMetaSource;
import com.example.spring_security_11_dynamicauthorize.service.MyUserDetaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class SecurityConfig {


    @Autowired
    private CustomerSecurityMetaSource customerSecurityMetaSource;


    @Bean
    public MyUserDetaisService myUserDetaisService(){
        return new MyUserDetaisService();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{


        //自定义授权处理 1.获取工厂对象

        ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);

        //2.设置 自定义URL权限处理
        http.apply(new UrlAuthorizationConfigurer<>(applicationContext))
                        .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                            @Override
                            public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                                object.setSecurityMetadataSource(customerSecurityMetaSource);
                                //是否拒绝公共资源访问
                                object.setRejectPublicInvocations(false);
                                return object;
                                }
                        });


        http.formLogin(form->{
        }).csrf(csrf->{
         csrf.disable();
        });

        return http.build();
    }
}
