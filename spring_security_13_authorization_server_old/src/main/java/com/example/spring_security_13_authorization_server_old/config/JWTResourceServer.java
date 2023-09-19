package com.example.spring_security_13_authorization_server_old.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;


//@Configuration
//@EnableResourceServer
// extends ResourceServerConfigAdapter
public class JWTResourceServer {


/*
* 资源服务器只需要配置 令牌存储 令牌转化
 */

    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }


    //通过数据库校验令牌

    //@Override
    /*
     * public void config(ResourceServerSecurityConfig resources) throws Exception{
     *           resources.tokenStore(tokenStore());
     * }
     * */

    @Bean //jwt秘钥转化令牌
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");//秘钥
        return converter;
    }

}
