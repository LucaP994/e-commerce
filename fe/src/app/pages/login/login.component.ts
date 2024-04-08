import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../../service/login.service';
import { FormControl } from '@angular/forms';
import { AccessUser, LoggedUser } from 'src/app/models/user.model';
import { UserService } from 'src/app/service/user.service';
import { MatDialogRef } from '@angular/material/dialog';
import { AppContextService } from 'src/app/service/app-context.service';
import { Buffer } from 'buffer';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  username = new FormControl();
  password = new FormControl();

  constructor(
    private router: Router,
    private userService: UserService,
    public dialogRef: MatDialogRef<LoginComponent>,
    private context: AppContextService
  ) { }

  ngAfterViewInit() {
  }
  login() {
    let access: AccessUser = new AccessUser();
    access.username = this.username.value;
    access.password = this.password.value;
    this.userService.logIn(access).subscribe({
      next: (res) => {
        console.log(res);
        let loggedUser: LoggedUser = new LoggedUser();
        sessionStorage.setItem("kc_access_info", JSON.stringify(res));
        let base64Url = res.access_token.split('.')[1];
        let base64 = base64Url.replace('-', '+').replace('_', '/');
        let decodedData = JSON.parse(Buffer.from(base64, 'base64').toString('binary'));
        let date = new Date().getTime();
        let def = (decodedData.exp * 1000) - date;
        loggedUser.name = decodedData.name;
        loggedUser.email = decodedData.email;
        loggedUser.username = decodedData.preferred_username;
        loggedUser.userRoles = decodedData.resource_access.ecommerce_kc.roles;
        loggedUser.isLogged = true;
        if (loggedUser.userRoles.includes("admin")) {
          loggedUser.isAdmin = true;
        } else {
          loggedUser.isAdmin = false;
        }
        sessionStorage.setItem('user', JSON.stringify(loggedUser))
        console.log(`Current Date: ${new Date(date)}`);
        console.log(`Def: ${def}`);

        this.dialogRef.close();
        this.router.navigateByUrl('/')
        this.context.onLoadedSub();
      }
    });
  }
}
