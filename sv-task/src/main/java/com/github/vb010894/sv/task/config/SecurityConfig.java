package com.github.vb010894.sv.task.config;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends VaadinWebSecurity {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http, "/login", "/login");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService service() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user").password(this.passwordEncoder().encode("1234098")).roles("USER").build(),
                User.withUsername("admin").password(this.passwordEncoder().encode("1234098")).roles("ADMIN").build()
        );
    }
}
