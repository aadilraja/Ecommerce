package application.server.Configs;

import application.server.Controller.CustomAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    public  WebSecurityConfig(CustomAuthenticationFailureHandler customAuthenticationFailureHandler) {
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/api/auth/**","/api/**").permitAll())
                .csrf(csrf -> {
                    try {
                        csrf
                        .ignoringRequestMatchers("/api/auth/**","/api/**")
                        .disable()
                                .formLogin(
                                        form->form
                                                .loginProcessingUrl("/api/login")
                                                .failureHandler(customAuthenticationFailureHandler)
                                                .permitAll()
                                );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });


        return http.build();

    }
    @Bean
    public PasswordEncoder Encoder() {
        return new BCryptPasswordEncoder();
    }


}