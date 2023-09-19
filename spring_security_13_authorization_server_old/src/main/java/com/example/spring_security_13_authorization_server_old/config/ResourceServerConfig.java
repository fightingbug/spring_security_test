package com.example.spring_security_13_authorization_server_old.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

//@Configuration
//@EnableResourceServer
// extends ResourceServerConfigAdapter
public class ResourceServerConfig {

    private DataSource dataSource;


    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }
    //通过数据库校验令牌

    //@Override
    /*
    * public void config(ResourceServerSecurityConfig resources) throws Exception{
    *           resources.tokenStore(tokenStore());
    * }
    * */


}
