package com.jarekjal.endo.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("jarek")
//                .password("jrk")
//                .roles("USER")
//                .and()
//                .withUser("ania")
//                .password("ania")
//                .roles("USER")
//                .and()
//                .withUser("admin")
//                .password("admin")
//                .roles("ADMIN");


          auth.jdbcAuthentication().dataSource(dataSource)
                  .usersByUsernameQuery("select username,password,enabled from users where username = ? ")
                  .authoritiesByUsernameQuery("select username,role from user_roles where username = ?");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/logout", "/h2-console/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/**").hasAnyRole("ADMIN", "USER")
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error=true")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/login?logout=true")
                    .invalidateHttpSession(true)
                    .permitAll()
                .and()
                    .csrf().disable();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        //TODO: Use osm of more secure encoder
        return NoOpPasswordEncoder.getInstance();
    }
}
