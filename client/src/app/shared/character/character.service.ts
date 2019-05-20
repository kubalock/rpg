import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CharacterService {
  public API = '//localhost:8080';
  public HEROES_API = this.API + '/heroes/';

  constructor(private http: HttpClient) { }

  getHeroName(username: String) {
    return this.http.get(this.API + '/hero/' + username);
  }

  newHero(heroname: String) {
    return this.http.post(this.API + '/newHero/' + heroname, heroname);
  }

  updateHero(hero: any) {
    return this.http.put(this.HEROES_API + hero.char_id, hero);
  }

  getHero(id: String) {
    return this.http.get(this.HEROES_API + id);
  }

  getAllHeroes(hero: String) {
    return this.http.get(this.API + '/getAllHeroes/' + hero);
  }

  fightHero(my_id: String, it_id: String) {
    return this.http.get(this.API + '/fight/' + my_id + '/' + it_id);
  }
}
