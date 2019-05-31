import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { CharacterService } from '../shared/character/character.service';
import { UserService } from '../shared/user/user.service';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemService } from '../shared/item/item.service';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-armory',
  templateUrl: './armory.component.html',
  styleUrls: ['./armory.component.css']
})
export class ArmoryComponent implements OnInit {

  hero: any = {};

  armory: Array<any>;
  items: any = [];

  item: any = {};

  showDetails = false;
  equippedItems: Array<any>;

  headFree = true;
  head: any = {};
  neckFree = true;
  neck: any = {};
  chestFree = true;
  chest: any = {};
  armsFree = true;
  arms: any = {};
  legsFree = true;
  legs: any = {};
  ring1Free = true;
  ring1: any = {};
  ring2Free = true;
  ring2: any = {};
  leftHandFree = true;
  leftHand: any = {};
  rightHandFree = true;
  rightHand: any = {};

  prefixTrue = false;
  suffixTrue = false;

  constructor(private characterService: CharacterService,
              private router: Router,
              private route: ActivatedRoute,
              private userService: UserService,
              private itemService: ItemService,
              private appComponent: AppComponent) { }

  ngOnInit() {
    if(sessionStorage.getItem('user_id') == 'undefined') {
      this.router.navigate(['/index']);
    }
    if(sessionStorage.getItem('hero') != null) {
      this.characterService.getHeroName(sessionStorage.getItem('hero')).subscribe((result: any) => {
        this.hero = result;
        this.itemService.getUserItems(this.hero.char_id).subscribe((result: any) => {
          this.armory = result;
          console.log(this.armory);
          for (let item of this.armory) {
            item.name = item.itemBase.name;
            if(item.prefix != null) {
              item.name = item.prefix.name + " " + item.name;
            }
            if(item.suffix != null) {
              item.name = item.name + " " + item.suffix.name;
            }
          }
          this.itemService.getEquippedItems(this.hero.char_id).subscribe((result: any) => {
            this.equippedItems = result;
            for (let item of this.equippedItems) {
              item.name = item.itemBase.name;
              if(item.prefix != null) {
                item.name = item.prefix.name + " " + item.name;
              }
              if(item.suffix != null) {
                item.name = item.name + " " + item.suffix.name;
              }
              if(item.itemBase.type == "Head") {
                this.headFree = false;
                this.head = item;
              }
              if(item.itemBase.type == "Arms") {
                this.armsFree = false;
                this.arms = item;
              }
              if(item.itemBase.type == "Legs") {
                this.legsFree = false;
                this.legs = item;
              }
              if(item.itemBase.type == "Chest") {
                this.chestFree = false;
                this.chest = item;
              }
              if(item.itemBase.type == "Necklage") {
                this.neckFree = false;
                this.neck = item;
              }
              if(item.itemBase.type == "Ring") {
                this.ring1Free = false;
                this.ring1 = item;
              }
              if(this.ring1Free == false) {
                if(item.itemBase.type == "Ring") {
                  this.ring2Free = false;
                  this.ring2 = item;
                }
              }
              if(item.itemBase.type == "Weapon") {
                this.rightHandFree = false;
                this.rightHand = item;
              }
              if(item.itemBase.type == "Shield") {
                this.leftHandFree = false;
                this.leftHand = item;
              }
            }
            console.log(this.equippedItems);
          });
        });
      });
      this.appComponent.getResources();
    } else {
      this.router.navigate(['/character']);
    }

  }

  showItemDetails(item: string, where: string) {
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
    var itemDescription: any = [];
    if (where == 'armory') {
    this.item = this.armory.find(iten => iten == item);
    }
    else if (where == 'equipped') {
      this.item = this.equippedItems.find(iten => iten == item);
    }
    if(this.item.prefix != null || this.item.prefix == 'undefined') {
      if (this.item.prefix.agility_percent != null) {
        agility_percent = this.item.prefix.agility_percent;
      }
      if(this.item.prefix.armor != null) {
        armor = this.item.prefix.armor;
      }
      if(this.item.prefix.armor_percent != null) {
        armor_percent = this.item.prefix.armor_percent;
      }
      if(this.item.prefix.attack_speed != null) {
        attack_speed = this.item.prefix.attack_speed;
      }
      if(this.item.prefix.block_percent != null) {
        block_percent = this.item.prefix.block_percent;
      }
      if(this.item.prefix.damage_cold != null) {
        damage_cold = this.item.prefix.damage_cold;
      }
      if(this.item.prefix.damage_electric != null) {
        damage_electric = this.item.prefix.damage_electric;
      }
      if(this.item.prefix.damage_fire != null) {
        damage_fire = this.item.prefix.damage_fire;
      }
      if(this.item.prefix.damage_percent != null) {
        damage_percent = this.item.prefix.damage_percent;
      }
      if(this.item.prefix.def_ability_percent != null) {
        def_ability_percent = this.item.prefix.def_ability_percent;
      }
      if(this.item.prefix.dmg_percent_bleed != null) {
        dmg_percent_bleed = this.item.prefix.dmg_percent_bleed;
      }
      if(this.item.prefix.dmg_percent_cold != null) {
        dmg_percent_cold = this.item.prefix.dmg_percent_cold;
      }
      if(this.item.prefix.dmg_percent_electric != null) {
        dmg_percent_electric = this.item.prefix.dmg_percent_electric;
      }
      if(this.item.prefix.dmg_percent_fire != null) {
        dmg_percent_fire = this.item.prefix.dmg_percent_fire;
      }
      if(this.item.prefix.health_percent != null) {
        health_percent = this.item.prefix.health_percent;
      }
      if(this.item.prefix.health_regen != null) {
        health_regen = this.item.prefix.health_regen;
      }
      if(this.item.prefix.intelligence_percent != null) {
        intelligence_percent = this.item.prefix.intelligence_percent;
      }
      if(this.item.prefix.max_bleed != null) {
        max_bleed = this.item.prefix.max_bleed;
      }
      if(this.item.prefix.max_cold != null) {
        max_cold = this.item.prefix.max_cold;
      }
      if(this.item.prefix.max_damage != null) {
        max_damage = this.item.prefix.max_damage;
      }
      if(this.item.prefix.max_electric != null) {
        max_electric = this.item.prefix.max_electric;
      }
      if(this.item.prefix.max_fire != null) {
        max_fire = this.item.prefix.max_fire;
      }
      if(this.item.prefix.max_poison != null) {
        max_poison = this.item.prefix.max_poison;
      }
      if(this.item.prefix.min_bleed != null) {
        min_bleed = this.item.prefix.min_bleed;
      }
      if(this.item.prefix.min_cold != null) {
        min_cold = this.item.prefix.min_cold;
      }
      if(this.item.prefix.min_damage != null) {
        min_damage = this.item.prefix.min_damage;
      }
      if(this.item.prefix.min_electric != null) {
        min_electric = this.item.prefix.min_electric;
      }
      if(this.item.prefix.min_fire != null) {
        min_fire = this.item.prefix.min_fire;
      }
      if(this.item.prefix.min_poison != null) {
        min_poison = this.item.prefix.min_poison;
      }
      if(this.item.prefix.off_ability != null) {
        off_ability = this.item.prefix.off_ability;
      }
      if(this.item.prefix.off_ability_percent != null) {
        off_ability_percent = this.item.prefix.off_ability_percent;
      }
      if(this.item.prefix.res_bleed != null) {
        res_bleed = this.item.prefix.res_bleed;
      }
      if(this.item.prefix.res_cold != null) {
        res_cold = this.item.prefix.res_cold;
      }
      if(this.item.prefix.res_electric != null) {
        res_electric = this.item.prefix.res_electric;
      }
      if(this.item.prefix.res_fire != null) {
        res_fire = this.item.prefix.res_fire;
      }
      if(this.item.prefix.res_poison != null) {
        res_poison = this.item.prefix.res_poison;
      }
      if(this.item.prefix.res_stun != null) {
        res_stun = this.item.prefix.res_stun;
      }
      if(this.item.prefix.slow != null) {
        slow = this.item.prefix.slow;
      }
      if(this.item.prefix.strength_percent != null) {
        strength_percent = this.item.prefix.strength_percent;
      }
      if(this.item.prefix.strength != null) {
        strength = this.item.prefix.strength;
      }
    }
    if(this.item.itemBase.armor != null) {
        armor = armor + this.item.itemBase.armor;
      }
      if(this.item.itemBase.agility != null) {
        agility = agility + this.item.itemBase.agility;
      }
      if(this.item.itemBase.agility_percent != null) {
        agility_percent = agility_percent + this.item.itemBase.agility_percent;
      }
      if(this.item.itemBase.attack_speed != null) {
        attack_speed = attack_speed + this.item.itemBase.attack_speed;
      }
      if(this.item.itemBase.block_chance != null) {
        block_percent = block_percent + this.item.itemBase.block_chance;
      }
      if(this.item.itemBase.block_damage != null) {
        block_damage = block_damage + this.item.itemBase.block_damage;
      }
      if(this.item.itemBase.def_ability != null) {
        def_ability = def_ability + this.item.itemBase.def_ability;
      }
      if(this.item.itemBase.damage_bleed != null) {
        damage_bleed = damage_bleed + this.item.itemBase.damage_bleed;
      }
      if(this.item.itemBase.damage_cold != null) {
        damage_cold = damage_cold + this.item.itemBase.damage_cold;
      }
      if(this.item.itemBase.damage_electric != null) {
        damage_electric = damage_electric + this.item.itemBase.damage_electric;
      }
      if(this.item.itemBase.damage_fire != null) {
        damage_fire = damage_fire + this.item.itemBase.damage_fire;
      }
      if(this.item.itemBase.damage_poison != null) {
        damage_poison = damage_poison + this.item.itemBase.damage_poison;
      }
      if(this.item.itemBase.dmg_percent != null) {
        damage_percent = damage_percent + this.item.itemBase.dmg_percent;
      }
      if(this.item.itemBase.dmg_bleed_percent != null) {
        dmg_percent_bleed = dmg_percent_bleed + this.item.itemBase.dmg_bleed_percent;
      }
      if(this.item.itemBase.dmg_cold_percent != null) {
        dmg_percent_cold = dmg_percent_cold + this.item.itemBase.dmg_cold_percent;
      }
      if(this.item.itemBase.dmg_electric_percent != null) {
        dmg_percent_electric = dmg_percent_electric + this.item.itemBase.dmg_electric_percent;
      }
      if(this.item.itemBase.dmg_fire_percent != null) {
        dmg_percent_fire = dmg_percent_fire + this.item.itemBase.dmg_fire_percent;
      }
      if(this.item.itemBase.dmg_poison_percent != null) {
        dmg_percent_poison = dmg_percent_poison + this.item.itemBase.dmg_poison_percent;
      }
      if(this.item.itemBase.health != null) {
        health = health + this.item.itemBase.health;
      }
      if(this.item.itemBase.health_percent != null) {
        health_percent = health_percent + this.item.itemBase.health_percent;
      }
      if(this.item.itemBase.health_regen != null) {
        health_regen = health_regen + this.item.itemBase.health_regen;
      }
      if(this.item.itemBase.intelligence != null) {
        intelligence = intelligence + this.item.itemBase.intelligence;
      }
      if(this.item.itemBase.intelligence_percent != null) {
        intelligence_percent = intelligence_percent + this.item.itemBase.intelligence_percent;
      }
      if(this.item.itemBase.min_damage != null) {
        min_damage = min_damage + this.item.itemBase.min_damage;
      }
      if(this.item.itemBase.max_damage != null) {
        max_damage = max_damage + this.item.itemBase.max_damage;
      }
      if(this.item.itemBase.min_cold != null) {
        min_cold = min_cold + this.item.itemBase.min_cold;
      }
      if(this.item.itemBase.max_cold != null) {
        max_cold = max_cold + this.item.itemBase.max_cold;
      }
      if(this.item.itemBase.min_fire != null) {
        min_fire = min_fire + this.item.itemBase.min_fire;
      }
      if(this.item.itemBase.max_fire != null) {
        max_fire = max_fire + this.item.itemBase.max_fire;
      }
      if(this.item.itemBase.min_electric != null) {
        min_electric = min_electric + this.item.itemBase.min_electric;
      }
      if(this.item.itemBase.max_electric != null) {
        max_electric = max_electric + this.item.itemBase.max_electric;
      }
      if(this.item.itemBase.off_ability != null) {
        off_ability = off_ability + this.item.itemBase.off_ability;
      }
      if(this.item.itemBase.res_bleed != null) {
        res_bleed = res_bleed + this.item.itemBase.res_bleed;
      }
      if(this.item.itemBase.res_cold != null) {
        res_cold = res_cold + this.item.itemBase.res_cold;
      }
      if(this.item.itemBase.res_electric != null) {
        res_electric = res_electric + this.item.itemBase.res_electric;
      }
      if(this.item.itemBase.res_fire != null) {
        res_fire = res_fire + this.item.itemBase.res_fire;
      }
      if(this.item.itemBase.res_poison != null) {
        res_poison = res_poison + this.item.itemBase.res_poison;
      }
      if(this.item.itemBase.res_stun != null) {
        res_stun = res_stun + this.item.itemBase.res_stun;
      }
      if(this.item.itemBase.strength != null) {
        strength = strength + this.item.itemBase.strength;
      }
      if(this.item.itemBase.strength_percent != null) {
        strength_percent = strength_percent + this.item.itemBase.strength_percent;
      }
      if(this.item.itemBase.stun_chance != null) {
        stun_chance = stun_chance + this.item.itemBase.stun_chance;
      }
    if(this.item.suffix != null || this.item.prefix == 'undefined') {
      if(this.item.suffix.agility_percent != null) {
          agility_percent = agility_percent + this.item.suffix.agility_percent;
        }
        if(this.item.suffix.agility != null) {
            agility = agility + this.item.suffix.agility;
          }
        if(this.item.suffix.armor_percent != null) {
          armor_percent = armor_percent + this.item.suffix.armor_percent;
        }
        if(this.item.suffix.attack_speed != null) {
          attack_speed = attack_speed + this.item.suffix.attack_speed;
        }
        if(this.item.suffix.damage != null) {
          damage = damage + this.item.suffix.damage;
        }
        if(this.item.suffix.damage_cold != null) {
          damage_cold = damage_cold + this.item.suffix.damage_cold;
        }
        if(this.item.suffix.damage_electric != null) {
          damage_electric = damage_electric + this.item.suffix.damage_electric;
        }
        if(this.item.suffix.damage_fire != null) {
          damage_fire = damage_fire + this.item.suffix.damage_fire;
        }
        if(this.item.suffix.damage_percent != null) {
          damage_percent = damage_percent + this.item.suffix.damage_percent;
        }
        if(this.item.suffix.dmg_percent_bleed != null) {
          dmg_percent_bleed = dmg_percent_bleed + this.item.suffix.dmg_percent_bleed;
        }
        if(this.item.suffix.dmg_percent_cold != null) {
          dmg_percent_cold = dmg_percent_cold + this.item.suffix.dmg_percent_cold;
        }
        if(this.item.suffix.dmg_percent_electric != null) {
          dmg_percent_electric = dmg_percent_electric + this.item.suffix.dmg_percent_electric;
        }
        if(this.item.suffix.dmg_percent_fire != null) {
          dmg_percent_fire = dmg_percent_fire + this.item.suffix.dmg_percent_fire;
        }
        if(this.item.suffix.dmg_percent_poison != null) {
          dmg_percent_poison = dmg_percent_poison + this.item.suffix.dmg_percent_poison;
        }
        if(this.item.suffix.def_ability != null) {
          def_ability = def_ability + this.item.suffix.def_ability;
        }
        if(this.item.suffix.health != null) {
          health = health + this.item.suffix.health;
        }
        if(this.item.suffix.health_percent != null) {
          health_percent = health_percent + this.item.suffix.health_percent;
        }
        if(this.item.suffix.health_regen != null) {
          health_regen = health_regen + this.item.suffix.health_regen;
        }
        if(this.item.suffix.intelligence != null) {
          intelligence = intelligence + this.item.suffix.intelligence;
        }
        if(this.item.suffix.intelligence_percent != null) {
          intelligence_percent = intelligence_percent + this.item.suffix.intelligence_percent;
        }
        if(this.item.suffix.off_ability != null) {
          off_ability = off_ability + this.item.suffix.off_ability;
        }
        if(this.item.suffix.res_cold != null) {
          res_cold = res_cold + this.item.suffix.res_cold;
        }
        if(this.item.suffix.res_electric != null) {
          res_electric = res_electric + this.item.suffix.res_electric;
        }
        if(this.item.suffix.res_fire != null) {
          res_fire = res_fire + this.item.suffix.res_fire;
        }
        if(this.item.suffix.res_stun != null) {
          res_stun = res_stun + this.item.suffix.res_stun;
        }
        if(this.item.suffix.slow != null) {
          slow = slow + this.item.suffix.slow;
        }
        if(this.item.suffix.strength_percent != null) {
          strength_percent = strength_percent + this.item.suffix.strength_percent;
        }
        if(this.item.suffix.strength != null) {
          strength = strength + this.item.suffix.strength;
        }
        if(this.item.suffix.stun_chance != null) {
          stun_chance = stun_chance + this.item.suffix.stun_chance;
        }
    }

    if(armor_percent != 0) {
    armor = armor + ((armor_percent / 100) * armor);
    }

    var itemDescription: any = [];
    itemDescription.push(this.item.itemBase.type);
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

    if(this.item.itemBase.req_strength) {
      itemDescription.push("Strength: " + this.item.itemBase.req_strength);
    }
    if(this.item.itemBase.req_intelligence) {
      itemDescription.push("Intelligence: " + this.item.itemBase.req_intelligence);
    }
    if(this.item.itemBase.req_dexterity) {
      itemDescription.push("Agility: " + this.item.itemBase.req_dexterity);
    }
    if(this.item.prefix != null && this.item.suffix != null) {
      if(this.item.prefix.level > this.item.suffix.level) {
        level = this.item.prefix.level;
      } else if (this.item.prefix.level < this.item.suffix.level) {
        level = this.item.suffix.level;
      } else {
        level = this.item.suffix.level;
      }
      itemDescription.push("Level: " + level);
    } else if (this.item.prefix == null && this.item.suffix != null) {
      level = this.item.suffix.level;
      itemDescription.push("Level: " + level);
    } else if (this.item.suffix == null && this.item.prefix != null) {
      level = this.item.prefix.level;
      itemDescription.push("Level: " + level);
    }
    return itemDescription;
  }



  equip(input: string) {
    let item: any = {};
    item = input;
    let level: any = 0;
    let strength: any = 0;
    let agility: any = 0;
    let intelligence: any = 0;
    if((item.itemBase.type == "Head" && this.headFree == true) ||
      (item.itemBase.type == "Chest" && this.chestFree == true) ||
      (item.itemBase.type == "Necklage" && this.neckFree == true) ||
      (item.itemBase.type == "Ring" && this.ring1Free == true) ||
      (item.itemBase.type == "Ring" && this.ring2Free == true) ||
      (item.itemBase.type == "Weapon" && this.rightHandFree == true) ||
      (item.itemBase.type == "Shield" && this.leftHandFree == true) ||
      (item.itemBase.type == "Arms" && this.armsFree == true) ||
      (item.itemBase.type == "Legs" && this.legsFree == true)) {

      if(item.prefix != null && item.suffix != null) {
          if(item.prefix.level > item.suffix.level) {
            level = item.prefix.level;
          } else if (item.prefix.level < item.suffix.level) {
            level = item.suffix.level;
          } else {
            level = item.suffix.level;
          }
        } else if (item.prefix == null && item.suffix != null) {
          level = item.suffix.level;
        } else if (item.suffix == null && item.prefix != null) {
          level = item.prefix.level;
        }
          if(this.hero.level >= level) {

            if(item.itemBase.req_dexterity) {
              agility = item.itemBase.req_dexterity;
            }
            if(item.itemBase.req_strength) {
              strength = item.itemBase.req_strength;
            }
            if(item.itemBase.req_intelligence) {
              intelligence = item.itemBase.req_intelligence;
            }

            if(this.hero.strength >= strength &&
              this.hero.intelligence >= intelligence &&
              this.hero.agility >= agility) {

                item.equipped = "yes";
                if(item.itemBase.type == "Head") {
                  this.headFree = false;
                } else if (item.itemBase.type == "Chest") {
                  this.chestFree = false;
                } else if (item.itemBase.type == "Necklage") {
                  this.neckFree = false;
                } else if (item.itemBase.type == "Ring" && this.ring1Free == false) {
                  this.ring2Free == false;
                } else if (item.itemBase.type == "Ring" && this.ring1Free == true) {
                  this.ring1Free = false;
                } else if (item.itemBase.type == "Weapon") {
                  this.rightHandFree = false;
                } else if (item.itemBase.type == "Shield") {
                  this.leftHandFree = false;
                } else if (item.itemBase.type == "Arms") {
                  this.armsFree = false;
                } else if (item.itemBase.type == "Legs") {
                  this.legsFree = false;
                }
                this.equippedItems.push(item);
                this.armory.splice(item, 1);
                this.itemService.equipItem(item.item_id, this.hero.char_id).subscribe();
                this.router.navigate(['/character']);
              } else {
                  console.log("Requirements not met");
                }
              }
              else {
                  console.log("You must level up to equip this item");
                }
    }
    else {
    console.log("You must take your current " + item.itemBase.type + " first");
    }
  }

  takeOff(item: any) {


    if(item.itemBase.type == "Head") {
      this.headFree = true;
    }
    if(item.itemBase.type == "Chest") {
      this.chestFree = true;
    }
    if(item.itemBase.type == "Necklage") {
      this.neckFree = true;
    }
    if(item.itemBase.type == "Arms") {
      this.legsFree = true;
    }
    if(item.itemBase.type == "Legs") {
      this.armsFree = true;
    }
    if(item.itemBase.type == "Ring") {
      if(this.ring2Free == true) {
      this.ring1Free = true;
    } else {
      this.ring2Free = true;
    }
    }
    if(item.itemBase.type == "Weapon") {
      this.rightHandFree = true;
    }
    if(item.itemBase.type == "Shield") {
      this.leftHandFree = true;
    }

    item.equipped = "no";
    this.itemService.takeOffItem(item.item_id, this.hero.char_id).subscribe();
    this.router.navigate(['/character']);
    //this.characterService.updateHero(this.hero).subscribe();
  }

}
