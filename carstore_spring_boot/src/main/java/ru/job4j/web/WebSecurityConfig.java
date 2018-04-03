package ru.job4j.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * Web Security Configuration.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * current user information.
     */
    @Autowired
    private MyUserDetailService myUserDetailService;

    /**
     * authorization by current user.
     * @param auth - Authentication Manager.
     * @throws Exception - exc.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }

    /**
     * access configure.
     * @param http - HttpSecurity.
     * @throws Exception - exc.
     */
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/add").hasRole("ADMIN")
                .antMatchers("/").hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

    /**
     * encoder.
     * @return - instance.
     */
    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
