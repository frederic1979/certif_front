import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {AuthenticationService} from '../../service/authentication.service';
import {User} from '../../model/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

  export class LoginComponent {



  loginForm = this.fb.group({
    username: [null, Validators.required],
    password: [null, Validators.compose([
      Validators.required, Validators.minLength(5), Validators.maxLength(255)])
    ]
  });

 /* Le constructor dans une classe Angular ça sert juste pour l'injection de dépendances
  Le constructor dit à Angular quelles dépendances il va avoir besoin*/
  constructor(private fb: FormBuilder, private loginService: AuthenticationService) {
  }


/*new user created, with form values and put in logIn method*/

  onSubmit() {
    const user = new User();
    user.username = this.loginForm.value.username;
    user.password = this.loginForm.value.password;
    this.loginService.logIn(user);
  }
}
