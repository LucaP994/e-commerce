import { DeferBlockDepsEmitMode } from '@angular/compiler';
import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AccessUser, LoggedUser, User } from 'src/app/models/user.model';
import { LoginService } from 'src/app/service/login.service';
import { UserService } from 'src/app/service/user.service';
import { Buffer } from 'buffer';
import { MatDialogRef } from '@angular/material/dialog';
import { AppContextService } from 'src/app/service/app-context.service';
@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.scss'
})
export class SigninComponent {
  user: User = new User();
  firstname = new FormControl();
  lastname = new FormControl();
  username = new FormControl();
  email = new FormControl();
  password = new FormControl();
  dateOfBirth = new FormControl();
  private loggedUser: LoggedUser;
  constructor(
    private userService: UserService,
    private router: Router,
    private loginService: LoginService,
    public dialogRef: MatDialogRef<SigninComponent>,
    private context: AppContextService

  ) { }
  ngOnInit() {

  }
  save() {
    console.log(this.dateOfBirth.value)
    this.user.firstname = this.firstname.value;
    this.user.lastname = this.lastname.value;
    this.user.email = this.email.value;
    this.user.password = this.password.value;
    this.user.username = this.username.value;
    this.user.role = "USER";
    this.user.dateOfBirthStr = this.dateOfBirth.value + " 12:00:00.000";
    console.log(this.user)
    this.userService.saveUser(this.user).subscribe({
      next: (res) => {
        console.log(res);
        let access: AccessUser = new AccessUser();
        access.username = this.username.value;
        access.password = this.password.value;
        console.log(access);
        this.userService.logIn(access).subscribe({
          next: (res) => {
            console.log(res);
            this.loggedUser = new LoggedUser();
            sessionStorage.setItem("kc_access_info", JSON.stringify(res));
            let base64Url = res.access_token.split('.')[1];
            let base64 = base64Url.replace('-', '+').replace('_', '/');
            let decodedData = JSON.parse(Buffer.from(base64, 'base64').toString('binary'));
            let date = new Date().getTime();
            let def = (decodedData.exp * 1000) - date;
            this.loggedUser.name = decodedData.name;
            this.loggedUser.email = decodedData.email;
            this.loggedUser.username = decodedData.preferred_username;
            this.loggedUser.userRoles = decodedData.resource_access.ecommerce_kc.roles;
            this.loggedUser.isLogged = true;
            if (this.loggedUser.userRoles.includes("admin")) {
              this.loggedUser.isAdmin = true;
            } else {
              this.loggedUser.isAdmin = false;
            }
            sessionStorage.setItem('user', JSON.stringify(this.loggedUser))
            console.log(`Current Date: ${new Date(date)}`);
            console.log(`Def: ${def}`);

            this.dialogRef.close();
            this.router.navigateByUrl('/')
            this.context.onLoadedSub();
          }
        });
        //this.loginService.logIn()
      },
      error: (err) => {
        console.error(err);
      },
    })
  }
}
