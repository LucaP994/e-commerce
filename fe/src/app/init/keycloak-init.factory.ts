import { KeycloakService } from "keycloak-angular";
import { environment } from "src/environments/environment.development";

export function initializeKeycloak(
  keycloak: KeycloakService
  ) {
    return () =>
      keycloak.init({
        config: {
          url: 'http://localhost:8085',
          realm: 'ecommerce',
          clientId: 'ecommerce_kc',
        },
        initOptions: {
    
            pkceMethod: 'S256', 
            // must match to the configured value in keycloak
            redirectUri: environment.redirectUri,     
            // this will solved the error 
            checkLoginIframe: false
          }
      });
}