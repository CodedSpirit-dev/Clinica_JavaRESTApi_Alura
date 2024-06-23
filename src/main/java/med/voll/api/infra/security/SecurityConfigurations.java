package med.voll.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class is responsible for configuring the security settings for the application.
 * It uses Spring's @Configuration and @EnableWebSecurity annotations to indicate that it is a configuration class and that it should enable Spring Security's web security support.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    /**
     * This method configures the security filter chain.
     * It disables CSRF, sets session management to stateless, and configures request authorization rules.
     * @param httpSecurity the HttpSecurity to modify
     * @return a SecurityFilterChain that holds the security configuration
     * @throws Exception if an error occurs when configuring the HttpSecurity
     */
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth .requestMatchers(HttpMethod.POST, "/login")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * This method provides the AuthenticationManager bean.
     * @param authenticationConfiguration the AuthenticationConfiguration to use
     * @return an AuthenticationManager
     * @throws Exception if an error occurs when getting the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * This method provides the PasswordEncoder bean.
     * It uses BCryptPasswordEncoder as the implementation.
     * @return a PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}