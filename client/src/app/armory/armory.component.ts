import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { CharacterService } from '../shared/character/character.service';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemService } from '../shared/item/item.service';
import { ShardService } from '../shared/shard/shard.service'
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

  shards: Array<any>;
  shard: any = {};

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
              private itemService: ItemService,
              private appComponent: AppComponent,
              private shardService: ShardService) { }

  ngOnInit() {
    if(sessionStorage.getItem('user_id') == 'undefined') {
      this.router.navigate(['/index']);
    }
    if(sessionStorage.getItem('hero') != null) {
      this.characterService.getHeroName(sessionStorage.getItem('hero')).subscribe((result: any) => {
        this.hero = result;
        this.itemService.getUserItems(this.hero.char_id).subscribe((result: any) => {
          this.armory = result;
          for (let item of this.armory) {
            item.name = item.itemBase.name;
            if(item.prefix != null) {
              item.name = item.prefix.name + " " + item.name;
            }
            if(item.suffix != null) {
              item.name = item.name + " " + item.suffix.name;
            }
            if(item.level > 0 && item.level < 5) {
              item.name = item.name + " +" + item.level;
            }
            if(item.level >= 5 && item.level < 10) {
              item.name = "Good " + item.name;
              if(item.level > 5) {
                item.name = item.name + " +" + (item.level - 5);
              }
            }
            if(item.level >= 10) {
              item.name = "Legendary " + item.name;
              if(item.level > 10) {
                item.name = item.name + " +" + (item.level - 10);
              }
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
              if(item.level > 0 && item.level < 5) {
                item.name = item.name + " +" + item.level;
              }
              if(item.level >= 5 && item.level < 10) {
                item.name = "Good " + item.name;
                if(item.level > 5) {
                  item.name = item.name + " +" + (item.level - 5);
                }
              }
              if(item.level >= 10) {
                item.name = "Legendary " + item.name;
                if(item.level > 10) {
                  item.name = item.name + " +" + (item.level - 10);
                }
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
                if(this.ring1Free == true) {
                this.ring1Free = false;
                this.ring1 = item;
              } else if (this.ring1Free == false) {
                if(this.ring2Free == true) {
                  this.ring2Free = false;
                  this.ring2 = item;
                }
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
            this.shardService.getCharShards(sessionStorage.getItem('char_id')).subscribe((result: any) => {
              this.shards = result;
            });
          });
        });
      });
      this.appComponent.getResources();
    } else {
      this.router.navigate(['/character']);
    }

  }

    showItemDetails(item: string, where: string, requestLevel: string) {
      if(where == 'armory') {
      this.item = this.armory.find(iten => iten == item);
      } else {
      this.item = this.equippedItems.find(iten => iten == item);
      }
      let array = this.itemService.showItemDetails(this.item, where, requestLevel);
      if(this.item.shard_id != null) {
        var shardArray = this.shardService.showShardDetails(this.item.shard_id);
        console.log(shardArray);
      }
      return array;
    }

  sell(item: any) {
    this.itemService.sellItem(item.item_id).subscribe();
  }

  equip(input: string) {
    let item: any = {};
    item = input;
    let requirementMultiply: any = 1;
    if(item.level > 0) {
      for(let i = 0; i < item.level; i++) {
        requirementMultiply = requirementMultiply + 0.025;
      }
    }
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
            level = Math.round(item.prefix.level * requirementMultiply);
          } else if (item.prefix.level < item.suffix.level) {
            level = Math.round(item.suffix.level * requirementMultiply);
          } else {
            level = Math.round(item.suffix.level * requirementMultiply);
          }
        } else if (item.prefix == null && item.suffix != null) {
          level = Math.round(item.suffix.level * requirementMultiply);
        } else if (item.suffix == null && item.prefix != null) {
          level = Math.round(item.prefix.level * requirementMultiply);
        }
          if(this.hero.level >= level) {

            if(item.itemBase.req_dexterity) {
              agility = Math.round(item.itemBase.req_dexterity * requirementMultiply);
            }
            if(item.itemBase.req_strength) {
              strength = Math.round(item.itemBase.req_strength * requirementMultiply);
            }
            if(item.itemBase.req_intelligence) {
              intelligence = Math.round(item.itemBase.req_intelligence * requirementMultiply);
            }

            if(Math.round(this.hero.strength + (this.hero.strength*this.hero.strength_percent/100)) >= strength &&
              Math.round(this.hero.intelligence + (this.hero.intelligence*this.hero.intelligence_percent/100)) >= intelligence &&
              Math.round(this.hero.agility + (this.hero.agility*this.hero.agility_percent/100))>= agility) {

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
