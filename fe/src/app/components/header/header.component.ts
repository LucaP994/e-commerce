import { registerLocaleData } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';
import { AppContextService } from 'src/app/service/app-context.service';
import { LoginService } from 'src/app/service/login.service';
import { environment } from 'src/environments/environment.development';
import localeIt from '@angular/common/locales/it';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from 'src/app/pages/login/login.component';
import { SigninComponent } from 'src/app/pages/signin/signin.component';
import { LoggedUser } from 'src/app/models/user.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  public loggedIn: boolean = false;
  private open: boolean = false;
  public user: LoggedUser = new LoggedUser();
  constructor(
    private loginService: LoginService,
    private keycloakService: KeycloakService,
    private context: AppContextService,
    private router: Router,
    public dialog: MatDialog
  ){

  }

  ngAfterViewInit() {
    this.context.onLoaded.subscribe(res => {
      this.user = JSON.parse(sessionStorage.getItem('user'));
    })
    if(JSON.parse(sessionStorage.getItem('user')) != null)
      this.user = JSON.parse(sessionStorage.getItem('user'));
    //console.log(this.keycloakService.isLoggedIn())
      /*if (this.keycloakService.isLoggedIn()) {
        this.keycloakService.getKeycloakInstance().loadUserInfo().then((res: any) => {
          this.user.name = res.name;
          this.user.username = res.preferred_username;
          this.user.email = res.email;
          this.user.isLogged = true;
        }).finally(() => {
          this.user.userRoles = this.keycloakService.getUserRoles();
          if (this.user.userRoles.includes("admin")) {
            this.user.isAdmin = true;
          } else {
            this.user.isAdmin = false;
          }
          sessionStorage.setItem('user', JSON.stringify(this.user))
          this.router.navigateByUrl('/')
          this.context.onLoadedSub();
        });
      }*/
    registerLocaleData(localeIt, 'it-IT');
  }

  public openMenu() {
    // let menu: HTMLElement = document.querySelector('.side-menu');
    let menuBar1: HTMLElement = document.querySelector('.menu-btn div:nth-child(1)');
    let menuBar2: HTMLElement = document.querySelector('.menu-btn div:nth-child(2)');
    let menuBar3: HTMLElement = document.querySelector('.menu-btn div:nth-child(3)');
    // menu.style.transition = "all 0.3s";
    if (!this.open) {
      // menu.style.left = "0";
      menuBar1.style.transform = "rotate(45deg)"      
      menuBar1.style.top = "50%";
      menuBar2.style.width = "0%";
      menuBar3.style.transform = "rotate(-45deg)"      
      menuBar3.style.top = "50%";
      this.open = true;
    }else{
      // menu.style.left = "100%";
      menuBar1.style.transform = "rotate(0deg)"      
      menuBar1.style.top = "0";
      menuBar2.style.width = "100%";
      menuBar3.style.transform = "rotate(0deg)"      
      menuBar3.style.top = "100%";
      this.open = false;
    }
  }
  public logIn(){
    this.loginService.logIn();
  }
  public logOut(){
    this.loginService.logOut();
    sessionStorage.clear();
  }
  public navigate(uri: string){
    this.router.navigate([uri]);
  }
  openDialog(type: string): void {
    let dialogRef = null;
    if(type === "login"){
      dialogRef = this.dialog.open(LoginComponent, {
        height: '250px',
        width: '600px',
        data: {data: "test"},
      });
    }else{
      dialogRef = this.dialog.open(SigninComponent, {
        height: '400px',
        width: '600px',
        data: {data: "test"},
      });
    }    

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      //this.animal = result;
    });
  }
}
