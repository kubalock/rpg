import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CharacterService } from '../shared/character/character.service';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-character-search',
  templateUrl: './character-search.component.html',
  styleUrls: ['./character-search.component.css']
})
export class CharacterSearchComponent implements OnInit {

  sub: Subscription;

  id: string;
  hero: string;

  heroes: Array<any>;

  constructor(private characterService: CharacterService,
              private router: Router,
              private appComponent: AppComponent) { }

  ngOnInit() {
    if(sessionStorage.getItem('hero') != null) {
    this.hero = sessionStorage.getItem('hero');
    this.characterService.getAllHeroes(this.hero).subscribe((heroes: any) => {
      this.heroes = heroes;
    });
    this.appComponent.getResources();
  } else {
    this.router.navigate(['/character']);
  }
}

}
