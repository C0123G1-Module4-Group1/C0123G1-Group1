package com.example.coffee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //Sử dụng thuật toán Bcrypt để mã hóa password.
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //Khi tạo mới tài khoản thì cần mã hóa mật khẩu trước khi lưu vào DB
//        String password = bCryptPasswordEncoder.encode();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();


        // Cấu hình cho Login Form.
        http.authorizeRequests().antMatchers("/login","/home/**","/static/**","/shopping_cart/list","/shopping_cart/operation/").permitAll()
//                .anyRequest().authenticated()
                .antMatchers("/changePass","/coupons/**","/api/coupons/**","/customer/**","/api/customer/**","/orderController/**","/productCoffee/**","/typeProduct/**")
                    .access("hasAnyRole('EMPLOYEE', 'ADMIN')")
                .and()
                .authorizeRequests()
                .antMatchers("/", "/staff","/staff/**")
                .access("hasRole( 'ADMIN')").and().exceptionHandling().accessDeniedPage("/403")
                .and().formLogin()//
                // Submit URL của trang login
                .loginProcessingUrl("/j_spring_security") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/productCoffee/listProduct")//
                .failureUrl("/login?error=true")
                .usernameParameter("account")//
                .passwordParameter("password")
                // Cấu hình cho Logout Page.
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");

        // Cấu hình Remember Me.
        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
        System.out.println();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}