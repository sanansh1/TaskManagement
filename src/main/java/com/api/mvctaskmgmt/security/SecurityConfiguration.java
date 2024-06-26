package com.api.mvctaskmgmt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security configration
 */
@Configuration
@EnableWebSecurity(debug = true)
@ComponentScan(basePackages = "com.api.mvctaskmgmt")
public class SecurityConfiguration {
   @Bean
   public InMemoryUserDetailsManager userDetailsService() {
       UserDetails user = User.withDefaultPasswordEncoder()
               .username("user")
               .password("password")
               .roles("USER")
               .build();
       UserDetails admin = User.withDefaultPasswordEncoder()
               .username("admin")
               .password("password")
               .roles("ADMIN", "USER")
               .build();
       UserDetails userSandhya = User.withDefaultPasswordEncoder()
               .username("Sandhya")
               .password("password")
               .roles("ADMIN", "USER")
               .build();
       return new InMemoryUserDetailsManager(user, admin, userSandhya);
   }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(auth -> {
                    auth.requestMatchers("/login").permitAll();
                    //auth.requestMatchers("/").hasRole("USER");
                    auth.requestMatchers("/*task*/**").hasRole("USER");
                })
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

}
