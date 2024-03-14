package it.trinakria.ecommerce.services;

import it.trinakria.ecommerce.config.Credentials;
import it.trinakria.ecommerce.config.KeycloakConfig;
import it.trinakria.ecommerce.model.entities.KcUser;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.*;

@Service
@Slf4j
public class KcService {

    @Autowired
    KeycloakConfig keycloakConfig;
    @Value("${backend.keycloak.realm}")
    private String realm;
    @Value("${backend.keycloak.client_id}")
    private String client;
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

        UsersResource userResource = getUserResources();
        userResource.create(user);
    }

    private RolesResource getRoleResources(){
        return keycloakConfig.getInstance().realm(realm).clients().get(client).roles();
    }

    private UsersResource getUserResources(){
        return keycloakConfig.getInstance().realm(realm).users();
    }

    public void setRole(List<String> roles, String email){
        UsersResource userResource = getUserResources();
        List<RoleRepresentation> kcRoles = new ArrayList<>();
        for(String s : roles) {
            RoleRepresentation newRole = getRoleResources().get(s).toRepresentation();
            kcRoles.add(newRole);
        }
        List<UserRepresentation> kcUser = userResource.list();
        for(UserRepresentation us : kcUser){
            System.out.println("KcUser: "+us.getId());
            if(us.getEmail() != null && us.getEmail().equals(email)){
                userResource.get(us.getId()).roles().clientLevel(client).add(kcRoles);
            }
        }
    }
}
