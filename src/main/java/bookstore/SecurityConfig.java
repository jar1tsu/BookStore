package bookstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled=true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authorize) -> authorize
            .requestMatchers("/login").permitAll()
            
            .requestMatchers("/h2-console/**").permitAll()
            .requestMatchers("/books/delete/**").hasRole("ADMIN")
            .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults())

            .formLogin((form) -> form
            .loginPage("/login")
            .defaultSuccessUrl("/books", true)
            .permitAll())

            .logout((logout) -> logout
            .permitAll());

            
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }  
}
