import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { CharacterService } from '../shared/character/character.service';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-character-selected',
  templateUrl: './character-selected.component.html',
  styleUrls: ['./character-selected.component.css']
})
export class CharacterSelectedComponent implements OnInit {

  sub: Subscription;

  id: string;
  my_id: string;
  user_id: string;

  myself = false;

  coldDamage = false;
  fireDamage = false;
  electricDamage = false;
  bleedDamage = false;
  poisonDamage = false;

  isFight = false;

  fightResult: Array<any>;

  hero: any = {};

  constructor(private characterService: CharacterService,
              private route: ActivatedRoute,
              private appComponent: AppComponent) { }

  ngOnInit() {
    this.my_id = sessionStorage.getItem('char_id');
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
      this.characterService.getHero(this.id).subscribe(hero => {
        this.hero = hero;
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
        if(this.my_id == this.id) {
          this.myself = true;
        }
      });
    });

  this.appComponent.getResources();
  }

  fight() {
    this.characterService.fightHero(this.my_id, this.id).subscribe((result: any) => {
      this.fightResult = result;
      this.isFight = true;
    });
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
