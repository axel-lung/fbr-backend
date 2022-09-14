/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.security;

import fr.alng.footapi.filter.CustomAuthenticationFilter;
import fr.alng.footapi.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;


@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        http.csrf().disable();
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**", "/api/user/save/**").permitAll();
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,
                        "/api/areas/**",
                        "/api/area/**",
                        "/api/bet/**",
                        "/api/bets/**",
                        "/api/competitions/**",
                        "/api/competition/**",
                        "/api/match/**",
                        "/api/matches/**",
                        "/api/room/**",
                        "/api/rooms/**",
                        "/api/season/**",
                        "/api/seasons/**",
                        "/api/team/**",
                        "/api/teams/**",
                        "/api/users/")
                .hasAnyAuthority("ROLE_USER");
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,
                        "/api/room/adduser",
                        "/api/bet/**")
                .hasAnyAuthority("ROLE_USER");
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,
                        "/api/area/**",
                        "/api/bet/**",
                        "/api/competition/**",
                        "/api/match/**",
                        "/api/room/**",
                        "/api/season/**",
                        "/api/team/**",
                        "/api/area/**")
                .hasAnyAuthority("ROLE_ADMIN");

        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

