package com.spring.security.test.Spring.Security.Test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class Config {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails staffUser = User.withUsername("Shyam").password(passwordEncoder().encode("password")).roles("STAFF").build();

        UserDetails adminUser = User.withUsername("Anmol").password(passwordEncoder().encode("password")).roles("ADMIN").build();

        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(staffUser,adminUser);

        return inMemoryUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.
                        requestMatchers("/home/admin").hasRole("ADMIN")
                        .requestMatchers("/home/staff").hasRole("STAFF")
                        .requestMatchers("/home/public")
                .permitAll().anyRequest().authenticated())
                .formLogin(withDefaults());

        return httpSecurity.build();
    }
}
