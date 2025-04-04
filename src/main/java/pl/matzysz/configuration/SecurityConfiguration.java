package pl.matzysz.configuration;

import jakarta.annotation.Resource;
import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorizationDecision;
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
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        // permit ALL
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/resources/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/home**").permitAll()
                        .requestMatchers("/access-denied").permitAll()
                        .requestMatchers("/logout**").permitAll()
                        .requestMatchers("/register-user/activate/**").permitAll()
                        .requestMatchers("/api/flights**").permitAll()
                        // permit IF USER
                        .requestMatchers("/personal-data**").hasAnyRole("USER")
                        .requestMatchers("/address**").hasAnyRole("USER")
                        .requestMatchers("/tickets**").hasAnyRole("USER")
                        .requestMatchers("/force-logout**").hasAnyRole("USER")
                        // permit IF USER and not NOT_VERIFIED
                        .requestMatchers("/register-company**").access((authentication, context) -> {
                            var auth = authentication.get();
                            boolean isUser = auth.getAuthorities().stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_USER"));
                            boolean isNotVerified = auth.getAuthorities().stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_NOT_VERIFIED"));

                            return new AuthorizationDecision(isUser && !isNotVerified);
                        })
                        // permit IF PROPRIETOR
                        .requestMatchers("/verifications/your-verifications*").hasAnyRole("PROPRIETOR")
                        // permit IF PROPRIETOR and VERIFIED
                        .requestMatchers("/fleet**",     "/flights**").access((authentication, context) -> {
                            var auth = authentication.get();
                            boolean isProprietor = auth.getAuthorities().stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_PROPRIETOR"));
                            boolean isVerified = auth.getAuthorities().stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_VERIFIED"));

                            return new AuthorizationDecision(isProprietor && isVerified);
                        })
                        // permit IF SUPPORT
                        .requestMatchers("/verifications/verification-panel**").hasAnyRole("SUPPORT")
                        // permit anonymous
                        .requestMatchers("/register-user**").anonymous()
                        .requestMatchers("/login*").anonymous()
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
                        .logoutSuccessUrl("/home")
                )
                .exceptionHandling(accessDenied -> accessDenied
                        .accessDeniedPage("/access-denied")
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}