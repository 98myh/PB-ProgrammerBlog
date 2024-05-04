package com.pb.pblog.config;

import com.pb.pblog.security.LoginFilter;
import jakarta.servlet.DispatcherType;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //AuthenticationManager가 인자로받을 AuthenticationConfiguration 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;
    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration){
        this.authenticationConfiguration=authenticationConfiguration;
    }

    //AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    //암호화
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{

        http.csrf((auth)->auth.disable());    //RestAPI일때 사용
            //Form 로그인 방식 disable
        http.formLogin((auth) -> auth.disable());

            //http basic 인증방식 disable
        http.httpBasic((auth)->auth.disable());

        http.authorizeHttpRequests((auth)->auth
                .requestMatchers("/","/WEB-INF/views/**","/login","/loginProc","/signup","/signupProc").permitAll() //모든 사용자 접근가능
                .requestMatchers("/admin/**").hasRole("ADMIN") //어드민만 접근 가능
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .dispatcherTypeMatchers(DispatcherType.INCLUDE).permitAll()
                .anyRequest().authenticated()//나머지 요청은 로그인 한 유저들만 접근 가능
            );
        //로그인 필터를 대체할것이기 때문에 addFilterAt
        //LoginFilter은 매개변수로 authenctionManger이 필요하고 authenticationManager은 authenticationConfiguration이 필요해서 위에서 생성해줌
        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration)), UsernamePasswordAuthenticationFilter.class);

            //JWT 방식은 항상 stateless 방식으로 관리
            //->세션 설정
        http.sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

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
