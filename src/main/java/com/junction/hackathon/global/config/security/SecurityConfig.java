package com.junction.hackathon.global.config.security;

import com.junction.hackathon.domain.member.domain.service.MemberQueryService;
import com.junction.hackathon.global.config.security.filter.JwtAccessDeniedHandler;
import com.junction.hackathon.global.config.security.filter.JwtAuthenticationEntryPoint;
import com.junction.hackathon.global.config.security.jwt.JwtSecurityConfig;
import com.junction.hackathon.global.config.security.jwt.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenUtil tokenUtil;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final MemberQueryService memberQueryService;

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()   //CSRF 보호 비활성화
                .formLogin().disable()  //폼 로그인 비활성화
                .httpBasic().disable()  // HTTP 기본 인증 비활성화
                .exceptionHandling()    //예외 처리 설정
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) //인증되지 않은 사용자가 보호된 리소스에 액세스 할 때 호출되는 JwtAuthenticationEntryPoint 설정
                .accessDeniedHandler(jwtAccessDeniedHandler)    //권한이 없는 사용자가 보호된 리소스에 액세스 할 때 호출되는 JwtAccessDeniedHandler 설정
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Spring Security에서 세션을 사용하지 않도록 설정
                .and()
                .authorizeRequests()
                .antMatchers("/auth/**", "/program/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtSecurityConfig(tokenUtil, memberQueryService));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resource/**", "/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addExposedHeader("Authorization");
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}