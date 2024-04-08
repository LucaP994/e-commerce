import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { KeycloakService } from 'keycloak-angular';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(
    protected readonly keycloak: KeycloakService
  ) { }

  public logIn(){ 
    this.keycloak.login(
      {
        redirectUri: environment.redirectUri,
      }
    );
  }

  public logOut(){
    this.keycloak.logout(environment.redirectUri)
  }

  public checkLogin(){
    console.log(this.keycloak.getKeycloakInstance())
  }

}
