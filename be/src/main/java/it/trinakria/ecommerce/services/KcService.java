package it.trinakria.ecommerce.services;

import it.trinakria.ecommerce.config.Credentials;
import it.trinakria.ecommerce.config.KeycloakConfig;
import it.trinakria.ecommerce.entities.KcUser;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class KcService {

    @Autowired
    KeycloakConfig keycloakConfig;
    @Value("${backend.keycloak.realm}")
    private String realm;

    public void addUser(KcUser userDTO){
        CredentialRepresentation credential = Credentials
                .createPasswordCredentials(userDTO.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmailId());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);

        UsersResource instance = getInstance();
        instance.create(user);
    }



    private UsersResource getInstance(){
        return keycloakConfig.getInstance().realm(realm).users();
    }
}
