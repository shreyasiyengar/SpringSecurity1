package com.security.DemoSecuritybasic.config;

import com.security.DemoSecuritybasic.filter.CsrfCookieFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.openrewrite.java.spring.boot2.WebSecurityConfigurerAdapter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.sql.DataSource;

import java.util.Collection;
import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //applying the csrf  y generating token
        CsrfTokenRequestAttributeHandler requestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        requestAttributeHandler.setCsrfRequestAttributeName("_crwsf");


        //to apply the cors configure
        http.securityContext((context) -> context.requireExplicitSave(false))
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setMaxAge(3600l);
                return config;
            }
        })).csrf((csrf) -> csrf.csrfTokenRequestHandler(requestAttributeHandler).ignoringRequestMatchers("/contact","/register")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount", "/myBalance", "/myLoan", "/myCards","/user").authenticated()
                .requestMatchers("/notices", "/contact","/register","/error").permitAll());

        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();

//        Above is the custom security Configuration

        //this is to deny all the request
//        http.authorizeHttpRequests((requets)-> requets
//                .anyRequest().denyAll());
//        http.formLogin(withDefaults());
//        http.httpBasic(withDefaults());
//        return http.build();

        //this is permit all request
//        http.authorizeHttpRequests((requets) -> requets
//                .anyRequest().permitAll());
//        http.httpBasic(withDefaults());
//        http.formLogin(withDefaults());
//        return http.build();

    }



//        @Bean
//        public InMemoryUserDetailsManager userDetailsService(){

//            Approach Where we use WithDefaultPasswordEncoder() method
//            while creating the user deatils

//            UserDetails admin = User.withDefaultPasswordEncoder()
//                    .username("admin")
//                    .password("12345")
//                    .authorities("admin")
//                    .build();
//            UserDetails user = User.withDefaultPasswordEncoder()
//                    .username("shreyas")
//                    .password("12345")
//                    .authorities("read")
//                    .build();
//
//            return new InMemoryUserDetailsManager(admin,user);

            //Approach 2 where we use NoOpPasswordEncoder Bean
           //while creating the user details
//            UserDetails admin = User.withUsername("admin")
//                    .password("12345")
//                    .authorities("admin")
//                    .build();
//            UserDetails user = User.withUsername("hreyas")
//                    .password("12345")
//                    .authorities("read")
//                    .build();
//            return  new InMemoryUserDetailsManager(admin,user);
//    }

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
