import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CharacterService } from '../shared/character/character.service';
import { UserService } from '../shared/user/user.service';
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

  isFight: false;

  fightResult: Array<any>;

  hero: any = {};

  constructor(private characterService: CharacterService,
              private router: Router,
              private route: ActivatedRoute,
              private userService: UserService,
              private appComponent: AppComponent) { }

  ngOnInit() {
    this.user_id = sessionStorage.getItem('user_id');
    this.userService.getUsername(sessionStorage.getItem('username')).subscribe((result: any) => {
    this.my_id = result.hero.char_id;
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
    if(this.hero.strength_percent > 0) {
      return this.hero.strength + (Math.round((this.hero.strength_percent/100) * this.hero.strength));
    } else {
      return this.hero.strength;
    }
  }

  calcMinDmg() {
    let damage = 0;
    if(this.hero.dmg_percent > 0) {
        damage = this.hero.min_damage + ((this.hero.dmg_percent/100) * this.hero.min_damage);
        return (damage + this.calcStrength()).toFixed();
    } else {
      return (this.hero.min_damage + this.calcStrength()).toFixed();
    }
  }

  calcMaxDmg() {
    let damage = 0;
    if(this.hero.dmg_percent > 0) {
      damage = this.hero.max_damage + ((this.hero.dmg_percent/100) * this.hero.max_damage);
      return (damage + this.calcStrength()).toFixed();
    } else {
      return (this.hero.max_damage + this.calcStrength()).toFixed();
    }
  }

  calcHP() {
    if(this.hero.hp_percent > 0) {
      return (this.hero.health + ((this.hero.hp_percent/100) * this.hero.health)).toFixed();
    } else {
      return this.hero.health;
    }
  }

  calcAgility() {
      if(this.hero.agility_percent > 0) {
        return (this.hero.agility + ((this.hero.agility_percent/100) * this.hero.agility)).toFixed();
      } else {
        return this.hero.agility;
      }
    }

  calcIntelligence() {
      if(this.hero.intelligence_percent > 0) {
        return (this.hero.intelligence + ((this.hero.intelligence_percent/100) * this.hero.intelligence)).toFixed();
      } else {
        return this.hero.intelligence;
      }
    }

  calcMinFire() {
      if(this.hero.fire_percent > 0) {
        return (this.hero.min_fire + Math.round(this.calcIntelligence() / 2) + ((this.hero.fire_percent/100) * this.hero.min_fire)).toFixed();
      } else {
        if(this.hero.min_fire > 0) {
        return this.hero.min_fire + Math.round(this.calcIntelligence() / 2);
      }
      }
    }

  calcMaxFire() {
      if(this.hero.fire_percent > 0) {
        return (this.hero.max_fire + Math.round(this.calcIntelligence() / 2) + ((this.hero.fire_percent/100) * this.hero.max_fire)).toFixed();
      } else {
        if(this.hero.max_fire > 0) {
        return this.hero.max_fire + Math.round(this.calcIntelligence() / 2);
      }
      }
    }

    calcMinCold() {
        if(this.hero.cold_percent > 0) {
          return (this.hero.min_cold + Math.round(this.calcIntelligence() / 2) + ((this.hero.cold_percent/100) * this.hero.min_cold)).toFixed();
        } else {
          if(this.hero.min_cold > 0) {
          return this.hero.min_cold + Math.round(this.calcIntelligence() / 2);
        }
        }
      }

    calcMaxCold() {
        if(this.hero.cold_percent > 0) {
          return (this.hero.max_cold + Math.round(this.calcIntelligence() / 2) + ((this.hero.cold_percent/100) * this.hero.max_cold)).toFixed();
        } else {
          if(this.hero.max_cold > 0) {
          return this.hero.max_cold + Math.round(this.calcIntelligence() / 2);
        }
        }
      }

      calcMinElectric() {
          if(this.hero.electric_percent > 0) {
            return (this.hero.min_electric + Math.round(this.calcIntelligence() / 2) + ((this.hero.electric_percent/100) * this.hero.min_electric)).toFixed();
          } else {
            if(this.hero.min_electric > 0) {
            return this.hero.min_electric + Math.round(this.calcIntelligence() / 2);
          }
          }
        }

      calcMaxElectric() {
          if(this.hero.electric_percent > 0) {
            return (this.hero.max_electric + Math.round(this.calcIntelligence() / 2) + ((this.hero.electric_percent/100) * this.hero.max_electric)).toFixed();
          } else {
            if(this.hero.max_electric > 0) {
            return this.hero.max_electric + Math.round(this.calcIntelligence() / 2);
          }
          }
        }

        calcMinBleed() {
            if(this.hero.bleed_percent > 0) {
              return (this.hero.min_bleed + ((this.hero.bleed_percent/100) * this.hero.min_bleed)).toFixed();
            } else {
              return this.hero.min_bleed;
            }
          }

        calcMaxBleed() {
            if(this.hero.bleed_percent > 0) {
              return (this.hero.max_bleed + ((this.hero.bleed_percent/100) * this.hero.max_bleed)).toFixed();
            } else {
              return this.hero.max_bleed;
            }
          }

          calcMinPoison() {
              if(this.hero.poison_percent > 0) {
                return (this.hero.min_poison + ((this.hero.fire_percent/100) * this.hero.min_poison)).toFixed();
              } else {
                return this.hero.min_poison;
              }
            }

          calcMaxPoison() {
              if(this.hero.fire_percent > 0) {
                return (this.hero.max_poison + ((this.hero.fire_percent/100) * this.hero.max_poison)).toFixed();
              } else {
                return this.hero.max_poison;
              }
            }

  calcEndurance() {
      if(this.hero.endurance_percent > 0) {
        return (this.hero.endurance + ((this.hero.endurance_percent/100) * this.hero.endurance)).toFixed();
      } else {
        return this.hero.endurance;
      }
    }

  calcOffAbility() {
    if(this.hero.off_ability_percent > 0) {
        return (this.hero.off_ability + this.calcAgility()) + ((this.hero.off_ability_percent/100) * this.hero.off_ability);
    } else {
    return this.hero.off_ability + this.calcAgility();
  }
    }

  calcDefAbility() {
      if(this.hero.def_ability_percent > 0) {
          return (this.hero.def_ability + this.calcAgility()) + ((this.hero.def_ability_percent/100) * this.hero.def_ability);
      } else {
      return this.hero.def_ability + this.calcAgility();
    }
    }

  calcHealthRegen() {
    return this.hero.health_regen + Math.round((this.calcEndurance()/2));
  }

  calcDefense() {
    return this.hero.defense + Math.round((this.calcEndurance()/2));
  }
}
