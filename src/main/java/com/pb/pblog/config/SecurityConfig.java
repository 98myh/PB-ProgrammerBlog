package com.pb.pblog.config;

import jakarta.servlet.DispatcherType;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //암호화
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{

            //http basic 인증방식 disable
        http.httpBasic((auth)->auth.disable());

        http.authorizeHttpRequests((auth)->auth
                .requestMatchers("/","/WEB-INF/views/**","/login","/loginProc","/signup","/signupProc","/check-id").permitAll() //모든 사용자 접근가능
                .requestMatchers("/admin/**").hasRole("ADMIN") //어드민만 접근 가능
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .dispatcherTypeMatchers(DispatcherType.INCLUDE).permitAll()
                .anyRequest().authenticated()//나머지 요청은 로그인 한 유저들만 접근 가능
            );
        http.formLogin((auth) ->
                auth.loginPage("/login")
                        .loginProcessingUrl("/loginProc").permitAll().defaultSuccessUrl("/"));

        //테스트 환경에서는 disable 추후 enable
        http.csrf((auth)->auth.disable());    //RestAPI일때 사용

        return http.build();
    }

    //css,js,images 불러오지 못해서 설정
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web->web.ignoring()
                .requestMatchers(PathRequest
                        .toStaticResources()
                        .atCommonLocations())
                .requestMatchers("/resources/**");
    }


}
