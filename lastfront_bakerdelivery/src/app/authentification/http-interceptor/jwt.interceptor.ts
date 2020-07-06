
import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';


/*va récuperer toutes les requetes qui partent du http client, on va verifier dans notre sessionStorage
si on a le token, si c'est le cas, on clone la req http et on lui ajoute le Bearer avec accessToken
rattraper la req avant qu'elle parte pour lui coller une etiquette du token*/
@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    // add authorization header with jwt token if available
    const accessToken = sessionStorage.getItem(environment.accessToken);

    if (accessToken) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${accessToken}`
        }
      });
    }
    return next.handle(request);
  }
}
