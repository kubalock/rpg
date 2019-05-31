import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { UserService } from '../shared/user/user.service';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: {};
  isLoggedIn = false;

  constructor(private userService: UserService,
              private router: Router,
              private route: ActivatedRoute,
              private appComponent: AppComponent) { }

  ngOnInit() {
    if(sessionStorage.getItem('user_id') != null) {
      this.router.navigate(['/dashboard']);
    }
  }

  login(form: NgForm) {
      this.userService.getUsername(form.value.username).subscribe((user: any) => {
        if (user == null) {
         console.log("User Not Found");
       } else {
        if (user.password == form.value.password) {
          sessionStorage.setItem('username', user.username);
          sessionStorage.setItem('user_id', user.user_id);
          if(user.hero != null) {
          sessionStorage.setItem('char_id', user.hero.char_id);
          sessionStorage.setItem('hero', user.hero.char_name);
        }
          this.appComponent.gotoHome();
        } else {
          console.log("Wrong password");
        }
      }
      })
  }

}
