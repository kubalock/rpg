import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShardService {
  public API = '//localhost:8080';

  constructor(private http: HttpClient) { }

  getCharShards(id: String) {
    return this.http.get(this.API + '/getCharShards/' + id);
  }

  getAssembleShards(char_id: String, shard_id: String, char_shard_id: String) {
    return this.http.get(this.API + '/getShardsToAssemble/' + char_id + '/' + shard_id + '/' + char_shard_id);
  }

  assembleShards(first_id: String, second_id: String) {
    return this.http.post(this.API + '/assembleShards/' + first_id + '/' + second_id);
  }

  deleteShard(shard_id: String) {
    return this.http.delete(this.API + '/deleteShard/' + shard_id);
  }

  enchanceItem(shard_id: String, item_id) {
    return this.http.post(this.API + '/enchanceItem/' + shard_id + '/' + item_id);
  }

  showShardDetails(shard: any) {
    var armor: any = 0;
    var agility: any = 0;
    var agility_percent: any = 0;
    var attack_speed: any = 0;
    var block_chance: any = 0;
    var block_damage: any = 0;
    var def_ability: any = 0;
    var dmg: any = 0;
    var dmg_bleed: any = 0;
    var dmg_bleed_percent: any = 0;
    var dmg_cold: any = 0;
    var dmg_cold_percent: any = 0;
    var dmg_electric: any = 0;
    var dmg_electric_percent: any = 0;
    var dmg_fire: any = 0;
    var dmg_fire_percent: any = 0;
    var dmg_poison: any = 0;
    var dmg_poison_percent: any = 0;
    var dmg_percent: any = 0;
    var endurance_percent: any = 0;
    var health: any = 0;
    var health_percent: any = 0;
    var health_regen: any = 0;
    var ignore_dmg: any = 0;
    var intelligence: any = 0;
    var intelligence_percent: any = 0;
    var off_ability: any = 0;
    var max_dmg: any = 0;
    var min_dmg: any = 0;
    var min_fire: any = 0;
    var max_fire: any = 0;
    var res_cold: any = 0;
    var res_bleed: any = 0;
    var res_electric: any = 0;
    var res_fire: any = 0;
    var res_stun: any = 0;
    var res_poison: any = 0;
    var slow: any = 0;
    var strength: any = 0;
    var strength_percent: any = 0;
    var stun_chance: any = 0;

    var shardDescription: any = [];

    if(shard.shard.armor != null) {
      armor = shard.shard.armor * shard.level;
    }
    if(shard.shard.agility != null) {
      agility = shard.shard.agility * shard.level;
    }
    if(shard.shard.agility_percent != null) {
      agility_percent = shard.shard.agility_percent * shard.level;
    }
    if(shard.shard.attack_speed != null) {
      attack_speed = shard.shard.attack_speed * shard.level;
    }
    if(shard.shard.block_damage != null) {
      block_damage = shard.shard.block_damage * shard.level;
    }
    if(shard.shard.def_ability != null) {
      def_ability = shard.shard.def_ability * shard.level;
    }
    if(shard.shard.dmg != null) {
      dmg = shard.shard.dmg * shard.level;
    }
    if(shard.shard.dmg_bleed != null) {
      dmg_bleed = shard.shard.dmg_bleed * shard.level;
    }
    if(shard.shard.dmg_bleed_percent != null) {
      dmg_bleed_percent = shard.shard.dmg_bleed_percent * shard.level;
    }
    if(shard.shard.dmg_cold != null) {
      dmg_cold = shard.shard.dmg_cold * shard.level;
    }
    if(shard.shard.dmg_cold_percent != null) {
      dmg_cold_percent = shard.shard.dmg_cold_percent * shard.level;
    }
    if(shard.shard.dmg_electric != null) {
      dmg_electric = shard.shard.dmg_electric * shard.level;
    }
    if(shard.shard.dmg_electric_percent != null) {
      dmg_electric_percent = shard.shard.dmg_electric_percent * shard.level;
    }
    if(shard.shard.dmg_fire != null) {
      dmg_fire = shard.shard.dmg_fire * shard.level;
    }
    if(shard.shard.dmg_fire_percent != null) {
      dmg_fire_percent = shard.shard.dmg_fire_percent * shard.level;
    }
    if(shard.shard.dmg_poison != null) {
      dmg_poison = shard.shard.dmg_poison * shard.level;
    }
    if(shard.shard.dmg_poison_percent != null) {
      dmg_poison_percent = shard.shard.dmg_poison_percent * shard.level;
    }
    if(shard.shard.dmg_percent != null) {
      dmg_percent = shard.shard.dmg_percent * shard.level;
    }
    if(shard.shard.endurance_percent != null) {
      endurance_percent = shard.shard.endurance_percent * shard.level;
    }
    if(shard.shard.health != null) {
      health = shard.shard.health * shard.level;
    }
    if(shard.shard.health_percent != null) {
      health_percent = shard.shard.health_percent * shard.level;
    }
    if(shard.shard.ignore_dmg != null) {
      ignore_dmg = shard.shard.ignore_dmg * shard.level;
    }
    if(shard.shard.intelligence != null) {
      intelligence = shard.shard.intelligence * shard.level;
    }
    if(shard.shard.intelligence_percent != null) {
      intelligence_percent = shard.shard.intelligence_percent * shard.level;
    }
    if(shard.shard.min_dmg != null) {
      min_dmg = shard.shard.min_dmg * shard.level;
      max_dmg = shard.shard.max_dmg * shard.level;
    }
    if(shard.shard.min_fire != null) {
      min_fire = shard.shard.min_fire * shard.level;
      max_fire = shard.shard.max_fire * shard.level;
    }
    if(shard.shard.res_bleed != null) {
      res_bleed = shard.shard.res_bleed * shard.level;
    }
    if(shard.shard.res_cold != null) {
      res_cold = shard.shard.res_cold * shard.level;
    }
    if(shard.shard.res_electric != null) {
      res_electric = shard.shard.res_electric * shard.level;
    }
    if(shard.shard.res_fire != null) {
      res_fire = shard.shard.res_fire * shard.level;
    }
    if(shard.shard.res_stun != null) {
      res_stun = shard.shard.res_stun * shard.level;
    }
    if(shard.shard.res_poison != null) {
      res_poison = shard.shard.res_poison * shard.level;
    }
    if(shard.shard.slow != null) {
      slow = shard.shard.slow * shard.level;
    }
    if(shard.shard.strength != null) {
      strength = shard.shard.strength * shard.level;
    }
    if(shard.shard.strength_percent != null) {
      strength_percent = shard.shard.strength_percent * shard.level;
    }
    if(shard.shard.stun_chance != null) {
      stun_chance = shard.shard.stun_chance * shard.level;
    }

    shardDescription.push(shard.shard.name);
    shardDescription.push("Type: " + shard.shard.type_eq);
    shardDescription.push("Level " + shard.level + "/5");
    shardDescription.push("-------");
    if(armor != 0) {
      shardDescription.push(" Armor +" + armor);
    }
    if(agility != 0) {
      shardDescription.push(" Agility +" + agility);
    }
    if(agility_percent != 0) {
      shardDescription.push(" Agility +" + agility_percent + "%");
    }
    if(attack_speed != 0) {
      shardDescription.push(" Attack speed +" + attack_speed + "%");
    }
    if(block_chance != 0) {
      shardDescription.push(" Block +" + block_chance + "%");
    }
    if(block_damage != 0) {
      shardDescription.push(" Damage blocked +" + block_damage);
    }
    if(def_ability != 0) {
      shardDescription.push(" Defensive ability +" + def_ability);
    }
    if(dmg != 0) {
      shardDescription.push(" Damage +" + dmg);
    }
    if(dmg_bleed != 0) {
      shardDescription.push(" Bleed damage +" + dmg_bleed);
    }
    if(dmg_bleed_percent != 0) {
      shardDescription.push(" Bleed damage +" + dmg_bleed + "%");
    }
    if(dmg_cold != 0) {
      shardDescription.push(" Cold damage +" + dmg_cold);
    }
    if(dmg_cold_percent != 0) {
      shardDescription.push(" Cold damage +" + dmg_cold_percent + "%");
    }
    if(dmg_electric != 0) {
      shardDescription.push(" Electric damage +" + dmg_electric);
    }
    if(dmg_electric_percent != 0) {
      shardDescription.push(" Electric damage +" + dmg_electric_percent + "%");
    }
    if(dmg_fire != 0) {
      shardDescription.push(" Fire damage +" + dmg_fire);
    }
    if(dmg_fire_percent != 0) {
      shardDescription.push(" Fire damage +" + dmg_fire_percent + "%");
    }
    if(dmg_poison != 0) {
      shardDescription.push(" Poison damage +" + dmg_poison);
    }
    if(dmg_poison_percent != 0) {
      shardDescription.push(" Poison damage +" + dmg_poison_percent + "%");
    }
    if(dmg_percent != 0) {
      shardDescription.push(" Physical damage +" + dmg_percent + "%");
    }
    if(endurance_percent != 0) {
      shardDescription.push(" Endurance +" + endurance_percent + "%");
    }
    if(health != 0) {
      shardDescription.push(" Health +" + health);
    }
    if(health_percent != 0) {
      shardDescription.push(" Health +" + health_percent + "%");
    }
    if(health_regen != 0) {
      shardDescription.push(" Health regeneration +" + health_regen);
    }
    if(ignore_dmg != 0) {
      shardDescription.push(" Ignore " + ignore_dmg + "% of damage");
    }
    if(intelligence != 0) {
      shardDescription.push(" Intelligence +" + intelligence);
    }
    if(intelligence_percent != 0) {
      shardDescription.push(" Intelligence +" + intelligence_percent + "%");
    }
    if(off_ability != 0) {
      shardDescription.push(" Offensive ability +" + off_ability);
    }
    if(min_dmg != 0) {
      shardDescription.push(" Damage: " + min_dmg + " - " + max_dmg);
    }
    if(min_fire != 0) {
      shardDescription.push(" Fire damage: " + min_fire + " - " + max_fire);
    }
    if(res_bleed != 0) {
      shardDescription.push(" Bleed resistance +" + res_bleed + "%");
    }
    if(res_cold != 0) {
      shardDescription.push(" Cold resistance +" + res_cold + "%");
    }
    if(res_electric != 0) {
      shardDescription.push(" Electric resistance +" + res_electric + "%");
    }
    if(res_fire != 0) {
      shardDescription.push(" Fire resistance +" + res_fire + "%");
    }
    if(res_stun != 0) {
      shardDescription.push(" Stun resistance +" + res_stun + "%");
    }
    if(res_poison != 0) {
      shardDescription.push(" Poison resistance +" + res_poison + "%");
    }
    if(slow != 0) {
      shardDescription.push(" Enemy slow +" + slow + "%");
    }
    if(strength != 0) {
      shardDescription.push(" Strength +" + strength);
    }
    if(strength_percent != 0) {
      shardDescription.push(" Strength +" + strength_percent + "%");
    }
    if(stun_chance != 0) {
      shardDescription.push(" Stun chance +" + stun_chance + "%");
    }
    if(shard.level == 5) {
      shardDescription.push("-------");
      shardDescription.push("Completion bonus");
      if(shard.agility != null) {
        shardDescription.push("Agility +" + shard.agility);
      }
      if(shard.armor != null) {
        shardDescription.push("Armor +" + shard.armor);
      }
      if(shard.attack_speed != null) {
        shardDescription.push("Attack speed +" + shard.attack_speed + "%");
      }
      if(shard.def_ability != null) {
        shardDescription.push("Defensive ability +" + shard.def_ability);
      }
      if(shard.dmg_bleed != null) {
        shardDescription.push("Bleed damage +" + shard.dmg_bleed);
      }
      if(shard.dmg_bleed_percent != null) {
        shardDescription.push("Bleed damage +" + shard.dmg_bleed_percent + "%");
      }
      if(shard.dmg_cold != null) {
        shardDescription.push("Cold damage +" + shard.dmg_cold);
      }
      if(shard.dmg_cold_percent != null) {
        shardDescription.push("Cold damage +" + shard.dmg_cold_percent + "%");
      }
      if(shard.dmg_elemental != null) {
        shardDescription.push("Elemental damage +" + shard.dmg_elemental);
      }
      if(shard.dmg_fire != null) {
        shardDescription.push("Fire damage +" + shard.dmg_fire);
      }
      if(shard.dmg_fire_percent != null) {
        shardDescription.push("Fire damage +" + shard.dmg_fire_percent + "%");
      }
      if(shard.dmg_poison != null) {
        shardDescription.push("Poison damage +" + shard.dmg_poison);
      }
      if(shard.dmg_poison_percent != null) {
        shardDescription.push("Poison damage +" + shard.dmg_poison_percent + "%");
      }
      if(shard.dmg_percent != null) {
        shardDescription.push("Physical damage +" + shard.dmg_percent + "%");
      }
      if(shard.health != null) {
        shardDescription.push("Health +" + shard.health);
      }
      if(shard.health_regen != null) {
        shardDescription.push("Health regeneration +" + shard.health_regen);
      }
      if(shard.intelligence != null) {
        shardDescription.push("Intelligence +" + shard.intelligence);
      }
      if(shard.intelligence_percent != null) {
        shardDescription.push("Intelligence +" + shard.intelligence_percent + "%");
      }
      if(shard.min_damage != null) {
        shardDescription.push("Damage " + shard.min_damage + " - " + shard.max_damage);
      }
      if(shard.min_cold != null) {
          shardDescription.push("Cold damage " + shard.min_cold + " - " + shard.max_cold);
      }
      if(shard.min_fire != null) {
          shardDescription.push("Fire damage " + shard.min_fire + " - " + shard.max_fire);
      }
      if(shard.min_poison != null) {
          shardDescription.push("Poison damage " + shard.min_poison + " - " + shard.max_poison);
      }
      if(shard.off_ability != null) {
        shardDescription.push("Offensive ability +" + shard.off_ability);
      }
      if(shard.res_bleed != null) {
        shardDescription.push("Bleed resistance +" + shard.res_bleed + "%");
      }
      if(shard.res_cold != null) {
        shardDescription.push("Cold resistance +" + shard.res_cold + "%");
      }
      if(shard.res_electric != null) {
        shardDescription.push("Electric resistance +" + shard.res_electric + "%");
      }
      if(shard.res_fire != null) {
        shardDescription.push("Fire resistance +" + shard.res_fire + "%");
      }
      if(shard.res_poison != null) {
        shardDescription.push("Poison resistance +" + shard.res_poison + "%");
      }
      if(shard.res_stun != null) {
        shardDescription.push("Stun resistance +" + shard.res_poison + "%");
      }
      if(shard.slow != null) {
        shardDescription.push("Enemy slow +" + shard.slow + "%");
      }
      if(shard.stun_chance != null) {
        shardDescription.push("Stun chance +" + shard.stun_chance + "%");
      }
      if(shard.strength != null) {
        shardDescription.push("Strength +" + shard.strength);
      }
      if(shard.strength_percent != null) {
        shardDescription.push("Strength +" + shard.strength_percent + "%");
      }
    }

    return shardDescription;
  }
}
