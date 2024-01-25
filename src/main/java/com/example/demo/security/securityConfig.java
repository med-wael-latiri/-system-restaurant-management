package com.example.demo.security;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class securityConfig {
    private CustomUserDetailsService userDetailsService;

@Autowired
@Bean
public static PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}
    @Bean
    public SecurityFilterChain filterChian(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests().requestMatchers("/login/**" , "/register/**", "/restaurant-admin/**", "/restaurant/**", "/css/**","/assets/**")
                .permitAll()

                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/restaurant")
                        .loginProcessingUrl("/login")
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                );
                http.formLogin()
                .successHandler(new AuthenticationSuccessHandler() {

                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        boolean isAdmin = authentication.getAuthorities().stream()
                                .anyMatch(r -> r.getAuthority().equals("ADMIN"));
                        boolean isClient = authentication.getAuthorities().stream()
                                .anyMatch(r -> r.getAuthority().equals("USER"));
                        System.out.println("Logged user: " + authentication.getName()
                                +", isAdmin : "+ isAdmin+ ", isClient : "+isClient);
                        if(isClient)
                        {response.sendRedirect("/restaurant");}
                        else
                        {response.sendRedirect("/restaurant-admin");}


                    }
                });
                return http.build();

    }
public void configure(AuthenticationManagerBuilder builder) throws Exception{
    builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
}
}
