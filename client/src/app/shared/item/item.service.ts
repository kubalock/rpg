import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ItemService {
public API = '//localhost:8080';
public USERS_ARMORY = this.API + '/getUsersItems/';
public EQUIPPED_ITEMS = this.API + '/getEquippedItems/';

  constructor(private http: HttpClient) { }

  getUserItems(id: String){
    return this.http.get(this.USERS_ARMORY + id);
  }

  getEquippedItems(id: String) {
    return this.http.get(this.EQUIPPED_ITEMS + id);
  }

  equipItem(id: String, char_id: String){
    return this.http.put(this.API + '/equipItem/' + id + '/' + char_id, id);
  }

  takeOffItem(id: String, char_id: String){
    return this.http.put(this.API + '/takeOffItem/' + id + '/' + char_id, id);
  }

  getItemToEnchance(char_id: String, shard_id: String) {
    return this.http.get(this.API + '/getItemToEnchance/' + char_id + '/' + shard_id);
  }

  sellItem(item_id: String) {
    return this.http.delete(this.API + '/sellItem/' + item_id);
  }

  showItemDetails(item: any, where: string, requestLevel: string) {
    let currentLevel: any = 0;
    let bonusMultiply: any = 1;
    let requirementMultiply: any = 1;

    let agility: any = 0;
    let agility_percent: any = 0;
    let armor: any = 0;
    let armor_percent: any = 0;
    let attack_speed: any = 0;
    let block_percent: any = 0;
    let block_damage: any = 0;
    let def_ability: any = 0;
    let def_ability_percent: any = 0;
    let damage: any = 0;
    let damage_bleed: any = 0;
    let damage_cold: any = 0;
    let damage_electric: any = 0;
    let damage_fire: any = 0;
    let damage_percent: any = 0;
    let damage_poison: any = 0;
    let dmg_percent_bleed: any = 0;
    let dmg_percent_cold: any = 0;
    let dmg_percent_electric: any = 0;
    let dmg_percent_fire: any = 0;
    let dmg_percent_poison: any = 0;
    let endurance_percent: any = 0;
    let health: any = 0;
    let health_percent: any = 0;
    let health_regen: any = 0;
    let intelligence: any = 0;
    let intelligence_percent: any = 0;
    let max_bleed: any = 0;
    let max_cold: any = 0;
    let max_damage: any = 0;
    let max_electric: any = 0;
    let max_fire: any = 0;
    let max_poison: any = 0;
    let min_bleed: any = 0;
    let min_cold: any = 0;
    let min_damage: any = 0;
    let min_electric: any = 0;
    let min_fire: any = 0;
    let min_poison: any = 0;
    let off_ability: any = 0;
    let off_ability_percent: any = 0;
    let res_bleed: any = 0;
    let res_cold: any = 0;
    let res_electric: any = 0;
    let res_fire: any = 0;
    let res_stun: any = 0;
    let res_poison: any = 0;
    let slow: any = 0;
    let strength: any = 0;
    let strength_percent: any = 0;
    let stun_chance: any = 0;
    let level: any = 0;

    currentLevel = item.level;
    if(currentLevel < requestLevel) {
      currentLevel = requestLevel;
    }
    if(currentLevel > 0) {
      for(let i = 0; i < currentLevel; i++) {
        bonusMultiply = bonusMultiply + 0.05;
        requirementMultiply = requirementMultiply + 0.025;
      }
    }

    if(item.prefix != null || item.prefix == 'undefined') {
      if (item.prefix.agility_percent != null) {
        agility_percent = Math.round(item.prefix.agility_percent * bonusMultiply);
      }
      if(item.prefix.armor != null) {
        armor = Math.round(item.prefix.armor * bonusMultiply);
      }
      if(item.prefix.armor_percent != null) {
        armor_percent = Math.round(item.prefix.armor_percent * bonusMultiply);
      }
      if(item.prefix.attack_speed != null) {
        attack_speed = Math.round(item.prefix.attack_speed * bonusMultiply);
      }
      if(item.prefix.block_percent != null) {
        block_percent = Math.round(item.prefix.block_percent * bonusMultiply);
      }
      if(item.prefix.damage_cold != null) {
        damage_cold = Math.round(item.prefix.damage_cold * bonusMultiply);
      }
      if(item.prefix.damage_electric != null) {
        damage_electric = Math.round(item.prefix.damage_electric * bonusMultiply);
      }
      if(item.prefix.damage_fire != null) {
        damage_fire = Math.round(item.prefix.damage_fire * bonusMultiply);
      }
      if(item.prefix.damage_percent != null) {
        damage_percent = Math.round(item.prefix.damage_percent * bonusMultiply);
      }
      if(item.prefix.def_ability_percent != null) {
        def_ability_percent = Math.round(item.prefix.def_ability_percent * bonusMultiply);
      }
      if(item.prefix.dmg_percent_bleed != null) {
        dmg_percent_bleed = Math.round(item.prefix.dmg_percent_bleed * bonusMultiply);
      }
      if(item.prefix.dmg_percent_cold != null) {
        dmg_percent_cold = Math.round(item.prefix.dmg_percent_cold * bonusMultiply);
      }
      if(item.prefix.dmg_percent_electric != null) {
        dmg_percent_electric = Math.round(item.prefix.dmg_percent_electric * bonusMultiply);
      }
      if(item.prefix.dmg_percent_fire != null) {
        dmg_percent_fire = Math.round(item.prefix.dmg_percent_fire * bonusMultiply);
      }
      if(item.prefix.health_percent != null) {
        health_percent = Math.round(item.prefix.health_percent * bonusMultiply);
      }
      if(item.prefix.health_regen != null) {
        health_regen = Math.round(item.prefix.health_regen * bonusMultiply);
      }
      if(item.prefix.intelligence_percent != null) {
        intelligence_percent = Math.round(item.prefix.intelligence_percent * bonusMultiply);
      }
      if(item.prefix.max_bleed != null) {
        max_bleed = Math.round(item.prefix.max_bleed * bonusMultiply);
      }
      if(item.prefix.max_cold != null) {
        max_cold = Math.round(item.prefix.max_cold * bonusMultiply);
      }
      if(item.prefix.max_damage != null) {
        max_damage = Math.round(item.prefix.max_damage * bonusMultiply);
      }
      if(item.prefix.max_electric != null) {
        max_electric = Math.round(item.prefix.max_electric * bonusMultiply);
      }
      if(item.prefix.max_fire != null) {
        max_fire = Math.round(item.prefix.max_fire * bonusMultiply);
      }
      if(item.prefix.max_poison != null) {
        max_poison = Math.round(item.prefix.max_poison * bonusMultiply);
      }
      if(item.prefix.min_bleed != null) {
        min_bleed = Math.round(item.prefix.min_bleed * bonusMultiply);
      }
      if(item.prefix.min_cold != null) {
        min_cold = Math.round(item.prefix.min_cold * bonusMultiply);
      }
      if(item.prefix.min_damage != null) {
        min_damage = Math.round(item.prefix.min_damage * bonusMultiply);
      }
      if(item.prefix.min_electric != null) {
        min_electric = Math.round(item.prefix.min_electric * bonusMultiply);
      }
      if(item.prefix.min_fire != null) {
        min_fire = Math.round(item.prefix.min_fire * bonusMultiply);
      }
      if(item.prefix.min_poison != null) {
        min_poison = Math.round(item.prefix.min_poison * bonusMultiply);
      }
      if(item.prefix.off_ability != null) {
        off_ability = Math.round(item.prefix.off_ability * bonusMultiply);
      }
      if(item.prefix.off_ability_percent != null) {
        off_ability_percent = Math.round(item.prefix.off_ability_percent * bonusMultiply);
      }
      if(item.prefix.res_bleed != null) {
        res_bleed = Math.round(item.prefix.res_bleed * bonusMultiply);
      }
      if(item.prefix.res_cold != null) {
        res_cold = Math.round(item.prefix.res_cold * bonusMultiply);
      }
      if(item.prefix.res_electric != null) {
        res_electric = Math.round(item.prefix.res_electric * bonusMultiply);
      }
      if(item.prefix.res_fire != null) {
        res_fire = Math.round(item.prefix.res_fire * bonusMultiply);
      }
      if(item.prefix.res_poison != null) {
        res_poison = Math.round(item.prefix.res_poison * bonusMultiply);
      }
      if(item.prefix.res_stun != null) {
        res_stun = Math.round(item.prefix.res_stun * bonusMultiply);
      }
      if(item.prefix.slow != null) {
        slow = Math.round(item.prefix.slow * bonusMultiply);
      }
      if(item.prefix.strength_percent != null) {
        strength_percent = Math.round(item.prefix.strength_percent * bonusMultiply);
      }
      if(item.prefix.strength != null) {
        strength = Math.round(item.prefix.strength * bonusMultiply);
      }
    }
    if(item.itemBase.armor != null) {
        armor = armor + Math.round(item.itemBase.armor * bonusMultiply);
      }
      if(item.itemBase.agility != null) {
        agility = agility + Math.round(item.itemBase.agility * bonusMultiply);
      }
      if(item.itemBase.agility_percent != null) {
        agility_percent = agility_percent + Math.round(item.itemBase.agility_percent * bonusMultiply);
      }
      if(item.itemBase.attack_speed != null) {
        attack_speed = attack_speed + Math.round(item.itemBase.attack_speed * bonusMultiply);
      }
      if(item.itemBase.block_chance != null) {
        block_percent = block_percent + Math.round(item.itemBase.block_chance * bonusMultiply);
      }
      if(item.itemBase.block_damage != null) {
        block_damage = block_damage + Math.round(item.itemBase.block_damage * bonusMultiply);
      }
      if(item.itemBase.def_ability != null) {
        def_ability = def_ability + Math.round(item.itemBase.def_ability * bonusMultiply);
      }
      if(item.itemBase.damage_bleed != null) {
        damage_bleed = damage_bleed + Math.round(item.itemBase.damage_bleed * bonusMultiply);
      }
      if(item.itemBase.damage_cold != null) {
        damage_cold = damage_cold + Math.round(item.itemBase.damage_cold * bonusMultiply);
      }
      if(item.itemBase.damage_electric != null) {
        damage_electric = damage_electric + Math.round(item.itemBase.damage_electric * bonusMultiply);
      }
      if(item.itemBase.damage_fire != null) {
        damage_fire = damage_fire + Math.round(item.itemBase.damage_fire * bonusMultiply);
      }
      if(item.itemBase.damage_poison != null) {
        damage_poison = damage_poison + Math.round(item.itemBase.damage_poison * bonusMultiply);
      }
      if(item.itemBase.dmg_percent != null) {
        damage_percent = damage_percent + Math.round(item.itemBase.dmg_percent * bonusMultiply);
      }
      if(item.itemBase.dmg_bleed_percent != null) {
        dmg_percent_bleed = dmg_percent_bleed + Math.round(item.itemBase.dmg_bleed_percent * bonusMultiply);
      }
      if(item.itemBase.dmg_cold_percent != null) {
        dmg_percent_cold = dmg_percent_cold + Math.round(item.itemBase.dmg_cold_percent * bonusMultiply);
      }
      if(item.itemBase.dmg_electric_percent != null) {
        dmg_percent_electric = dmg_percent_electric + Math.round(item.itemBase.dmg_electric_percent * bonusMultiply);
      }
      if(item.itemBase.dmg_fire_percent != null) {
        dmg_percent_fire = dmg_percent_fire + Math.round(item.itemBase.dmg_fire_percent * bonusMultiply);
      }
      if(item.itemBase.dmg_poison_percent != null) {
        dmg_percent_poison = dmg_percent_poison + Math.round(item.itemBase.dmg_poison_percent * bonusMultiply);
      }
      if(item.itemBase.endurance_percent != null) {
        endurance_percent = endurance_percent + Math.round(item.itemBase.endurance_percent * bonusMultiply);
      }
      if(item.itemBase.health != null) {
        health = health + Math.round(item.itemBase.health * bonusMultiply);
      }
      if(item.itemBase.health_percent != null) {
        health_percent = health_percent + Math.round(item.itemBase.health_percent * bonusMultiply);
      }
      if(item.itemBase.health_regen != null) {
        health_regen = health_regen + Math.round(item.itemBase.health_regen * bonusMultiply);
      }
      if(item.itemBase.intelligence != null) {
        intelligence = intelligence + Math.round(item.itemBase.intelligence * bonusMultiply);
      }
      if(item.itemBase.intelligence_percent != null) {
        intelligence_percent = intelligence_percent + Math.round(item.itemBase.intelligence_percent * bonusMultiply);
      }
      if(item.itemBase.min_damage != null) {
        min_damage = min_damage + Math.round(item.itemBase.min_damage * bonusMultiply);
      }
      if(item.itemBase.max_damage != null) {
        max_damage = max_damage + Math.round(item.itemBase.max_damage * bonusMultiply);
      }
      if(item.itemBase.min_cold != null) {
        min_cold = min_cold + Math.round(item.itemBase.min_cold * bonusMultiply);
      }
      if(item.itemBase.max_cold != null) {
        max_cold = max_cold + Math.round(item.itemBase.max_cold * bonusMultiply);
      }
      if(item.itemBase.min_fire != null) {
        min_fire = min_fire + Math.round(item.itemBase.min_fire * bonusMultiply);
      }
      if(item.itemBase.max_fire != null) {
        max_fire = max_fire + Math.round(item.itemBase.max_fire * bonusMultiply);
      }
      if(item.itemBase.min_electric != null) {
        min_electric = min_electric + Math.round(item.itemBase.min_electric * bonusMultiply);
      }
      if(item.itemBase.max_electric != null) {
        max_electric = max_electric + Math.round(item.itemBase.max_electric * bonusMultiply);
      }
      if(item.itemBase.off_ability != null) {
        off_ability = off_ability + Math.round(item.itemBase.off_ability * bonusMultiply);
      }
      if(item.itemBase.res_bleed != null) {
        res_bleed = res_bleed + Math.round(item.itemBase.res_bleed * bonusMultiply);
      }
      if(item.itemBase.res_cold != null) {
        res_cold = res_cold + Math.round(item.itemBase.res_cold * bonusMultiply);
      }
      if(item.itemBase.res_electric != null) {
        res_electric = res_electric + Math.round(item.itemBase.res_electric * bonusMultiply);
      }
      if(item.itemBase.res_fire != null) {
        res_fire = res_fire + Math.round(item.itemBase.res_fire * bonusMultiply);
      }
      if(item.itemBase.res_poison != null) {
        res_poison = res_poison + Math.round(item.itemBase.res_poison * bonusMultiply);
      }
      if(item.itemBase.res_stun != null) {
        res_stun = res_stun + Math.round(item.itemBase.res_stun * bonusMultiply);
      }
      if(item.itemBase.strength != null) {
        strength = strength + Math.round(item.itemBase.strength * bonusMultiply);
      }
      if(item.itemBase.strength_percent != null) {
        strength_percent = strength_percent + Math.round(item.itemBase.strength_percent * bonusMultiply);
      }
      if(item.itemBase.stun_chance != null) {
        stun_chance = stun_chance + Math.round(item.itemBase.stun_chance * bonusMultiply);
      }
    if(item.suffix != null || item.prefix == 'undefined') {
      if(item.suffix.agility_percent != null) {
          agility_percent = agility_percent + Math.round(item.suffix.agility_percent * bonusMultiply);
        }
        if(item.suffix.agility != null) {
            agility = agility + Math.round(item.suffix.agility * bonusMultiply);
          }
        if(item.suffix.armor_percent != null) {
          armor_percent = armor_percent + Math.round(item.suffix.armor_percent * bonusMultiply);
        }
        if(item.suffix.attack_speed != null) {
          attack_speed = attack_speed + Math.round(item.suffix.attack_speed * bonusMultiply);
        }
        if(item.suffix.damage != null) {
          damage = damage + Math.round(item.suffix.damage * bonusMultiply);
        }
        if(item.suffix.damage_cold != null) {
          damage_cold = damage_cold + Math.round(item.suffix.damage_cold * bonusMultiply);
        }
        if(item.suffix.damage_electric != null) {
          damage_electric = damage_electric + Math.round(item.suffix.damage_electric * bonusMultiply);
        }
        if(item.suffix.damage_fire != null) {
          damage_fire = damage_fire + Math.round(item.suffix.damage_fire * bonusMultiply);
        }
        if(item.suffix.damage_percent != null) {
          damage_percent = damage_percent + Math.round(item.suffix.damage_percent * bonusMultiply);
        }
        if(item.suffix.dmg_percent_bleed != null) {
          dmg_percent_bleed = dmg_percent_bleed + Math.round(item.suffix.dmg_percent_bleed * bonusMultiply);
        }
        if(item.suffix.dmg_percent_cold != null) {
          dmg_percent_cold = dmg_percent_cold + Math.round(item.suffix.dmg_percent_cold * bonusMultiply);
        }
        if(item.suffix.dmg_percent_electric != null) {
          dmg_percent_electric = dmg_percent_electric + Math.round(item.suffix.dmg_percent_electric * bonusMultiply);
        }
        if(item.suffix.dmg_percent_fire != null) {
          dmg_percent_fire = dmg_percent_fire + Math.round(item.suffix.dmg_percent_fire * bonusMultiply);
        }
        if(item.suffix.dmg_percent_poison != null) {
          dmg_percent_poison = dmg_percent_poison + Math.round(item.suffix.dmg_percent_poison * bonusMultiply);
        }
        if(item.suffix.def_ability != null) {
          def_ability = def_ability + Math.round(item.suffix.def_ability * bonusMultiply);
        }
        if(item.suffix.health != null) {
          health = health + Math.round(item.suffix.health * bonusMultiply);
        }
        if(item.suffix.health_percent != null) {
          health_percent = health_percent + Math.round(item.suffix.health_percent * bonusMultiply);
        }
        if(item.suffix.health_regen != null) {
          health_regen = health_regen + Math.round(item.suffix.health_regen * bonusMultiply);
        }
        if(item.suffix.intelligence != null) {
          intelligence = intelligence + Math.round(item.suffix.intelligence * bonusMultiply);
        }
        if(item.suffix.intelligence_percent != null) {
          intelligence_percent = intelligence_percent + Math.round(item.suffix.intelligence_percent * bonusMultiply);
        }
        if(item.suffix.off_ability != null) {
          off_ability = off_ability + Math.round(item.suffix.off_ability * bonusMultiply);
        }
        if(item.suffix.res_cold != null) {
          res_cold = res_cold + Math.round(item.suffix.res_cold * bonusMultiply);
        }
        if(item.suffix.res_electric != null) {
          res_electric = res_electric + Math.round(item.suffix.res_electric * bonusMultiply);
        }
        if(item.suffix.res_fire != null) {
          res_fire = res_fire + Math.round(item.suffix.res_fire * bonusMultiply);
        }
        if(item.suffix.res_stun != null) {
          res_stun = res_stun + Math.round(item.suffix.res_stun * bonusMultiply);
        }
        if(item.suffix.slow != null) {
          slow = slow + Math.round(item.suffix.slow * bonusMultiply);
        }
        if(item.suffix.strength_percent != null) {
          strength_percent = strength_percent + Math.round(item.suffix.strength_percent * bonusMultiply);
        }
        if(item.suffix.strength != null) {
          strength = strength + Math.round(item.suffix.strength * bonusMultiply);
        }
        if(item.suffix.stun_chance != null) {
          stun_chance = stun_chance + Math.round(item.suffix.stun_chance * bonusMultiply);
        }
    }

    if(armor_percent != 0) {
    armor = armor + ((armor_percent / 100) * armor);
    }
    var itemDescription: any = [];
    itemDescription.push(item.itemBase.type);
    itemDescription.push("-------");
    if(armor != 0) {
      itemDescription.push(" Armor: " + armor);
    }
    if(attack_speed != 0) {
      itemDescription.push(" Attack speed +" + attack_speed + "%");
    }
    if(block_percent != 0) {
      itemDescription.push(" Block +" + block_percent + "%");
    }
    if(block_damage != 0) {
      itemDescription.push(" Blocked damage: " + block_damage);
    }
    if(max_damage != 0) {
      itemDescription.push(" Physical damage:" + min_damage + " - " + max_damage);
    }
    if(max_bleed != 0) {
      itemDescription.push(" Bleed damage:" + min_bleed + " - " + max_bleed);
    }
    if(max_cold != 0) {
      itemDescription.push(" Cold damage:" + min_cold + " - " + max_cold);
    }
    if(max_electric != 0) {
      itemDescription.push(" Electric damage:" + min_electric + " - " + max_electric);
    }
    if(max_fire != 0) {
      itemDescription.push(" Fire damage:" + min_fire + " - " + max_fire);
    }
    if(max_poison != 0) {
      itemDescription.push(" Bleed damage:" + min_poison + " - " + max_poison);
    }
    if(health != 0) {
      itemDescription.push(" Health +" + health);
    }
    if(health_regen != 0) {
      itemDescription.push(" Health Per Round +" + health_regen);
    }
    if(strength != 0) {
      itemDescription.push(" Strength +" + strength);
    }
    if(agility != 0) {
      itemDescription.push(" Agility +" + agility);
    }
    if(intelligence != 0) {
      itemDescription.push(" Intelligence +" + intelligence);
    }
    if(damage != 0) {
      itemDescription.push(" Damage +" + damage);
    }
    if(damage_bleed != 0) {
      itemDescription.push(" Bleed damage +" + damage_bleed);
    }
    if(damage_cold != 0) {
      itemDescription.push(" Cold damage +" + damage_cold);
    }
    if(damage_electric != 0) {
      itemDescription.push(" Electric damage +" + damage_electric);
    }
    if(damage_fire != 0) {
      itemDescription.push(" Fire damage +" + damage_fire);
    }
    if(off_ability != 0) {
      itemDescription.push(" Offensive ability +" + off_ability);
    }
    if(def_ability != 0) {
      itemDescription.push(" Defensive ability +" + def_ability);
    }
    if(damage_percent != 0) {
      itemDescription.push(" Damage +" + damage_percent + "%");
    }
    if(dmg_percent_bleed != 0) {
      itemDescription.push(" Bleed damage +" + dmg_percent_bleed + "%");
    }
    if(dmg_percent_cold != 0) {
      itemDescription.push(" Cold damage +" + dmg_percent_cold + "%");
    }
    if(dmg_percent_electric != 0) {
      itemDescription.push(" Electric damage +" + dmg_percent_electric + "%");
    }
    if(dmg_percent_fire != 0) {
      itemDescription.push(" Fire damage +" + dmg_percent_fire + "%");
    }
    if(dmg_percent_poison != 0) {
      itemDescription.push(" Poison damage +" + dmg_percent_poison + "%");
    }
    if(agility_percent != 0) {
      itemDescription.push(" Agility +" + agility_percent + "%");
    }
    if(endurance_percent != 0) {
      itemDescription.push(" Endurance +" + endurance_percent + "%");
    }
    if(health_percent != 0) {
      itemDescription.push(" Health +" + health_percent + "%");
    }
    if(strength_percent != 0) {
      itemDescription.push(" Strength +" + strength_percent + "%");
    }
    if(intelligence_percent != 0) {
      itemDescription.push(" Intelligence +" + intelligence_percent + "%");
    }
    if(def_ability_percent != 0) {
      itemDescription.push(" Defensive ability +" + def_ability_percent + "%");
    }
    if(off_ability_percent != 0) {
      itemDescription.push(" Offensive ability +" + off_ability_percent + "%");
    }
    if(res_bleed != 0) {
      itemDescription.push(" Bleed resistance +" + res_bleed + "%");
    }
    if(res_cold != 0) {
      itemDescription.push(" Cold resistance +" + res_cold + "%");
    }
    if(res_electric != 0) {
      itemDescription.push(" Electric resistance +" + res_electric + "%");
    }
    if(res_fire != 0) {
      itemDescription.push(" Fire resistance +" + res_fire + "%");
    }
    if(res_stun != 0) {
      itemDescription.push(" Stun resistance +" + res_stun + "%");
    }
    if(res_poison != 0) {
      itemDescription.push(" Poison resistance +" + res_poison + "%");
    }
    if(slow != 0) {
      itemDescription.push(" Enemy slow +" + slow + "%");
    }
    if(stun_chance != 0) {
      itemDescription.push(" Stun chance +" + stun_chance + "%");
    }
    itemDescription.push("-------");
    itemDescription.push("Requirements: ");
    if(item.itemBase.req_strength) {
      itemDescription.push("Strength: " + Math.round(item.itemBase.req_strength * requirementMultiply));
    }
    if(item.itemBase.req_intelligence) {
      itemDescription.push("Intelligence: " + Math.round(item.itemBase.req_intelligence * requirementMultiply));
    }
    if(item.itemBase.req_dexterity) {
      itemDescription.push("Agility: " + Math.round(item.itemBase.req_dexterity * requirementMultiply));
    }
    if(item.prefix != null && item.suffix != null) {
      if(item.prefix.level > item.suffix.level) {
        level = Math.round(item.prefix.level * requirementMultiply);
      } else if (item.prefix.level < item.suffix.level) {
        level = Math.round(item.suffix.level * requirementMultiply);
      } else {
        level = Math.round(item.suffix.level * requirementMultiply);
      }
      itemDescription.push("Level: " + level);
    } else if (item.prefix == null && item.suffix != null) {
      level = Math.round(item.suffix.level * requirementMultiply);
      itemDescription.push("Level: " + level);
    } else if (item.suffix == null && item.prefix != null) {
      level = Math.round(item.prefix.level * requirementMultiply);
      itemDescription.push("Level: " + level);
    }
    return itemDescription;
}
}
