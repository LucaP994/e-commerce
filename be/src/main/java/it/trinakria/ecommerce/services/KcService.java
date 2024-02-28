package it.trinakria.ecommerce.services;

import it.trinakria.ecommerce.config.Credentials;
import it.trinakria.ecommerce.config.KeycloakConfig;
import it.trinakria.ecommerce.model.entities.KcUser;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class KcService {

    @Autowired
    KeycloakConfig keycloakConfig;
    @Value("${backend.keycloak.realm}")
    private String realm;

    public void addUser(KcUser userDTO){
        Map<String, List<String>> clientRoles = new HashMap<>();
        clientRoles.put("ecommerce_kc",userDTO.getRoles());
        CredentialRepresentation credential = Credentials
                .createPasswordCredentials(userDTO.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmailId());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);
        user.setRealmRoles(userDTO.getRoles());
        user.setClientRoles(clientRoles);
        UsersResource instance = getInstance();
        instance.create(user);
    }



    private UsersResource getInstance(){
        return keycloakConfig.getInstance().realm(realm).users();
    }
}
