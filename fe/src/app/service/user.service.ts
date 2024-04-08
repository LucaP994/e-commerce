import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AccessUser, User } from '../models/user.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { AccessResult } from '../models/accessResult.model';
import { KeycloakService } from 'keycloak-angular';
const userApi: string = environment.apiUrl+"/user/";
const authUrl: string = environment.authUrl;
const realm: string = environment.realm;
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient,
    private keycloakService: KeycloakService
  ) { }
  public logIn(user: AccessUser): Observable<AccessResult> {
    let body = new URLSearchParams();
    body.set('client_id', user.client_id);
    body.set('username', user.username);
    body.set('password', user.password);
    body.set('grant_type', user.grant_type);

    return this.http.post<AccessResult>(authUrl+"/realms/"+realm+"/protocol/openid-connect/token",body, {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
  }

  public getUserInfo(): Observable<any>{
    let token ="Bearer " + JSON.parse(sessionStorage.getItem('kc_access_info')).access_token;
    return this.http.get<any>(authUrl+"/realms/"+realm+"/protocol/openid-connect/userinfo",{headers: {'Authorization': token}})
  }

  public saveUser(user: User): Observable<User> {
   return this.http.post<User>(userApi+"new", user);
  }
}
