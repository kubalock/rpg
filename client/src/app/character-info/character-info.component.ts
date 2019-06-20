import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { CharacterService } from '../shared/character/character.service';
import { UserService } from '../shared/user/user.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-character-info',
  templateUrl: './character-info.component.html',
  styleUrls: ['./character-info.component.css']
})
export class CharacterInfoComponent implements OnInit {

  charPoints = false;
  haveCharacter = false;
  submitPoints = false;
  enoughExp = false;

  coldDamage = false;
  fireDamage = false;
  electricDamage = false;
  bleedDamage = false;
  poisonDamage = false;

  user: any = {};
  hero: any = {};

  constructor(private characterService: CharacterService,
              private router: Router,
              private userService: UserService,
              private appComponent: AppComponent) { }

  ngOnInit() {
    if(sessionStorage.getItem('user_id') == 'undefined') {
      this.router.navigate(['/index']);
    }
    if(sessionStorage.getItem('hero') != null) {
      this.haveCharacter = true;
      this.characterService.getHero(sessionStorage.getItem('char_id')).subscribe((hero: any) => {
      this.hero = hero;

    if(this.hero.points > 0) {
      this.charPoints = true;
    }
    if(this.hero.experience >= this.hero.next_level) {
      this.enoughExp = true;
    }
    if(this.hero.min_cold > 0) {
      this.coldDamage = true;
    }
    if(this.hero.min_fire > 0) {
      this.fireDamage = true;
    }
    if(this.hero.min_electric > 0) {
      this.electricDamage = true;
    }
    if(this.hero.min_poison > 0) {
      this.poisonDamage = true;
    }
    if(this.hero.min_bleed > 0) {
      this.bleedDamage = true;
    }
    });
    this.appComponent.getResources();
  }
}

  createCharacter(form: NgForm) {
    this.characterService.getHeroName(form.value.name).subscribe((hero: any) => {
      if(hero != null) {
        console.log("Hero name is taken");
      } else {
        this.characterService.newHero(form.value.name).subscribe();
        sessionStorage.setItem('hero', form.value.name);
        this.assignHero(form);
      }
    });
  }

  assignHero(form: NgForm) {
    this.userService.get(sessionStorage.getItem('user_id')).subscribe((user: any) => {
      this.user = user;
      this.user.hero = form.value.name;
      this.user.user_id = sessionStorage.getItem('user_id');
      this.userService.assignHero(this.user).subscribe();
      this.router.navigate(['/dashboard']);
    });
  }

  addHealth() {
    this.hero.health = this.hero.health + 28;
    this.hero.points = this.hero.points - 1;
    if(this.hero.points == 0) {
      this.charPoints = false;
    }
    this.submitPoints = true;
  }

  addStrength() {
    this.hero.strength = this.hero.strength + 4;
    this.hero.points = this.hero.points - 1;
    if(this.hero.points == 0) {
      this.charPoints = false;
    }
    this.submitPoints = true;
  }

  addDexterity() {
    this.hero.agility = this.hero.agility + 4;
    this.hero.points = this.hero.points - 1;
    if(this.hero.points == 0) {
      this.charPoints = false;
    }
    this.submitPoints = true;
  }

  addIntelligence() {
    this.hero.intelligence = this.hero.intelligence + 4;
    this.hero.points = this.hero.points - 1;
    if(this.hero.points == 0) {
      this.charPoints = false;
    }
    this.submitPoints = true;
  }

  addEndurance() {
    this.hero.endurance = this.hero.endurance + 4;
    this.hero.points = this.hero.points - 1;
    if(this.hero.points == 0) {
      this.charPoints = false;
    }
    this.submitPoints = true;
  }

  enterPoints() {
    this.characterService.updateHero(this.hero).subscribe();
  }

  levelUp() {
    this.hero.level = this.hero.level + 1;
    this.hero.points = this.hero.points + 3;
    this.hero.experience = this.hero.experience - this.hero.next_level;
    this.hero.next_level = (this.hero.next_level * 1.3).toFixed();
    this.hero.mastery_points = this.hero.mastery_points + 3;
    this.characterService.updateHero(this.hero).subscribe();
    this.enoughExp = false;
    this.charPoints = true;
  }

  calcStrength() {
    return this.characterService.calcStrength(this.hero);
    }

  calcMinDmg() {
    return this.characterService.calcMinDmg(this.hero);
    }

  calcMaxDmg() {
    return this.characterService.calcMaxDmg(this.hero);
  }

  calcHP() {
    return this.characterService.calcHP(this.hero);
  }

  calcAgility() {
    return this.characterService.calcAgility(this.hero);
    }

  calcIntelligence() {
    return this.characterService.calcIntelligence(this.hero);
    }

  calcMinFire() {
    return this.characterService.calcMinFire(this.hero);
    }

  calcMaxFire() {
    return this.characterService.calcMaxFire(this.hero);
    }

  calcMinCold() {
    return this.characterService.calcMinCold(this.hero);
    }

  calcMaxCold() {
    return this.characterService.calcMaxCold(this.hero);
    }

  calcMinElectric() {
    return this.characterService.calcMinElectric(this.hero);
    }

  calcMaxElectric() {
    return this.characterService.calcMaxElectric(this.hero);
    }

  calcMinBleed() {
    return this.characterService.calcMinBleed(this.hero);
    }

  calcMaxBleed() {
    return this.characterService.calcMaxBleed(this.hero);
    }

  calcMinPoison() {
    return this.characterService.calcMinPoison(this.hero);
    }

  calcMaxPoison() {
    return this.characterService.calcMaxPoison(this.hero);
    }

  calcEndurance() {
    return this.characterService.calcEndurance(this.hero);
    }

  calcOffAbility() {
    return this.characterService.calcOffAbility(this.hero);
    }

  calcDefAbility() {
    return this.characterService.calcDefAbility(this.hero);
    }

  calcHealthRegen() {
    return this.characterService.calcHealthRegen(this.hero);
  }

  calcDefense() {
    return this.characterService.calcDefense(this.hero);
  }
  }
