package it.trinakria.ecommerce.config;

import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KeycloakConfig {

    static Keycloak keycloak = null;
    @Value("${backend.keycloak.url}")
    private String serverUrl;
    @Value("${backend.keycloak.realm}")
    private String realm;
    @Value("${backend.keycloak.client_id}")
    private String clientId;
    @Value("${backend.keycloak.client_secret}")
    private String clientSecret;
    @Value("${backend.keycloak.username}")
    private String userName;
    @Value("${backend.keycloak.password}")
    private String password;

    public KeycloakConfig() {
    }
    public Keycloak getInstance(){
        if(keycloak == null){

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(userName)
                    .password(password)
                    .clientId(clientId)
                    .resteasyClient(new ResteasyClientBuilder()
                            .connectionPoolSize(10)
                            .build())
                    .build();
        }
        return keycloak;
    }
}
