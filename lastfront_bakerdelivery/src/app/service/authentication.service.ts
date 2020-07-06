import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {BehaviorSubject} from 'rxjs';
import {User} from '../model/user';
import {environment} from '../../environments/environment';
import {JsonWebToken} from '../model/jsonWebToken';
import * as jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  userRoles: BehaviorSubject<string[]> = new BehaviorSubject([]);

  constructor(private httpClient: HttpClient, private router: Router) {
    this.getUserRoles();
  }

  public get loggedIn(): boolean {
    return sessionStorage.getItem(environment.accessToken) !== null;
  }

  logIn(user: User) {
    /*on recupere le token qui nous arrive du back
    * on le range dans le sessionStorage
    * petite fct utilitaire getUserRoles pour recup les roles de l'utilisateur
    * et je retourne à la racine avec le router*/
    this.httpClient.post<JsonWebToken>('http://localhost:8080/api/' + 'users/sign-in', user).subscribe(
      token => {
        sessionStorage.setItem(environment.accessToken, token.token);
        this.getUserRoles();
        this.router.navigate(['']);
        alert('Bienvenue');
      },
      error => alert('Login ou mot de passe éronné'));
    /*A faire : pop up erreur d'authent'*/

    /*à ce niveau là le token est dans le session Storage, il faudra le renvoyer à chaque requete qui part du front
    * gràce au jwtInterceptor*/
  }


  logOut() {
    sessionStorage.removeItem(environment.accessToken);
    this.userRoles.next([]);
    this.router.navigate(['login']);
  }

  private getUserRoles() {
        if (sessionStorage.getItem(environment.accessToken)) {
          const decodedToken = jwt_decode(sessionStorage.getItem(environment.accessToken));
      const authorities: Array<any> = decodedToken.auth;
      this.userRoles.next(authorities.map(authority => authority.authority));
    }
  }



}
