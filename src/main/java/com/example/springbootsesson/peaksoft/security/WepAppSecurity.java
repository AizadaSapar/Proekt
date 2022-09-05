package com.example.springbootsesson.peaksoft.security;

import com.example.springbootsesson.peaksoft.model.User;
import com.example.springbootsesson.peaksoft.repasitory.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WepAppSecurity {

    private final UserRepository userRepository;

    public WepAppSecurity(UserRepository userRepository, UserRepository userRepository1) {

        this.userRepository = userRepository1;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider =
                new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(email -> {
            User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                    email + "not fount!"));
            return new AuthInfo(user);
        });
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    //@Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(authorize->authorize
//                .antMatchers("/companies/allcompanies").permitAll()
//                .antMatchers("/companies/new").hasAnyAuthority("ADMIN")
//                .antMatchers(HttpMethod.POST,"/companies/saveCompany").hasAnyAuthority("ADMIN")
//                .antMatchers("/companies/editCompany/**").hasAnyAuthority())
//}
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/companies/allCompanies").permitAll()
                        .antMatchers("/companies/new").hasAuthority("ADMIN")
                        .antMatchers(HttpMethod.POST, "/companies/saveCompany").hasAuthority("ADMIN")
                        .antMatchers("/companies/editCompany/**").hasAnyAuthority("ADMIN", "INSTRUCTOR")
                        .antMatchers(HttpMethod.PATCH, "/companies/updateCompany/**").hasAnyAuthority("ADMIN", "INSTRUCTOR")
                        .antMatchers(HttpMethod.DELETE, "/companies/deleteCompany/**/").hasAnyAuthority("ADMIN", "INSTRUCTOR")
                        .anyRequest().authenticated())
                .formLogin().
                defaultSuccessUrl("/companies/allCompanies")
                .permitAll();
        return http.build();

    }
}
