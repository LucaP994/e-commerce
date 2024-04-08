import { KeycloakService } from "keycloak-angular";
import { environment } from "src/environments/environment";

export function initializeKeycloak(
  keycloak: KeycloakService
  ) {
    return () =>
      keycloak.init({
        config: {
          url: environment.authUrl,
          realm: environment.realm,
          clientId: environment.client_id
        },
        initOptions: {
            pkceMethod: 'S256', 
            redirectUri: environment.redirectUri,     
            checkLoginIframe: false
          }
      });
}