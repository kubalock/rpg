import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { CharacterService } from '../shared/character/character.service';
import { UserService } from '../shared/user/user.service';
import { MasteryService } from '../shared/mastery/mastery.service';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

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
              private route: ActivatedRoute,
              private userService: UserService,
              private masteryService: MasteryService) { }

  ngOnInit() {
    if(sessionStorage.getItem('user_id') == 'undefined') {
      this.router.navigate(['/index']);
    }
    if(sessionStorage.getItem('hero') != null) {
      this.haveCharacter = true;
      this.characterService.getHeroName(sessionStorage.getItem('hero')).subscribe((hero: any) => {
        this.hero = hero;
        console.log(this.hero);

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
    this.hero.health = this.hero.health + 20;
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
    this.router.navigate(['/dashboard']);
  }

  levelUp() {
    this.hero.level = this.hero.level + 1;
    this.hero.points = this.hero.points + 4;
    this.hero.experience = this.hero.experience - this.hero.next_level;
    this.hero.next_level = (this.hero.next_level * 1.2).toFixed();
    this.hero.mastery_points = this.hero.mastery_points + 3;
    this.characterService.updateHero(this.hero).subscribe();
    this.enoughExp = false;
    this.charPoints = true;
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
