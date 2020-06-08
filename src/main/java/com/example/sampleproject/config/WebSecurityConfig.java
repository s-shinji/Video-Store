package com.example.sampleproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //ログインページを指定。
        //ログインページへのアクセスは全員許可する。
        http.formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/authenticate")
            .usernameParameter("userName")
            .passwordParameter("password")
			.defaultSuccessUrl("/index")
			.failureUrl("/login-error")
			.permitAll();
		
		http.logout()
			.logoutSuccessUrl("/login")
			.permitAll();
		

		http.csrf().disable().authorizeRequests()
			//antMatchers().permitAll()で記載したURLへはログインなしで入れる
			.antMatchers("/css/**", "/images/**", "/js/**").permitAll()
            .antMatchers("/RegistrationForm").permitAll()
            .antMatchers("/Register").permitAll()
            .antMatchers("/Result").permitAll()
            .antMatchers("/index").permitAll()
            .antMatchers("/video/{id}").permitAll()
            .antMatchers("/").permitAll()
            .antMatchers("/top").permitAll()
            .antMatchers("/search").permitAll()
			//anyRequest().authenticated()でその他の全てのページへはログインが必要にする
            .anyRequest().authenticated();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }
}