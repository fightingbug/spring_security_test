package com.example.spring_security_11_dynamicauthorize.securiy.metasource;

import com.example.spring_security_11_dynamicauthorize.entity.Menu;
import com.example.spring_security_11_dynamicauthorize.entity.Role;
import com.example.spring_security_11_dynamicauthorize.service.MenuService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.log.LogMessage;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;


@Component
public class CustomerSecurityMetaSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuService menuService;


    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
            //1.当前请求对象
        String requestURI = ((FilterInvocation)object).getRequest().getRequestURI();
            //2.查询菜单
        List<Menu> allMenu = menuService.getAll();
        for(Menu menu:allMenu){
            System.out.println(requestURI);
            System.out.println(menu);
            if(antPathMatcher.match(menu.getPattern(),requestURI)){
                String[] roles = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
                return SecurityConfig.createList(roles);
            }
        }
            return null;


        /*final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        int count = 0;
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : this.requestMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
            else {
                if (this.logger.isTraceEnabled()) {
                    this.logger.trace(LogMessage.format("Did not match request to %s - %s (%d/%d)", entry.getKey(),
                            entry.getValue(), ++count, this.requestMap.size()));
                }
            }
        }
        return null;*/
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
