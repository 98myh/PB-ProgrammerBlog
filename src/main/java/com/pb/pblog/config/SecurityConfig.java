package com.pb.pblog.config;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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

        //테스트 환경에서는 disable 해도 됨
        //http.csrf((auth)->auth.disable());


        //httpBasic로그인 방식 추가
        //http.httpBasic(Customizer.withDefaults());
        http.httpBasic((auth)->auth.disable());


        http.authorizeHttpRequests((auth)->auth
                .requestMatchers("/board/write").hasAnyRole("user","admin")
                .requestMatchers("/admin").hasRole("admin") //어드민만 접근 가능
                .requestMatchers("/","/WEB-INF/views/**","/login","/loginProc","/logout","/signup","/signupProc","/check-id",
                        "/board/**").permitAll() //모든 사용자 접근가능
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .dispatcherTypeMatchers(DispatcherType.INCLUDE).permitAll()
                .anyRequest().authenticated()//나머지 요청은 로그인 한 유저들만 접근 가능
            );


        //로그인 설정
        http.formLogin((auth) ->
                auth.loginPage("/login")
                        .usernameParameter("id")
                        .passwordParameter("password")
                        .loginProcessingUrl("/loginProc")
                        .permitAll());



        //로그아웃 설정
        http.logout((auth)->auth.logoutUrl("/logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login")
                .permitAll());//세션 삭제


        //다중 로그인 설정
        //maximunSession에 인자로 int값을 받도 몇개의 세션에서 로그인을 허용할 것인가?
        //maxSessionPreventsLogin은 다중 로그인 개수를 초과하였을 경우 처리방볍
        // -> true 초과시 새로운 로그인 차단, false 초과시 기존 세션 하나 삭제
//        http.sessionManagement((auth)->auth.maximumSessions(2)
//                .maxSessionsPreventsLogin(true));

        //세션 고정 공격 보호
        //.none 로그인 시 세션 정보 변경 안함
        //.newSession 로그인 시 세션 새로 생성
        //.changeSessionId 로그인 시 동일한 세션에 대한 id 변경 -> 주로 사용
        http.sessionManagement((auth)->auth.sessionFixation().changeSessionId());

        return http.build();
    }

    //css,js,images 불러오지 못해서 설정
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web->web.ignoring()
                .requestMatchers(PathRequest
                        .toStaticResources()
                        .atCommonLocations())
                .requestMatchers("/resources/**","/favicon.ico");
    }
}
