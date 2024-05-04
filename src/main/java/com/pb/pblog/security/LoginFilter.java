package com.pb.pblog.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public LoginFilter(AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;
    }



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username=obtainUsername(request);
        String password=obtainPassword(request);

        System.out.println("아이디 테스트 : "+username );
        System.out.println("비밀번호 테스트 : "+password);

        //username과 password를 검증하기 위해서는 token에 담아야함
        UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(username,password,null); //세번째 인자는 role이 들어가야함

        return authenticationManager.authenticate(authToken);
    }

    //jwt를 반환할 것임
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,Authentication authentication){

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

    }
}
