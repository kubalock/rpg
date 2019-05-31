import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { NavigationStart, ActivatedRoute, Router } from '@angular/router';
import { CharacterService } from './shared/character/character.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'client';
  isLoggedIn = false;
  isUser = false;
  isAdmin = false;

  gold: any;
  wood: any;
  iron: any;

  constructor(private router: Router,
  private route: ActivatedRoute,
  private characterService: CharacterService) {}

  ngOnInit() {
    if(sessionStorage.getItem('user_id') != null) {
      this.isLoggedIn = true;
    } else {
      this.isLoggedIn = false;
    }
  }

  logout() {
          sessionStorage.clear();
          this.gotoHome();
  }

  getResources() {
    this.characterService.getCharacterResources(sessionStorage.getItem('char_id')).subscribe((result : any) => {
      this.gold = result.gold;
      this.wood = result.wood;
      this.iron = result.iron;
    });
  }

  gotoHome() {
    if(sessionStorage.getItem('user_id') != null) {
      this.isLoggedIn = true;
      this.router.navigate(['/dashboard']);
    } else {
      this.isLoggedIn = false;
      this.router.navigate(['/index']);
    }
  }
}
