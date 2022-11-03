//package com.workshop.carautionsystem.config;
//
//import com.workshop.carautionsystem.security.jwt.JwtEntryPoint;
//import com.workshop.carautionsystem.security.jwt.JwtTokenFilter;
//import com.workshop.carautionsystem.security.userPrinciple.UserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    UserDetailsService userDetailsService;
//    @Autowired
//    JwtEntryPoint jwtEntryPoint;
//    @Bean
//    public JwtTokenFilter jwtTokenFilter(){
//        return new JwtTokenFilter();
//    }
//    @Override
//    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
//        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManager() throws Exception{
//        return super.authenticationManager();
//    }
//    @Override
//    public void configure(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.cors().and().csrf().disable().
//                authorizeRequests().antMatchers("/api/v1/**").permitAll()
//                .anyRequest().authenticated()
//                .and().exceptionHandling()
//                .authenticationEntryPoint(jwtEntryPoint)
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        httpSecurity.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//}