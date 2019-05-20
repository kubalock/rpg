import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  public API = '//localhost:8080';
  public USERS_API = this.API + '/users/';

  constructor(private http: HttpClient) { }

  get(id: string) {
    return this.http.get(this.API + '/users/' + id);
  }

  getUserEmail(email: String) {
    return this.http.get(this.API + '/userEmail/' + email);
  }

  getUsername(username: String) {
    return this.http.get(this.API + '/user/' + username);
  }

  registerUser(user: any) {
    return this.http.post(this.API + '/newUser/' + user.username + '/'+ user.password + '/' + user.email, user);
  }

  assignHero(user: any) {
    return this.http.put(this.API + '/assignHero/' + user.user_id + '/' + user.hero, user);
  }
}
