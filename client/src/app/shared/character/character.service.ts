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

  getHeroGuild(heroname: String) {
    return this.http.get(this.API + '/getHeroGuild/' + heroname);
  }

  getGuildHeroes(id: String) {
    return this.http.get(this.API + '/getGuildHeroes/' + id);
  }

  getCharacterResources(id: String) {
    return this.http.get(this.API + '/getCharResources/' + id);
  }

  calcStrength(hero: any) {
    if(hero.strength_percent > 0) {
      return hero.strength + (Math.round((hero.strength_percent/100) * hero.strength));
    } else {
      return hero.strength;
    }
  }

  calcMinDmg(hero: any) {
      let damage = 0;
      if(hero.dmg_percent > 0) {
          damage = hero.min_damage + ((hero.dmg_percent/100) * hero.min_damage);
          return Math.round(damage) + this.calcStrength(hero);
      } else {
        return hero.min_damage + this.calcStrength(hero);
      }
    }

  calcMaxDmg(hero: any) {
    let damage = 0;
    if(hero.dmg_percent > 0) {
      damage = hero.max_damage + ((hero.dmg_percent/100) * hero.max_damage);
      return Math.round(damage) + this.calcStrength(hero);
    } else {
      return hero.max_damage + this.calcStrength(hero);
    }
  }

  calcHP(hero: any) {
    let hp = 0;
    if(hero.hp_percent > 0) {
      hp = hero.health + (hero.hp_percent/100) * hero.health;
      return Math.round(hp);
    } else {
      return hero.health;
    }
  }

  calcAgility(hero: any) {
    let agility = 0;
      if(hero.agility_percent > 0) {
        agility = hero.agility + (hero.agility_percent/100) * hero.agility;
        return Math.round(agility);
      } else {
        return hero.agility;
      }
    }

  calcIntelligence(hero: any) {
    let intelligence = 0;
      if(hero.intelligence_percent > 0) {
        intelligence = hero.intelligence + (hero.intelligence_percent/100) * hero.intelligence;
        return Math.round(intelligence);
      } else {
        return hero.intelligence;
      }
    }

  calcMinFire(hero: any) {
      if(hero.fire_percent > 0) {
        return (hero.min_fire + Math.round(this.calcIntelligence(hero) / 2) + Math.round(((hero.fire_percent/100) * hero.min_fire)));
      } else {
        if(hero.min_fire > 0) {
        return hero.min_fire + Math.round(this.calcIntelligence(hero) / 2);
      }
      }
    }

  calcMaxFire(hero: any) {
      if(hero.fire_percent > 0) {
        return (hero.max_fire + Math.round(this.calcIntelligence(hero) / 2) + Math.round(((hero.fire_percent/100) * hero.max_fire)));
      } else {
        if(hero.max_fire > 0) {
        return hero.max_fire + Math.round(this.calcIntelligence(hero) / 2);
      }
      }
    }

  calcMinCold(hero: any) {
        if(hero.cold_percent > 0) {
          return (hero.min_cold + Math.round(this.calcIntelligence(hero) / 2) + Math.round(((hero.cold_percent/100) * hero.min_cold)));
        } else {
          if(hero.min_cold > 0) {
          return hero.min_cold + Math.round(this.calcIntelligence(hero) / 2);
        }
        }
      }

  calcMaxCold(hero: any) {
        if(hero.cold_percent > 0) {
          return (hero.max_cold + Math.round(this.calcIntelligence(hero) / 2) + Math.round(((hero.cold_percent/100) * hero.max_cold)));
        } else {
          if(hero.max_cold > 0) {
          return hero.max_cold + Math.round(this.calcIntelligence(hero) / 2);
        }
        }
      }

  calcMinElectric(hero: any) {
          if(hero.electric_percent > 0) {
            return (hero.min_electric + Math.round(this.calcIntelligence(hero) / 2) + Math.round(((hero.electric_percent/100) * hero.min_electric)));
          } else {
            if(hero.min_electric > 0) {
            return hero.min_electric + Math.round(this.calcIntelligence(hero) / 2);
          }
          }
        }

  calcMaxElectric(hero: any) {
          if(hero.electric_percent > 0) {
            return (hero.max_electric + Math.round(this.calcIntelligence(hero) / 2) + Math.round(((hero.electric_percent/100) * hero.max_electric)));
          } else {
            if(hero.max_electric > 0) {
            return hero.max_electric + Math.round(this.calcIntelligence(hero) / 2);
          }
          }
        }

  calcMinBleed(hero: any) {
            if(hero.bleed_percent > 0) {
              return (hero.min_bleed + Math.round(((hero.bleed_percent/100) * hero.min_bleed)));
            } else {
              return hero.min_bleed;
            }
          }

  calcMaxBleed(hero: any) {
            if(hero.bleed_percent > 0) {
              return (hero.max_bleed + Math.round(((hero.bleed_percent/100) * hero.max_bleed)));
            } else {
              return hero.max_bleed;
            }
          }

  calcMinPoison(hero: any) {
              if(hero.poison_percent > 0) {
                return (hero.min_poison + Math.round(((hero.fire_percent/100) * hero.min_poison)));
              } else {
                return hero.min_poison;
              }
            }

  calcMaxPoison(hero: any) {
              if(hero.fire_percent > 0) {
                return (hero.max_poison + Math.round(((hero.fire_percent/100) * hero.max_poison)));
              } else {
                return hero.max_poison;
              }
            }

  calcEndurance(hero: any) {
      if(hero.endurance_percent > 0) {
        return (hero.endurance + Math.round(((hero.endurance_percent/100) * hero.endurance)));
      } else {
        return hero.endurance;
      }
    }

  calcOffAbility(hero: any) {
    let off = 0;
    if(hero.off_ability_percent > 0) {
        off = this.calcAgility(hero) + (hero.off_ability_percent/100 * hero.off_ability) + hero.off_ability;
        return Math.round(off);
    } else {
    return hero.off_ability + this.calcAgility(hero);
  }
    }

  calcDefAbility(hero: any) {
    let def = 0;
    if(hero.def_ability_percent > 0) {
        def = this.calcAgility(hero) + (hero.def_ability_percent/100 * hero.def_ability) + hero.def_ability;
        return Math.round(def);
      } else {
      return hero.def_ability + this.calcAgility(hero);
    }
    }

  calcHealthRegen(hero: any) {
    return hero.health_regen + Math.round((this.calcEndurance(hero)/2));
  }

  calcDefense(hero: any) {
    return hero.defense + Math.round((this.calcEndurance(hero)/2));
  }
}
