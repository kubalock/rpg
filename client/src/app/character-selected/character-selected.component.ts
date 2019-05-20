import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CharacterService } from '../shared/character/character.service';
import { UserService } from '../shared/user/user.service';

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
              private userService: UserService) { }

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
      });
    });
  });
  }

  fight() {
    this.characterService.fightHero(this.my_id, this.id).subscribe((result: any) => {
      this.fightResult = result;
      this.isFight = true;
    });
  }

  calcStrength() {
    if(this.hero.strength_percent > 0) {
      return this.hero.strength + ((this.hero.strength_percent/100) * this.hero.strength);
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
        return (this.hero.min_fire + ((this.hero.fire_percent/100) * this.hero.min_fire)).toFixed();
      } else {
        return this.hero.min_fire;
      }
    }

  calcMaxFire() {
      if(this.hero.fire_percent > 0) {
        return (this.hero.max_fire + ((this.hero.fire_percent/100) * this.hero.max_fire)).toFixed();
      } else {
        return this.hero.max_fire;
      }
    }

    calcMinCold() {
        if(this.hero.cold_percent > 0) {
          return (this.hero.min_cold + ((this.hero.cold_percent/100) * this.hero.min_cold)).toFixed();
        } else {
          return this.hero.min_cold;
        }
      }

    calcMaxCold() {
        if(this.hero.cold_percent > 0) {
          return (this.hero.max_cold + ((this.hero.cold_percent/100) * this.hero.max_cold)).toFixed();
        } else {
          return this.hero.max_cold;
        }
      }

      calcMinElectric() {
          if(this.hero.electric_percent > 0) {
            return (this.hero.min_electric + ((this.hero.electric_percent/100) * this.hero.min_electric)).toFixed();
          } else {
            return this.hero.min_electric;
          }
        }

      calcMaxElectric() {
          if(this.hero.electric_percent > 0) {
            return (this.hero.max_electric + ((this.hero.electric_percent/100) * this.hero.max_electric)).toFixed();
          } else {
            return this.hero.max_electric;
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
    return this.hero.health_regen + (this.calcEndurance()/2);
  }

  calcDefense() {
    return this.hero.defense + (this.calcEndurance()/2);
  }
}
