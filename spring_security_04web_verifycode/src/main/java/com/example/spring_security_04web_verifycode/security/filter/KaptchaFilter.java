package com.example.spring_security_04web_verifycode.security.filter;


import com.example.spring_security_04web_verifycode.security.exception.KaptchaNotMatchException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class KaptchaFilter extends UsernamePasswordAuthenticationFilter {

    private final String FORM_KAPTCHA_KEY = "kaptcha";

    private String kaptcharParamater = FORM_KAPTCHA_KEY;


    public KaptchaFilter(AuthenticationManager authenticationManager) {

        setUsernameParameter("uname");
        setPasswordParameter("passwd");
        setKaptcharParamater("kaptcha");
        setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl("/doL");

        this.setAuthenticationSuccessHandler((request, response, authentication) -> {
            response.getWriter().println("success");
            //response.sendRedirect("/index");
        });
        this.setAuthenticationFailureHandler((request, response, exception) -> {
            response.getWriter().println("failure");
            //response.sendRedirect("/login");
        });

    }
    public String getKaptcharParamater() {
        return kaptcharParamater;
    }

    public void setKaptcharParamater(String kaptcharParamater) {
        this.kaptcharParamater = kaptcharParamater;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
        //判断请求
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        //获取验证码
        String verifyCode = request.getParameter(getKaptcharParamater());
        String sessionVerifyCode = (String) request.getSession().getAttribute("kaptcha");

        //比较验证码
        if(!ObjectUtils.isEmpty(verifyCode) && !ObjectUtils.isEmpty(sessionVerifyCode) &&
            verifyCode.equalsIgnoreCase(sessionVerifyCode)){

            String username = request.getParameter(getUsernameParameter());
            String password = request.getParameter(getPasswordParameter());

            UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.authenticated(username,
                    password,null);

            SecurityContextHolder.getContext().setAuthentication(authRequest);
            request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

            // Allow subclasses to set the "details" property
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);

        }
        throw new KaptchaNotMatchException("验证码不对",new Throwable());
    }
}
