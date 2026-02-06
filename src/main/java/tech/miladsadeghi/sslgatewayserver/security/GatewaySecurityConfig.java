package tech.miladsadeghi.sslgatewayserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class GatewaySecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .x509(x509 -> x509
                        .subjectPrincipalRegex("CN=(.*?)(?:,|$)")
                        .userDetailsService(userDetailsService())
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {

            // Only allow core-service certificate
            if ("ssl-core".equals(username)) {
                return User.withUsername(username)
                        .password("")
                        .authorities("ROLE_SERVICE")
                        .build();
            }

            throw new UsernameNotFoundException(
                    "Certificate not authorized: " + username);
        };
    }
}
