package ru.abzelilov.ikvs.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.abzelilov.ikvs.security.filter.JwtAuthenticationFilter;

import java.util.List;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static ru.abzelilov.ikvs.model.user.Permission.*;
import static ru.abzelilov.ikvs.model.user.Role.ADMIN;
import static ru.abzelilov.ikvs.model.user.Role.MANAGER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration  {

    private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/api/v1/bacteria/**",
            "/api/v1/bacteria",
            "/api/v1/bacteria/byId/{id}",
            "/api/v1/bacteria/filter",
            "/api/v1/bacteria/saveBacteria/**",
            "/api/v1/bacteria/saveBacteria",
            "/api/v1/general/search",

            "/api/v1/mushroom/**",
            "/api/v1/mushroom",
            "/api/v1/mushroom/saveMushroom",
            "/api/v1/mushroom/byId/{id}",
            "/api/v1/mushroom/filter",

            "/api/v1/seaweed/**",
            "/api/v1/seaweed",
            "/api/v1/seaweed/saveSeaweed",
            "/api/v1/seaweed/byId/{id}",
            "/api/v1/seaweed/filter",

            "/api/v1/archaea/**",
            "/api/v1/archaea",
            "/api/v1/archaea/saveArchaea",
            "/api/v1/archaea/byId/{id}",
            "/api/v1/archaea/filter",

            "/api/v1/simplest/**",
            "/api/v1/simplest",
            "/api/v1/simplest/byId/{id}",
            "/api/v1/simplest/filter",
            "/api/v1/simplest/saveSimplest",

            "/api/v1/dna/**",
            "/api/v1/dna",
            "/api/v1/dna/byId/{id}",
            "/api/v1/dna/filter",
            "/api/v1/bacteria",
            "/api/v1/bacteria/**",
            "/api/v1/bacteria/saveBacteria"


    };
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final LogoutHandler logoutHandler;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                // TODO: 23.01.2024 полностью выключить (пока хз как) метод OPTIONS либо засекьюрить
                                .requestMatchers(OPTIONS, "/**").permitAll()
                                .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                                .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
                                .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
                                .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
                                .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );

        return http.build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
//        return web -> web
//                        .ignoring()
//                        .requestMatchers(OPTIONS);
//    }

    @Bean
    public CorsConfigurationSource corsConfiguration() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.applyPermitDefaultValues();
        corsConfig.setAllowCredentials(true);
        corsConfig.addAllowedMethod("GET");
        corsConfig.addAllowedMethod("PATCH");
        corsConfig.addAllowedMethod("POST");
        corsConfig.addAllowedMethod("OPTIONS");
        corsConfig.setAllowedOrigins(List.of("*"));
        corsConfig.setAllowedHeaders(List.of("*"));
        corsConfig.setExposedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }
}
