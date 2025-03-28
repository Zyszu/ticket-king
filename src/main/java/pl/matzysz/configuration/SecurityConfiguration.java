package pl.matzysz.configuration;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Resource(name="myAppUserDetailsService")
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // for database users
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

        http
                .authorizeHttpRequests((authz) -> authz
                        // permit all
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/home**").permitAll()
                        .requestMatchers("/access-denied").permitAll()
                        // permit users
                        .requestMatchers("/personal-data**").hasAnyRole("USER")
                        .requestMatchers("/address**").hasAnyRole("USER")
                        .requestMatchers("/register-company**").hasAnyRole("USER")
                        // permit proprietors
                        .requestMatchers("/fleet**").hasAnyRole("PROPRIETOR")
                        .requestMatchers("/flights**").hasAnyRole("PROPRIETOR")
                        // permit anonymous
                        .requestMatchers("/login*").anonymous()
                        .requestMatchers("/register-user*").anonymous() // not the best way to hande that :D
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("login")
                        .passwordParameter("password")
                        .failureUrl("/login?error")
                        .defaultSuccessUrl("/home",true) //use wisely
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/home    ")
                )
                .exceptionHandling(accessDenied -> accessDenied
                        .accessDeniedPage("/access-denied")
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}