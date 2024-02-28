package it.trinakria.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private static final String RESOURCE_ACCESS_CLAIM = "resource_access";
    private static final String ROLES_CLAIM = "roles";
    public static final String ROLE_ADMIN = "admin";

    @Value("${backend.keycloak.client_id}")
    private String clientId;

    @Autowired
    private AppConfiguration configuration;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .cors(corsConfigurer -> {
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/**", configuration.getCors().toCorsConfiguration());
                    corsConfigurer.configurationSource(source);
                })
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/api/v1/user/new")
                                .permitAll()
                                .requestMatchers("/api/v1/user/all")
                                .hasRole(ROLE_ADMIN)
                                .requestMatchers(HttpMethod.DELETE)
                                .hasRole(ROLE_ADMIN)
                                .requestMatchers(HttpMethod.POST)
                                .hasRole(ROLE_ADMIN)
                                .requestMatchers(HttpMethod.PUT)
                                .hasRole(ROLE_ADMIN)
                                .anyRequest()
                                .authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)

                .oauth2ResourceServer(resourceServerConfigurer ->
                        resourceServerConfigurer
                                .jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(this::parseJwtAutheticationToken)
                                )
                )
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

    @SuppressWarnings("unchecked")
    private JwtAuthenticationToken parseJwtAutheticationToken(Jwt jwt) {

        Collection<String> rawRoles = new HashSet<>();

        if (jwt.hasClaim(RESOURCE_ACCESS_CLAIM)) {
            Map<String, Object> resourceMap = jwt.getClaimAsMap(RESOURCE_ACCESS_CLAIM);
            if (resourceMap.containsKey(clientId)) {
                Map<String, Object> roleMap = ((Map<String, Object>) resourceMap.get(clientId));
                if (roleMap.containsKey(ROLES_CLAIM)) {
                    rawRoles = (Collection<String>) roleMap.get(ROLES_CLAIM);
                }
            }
        }
        for (String s : rawRoles) {
            System.out.println(s);
        }
        Collection<GrantedAuthority> grantedAuthorities = rawRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        return new JwtAuthenticationToken(jwt, grantedAuthorities);

    }
}



