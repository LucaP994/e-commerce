import { registerLocaleData } from '@angular/common';
import { Component } from '@angular/core';
import localeIt from '@angular/common/locales/it';
import { Router } from '@angular/router';
import { AppContextService } from './service/app-context.service';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'fe';

  
}
