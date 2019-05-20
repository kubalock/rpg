import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { UserService } from '../shared/user/user.service';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

    user: any = {};

  constructor(private userService: UserService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    if(sessionStorage.getItem('user_id') != null) {
      this.router.navigate(['/dashboard']);
    }
  }

  register(form: NgForm) {
    this.userService.getUserEmail(form.value.email).subscribe((user: any) => {
      if(user != null) {
        console.log("Email taken");
      }
      else {
        this.userService.getUsername(form.value.username).subscribe((user: any) => {
          if(user != null) {
            console.log("Username taken");
          }
          else {
            this.user.username = form.value.username;
            this.user.password = form.value.password;
            this.user.email = form.value.email;
            this.userService.registerUser(this.user).subscribe();
          }
        });
      }
    });
  }

}
