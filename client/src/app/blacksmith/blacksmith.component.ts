import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemService } from '../shared/item/item.service';
import { AppComponent } from '../app.component';
import { BlacksmithService } from '../shared/blacksmith/blacksmith.service';

@Component({
  selector: 'app-blacksmith',
  templateUrl: './blacksmith.component.html',
  styleUrls: ['./blacksmith.component.css']
})
export class BlacksmithComponent implements OnInit {

  armory: Array<any>;

  stones: any = {};

  item: any = {};

  selectedItem: any = {};

  nextLevel: Array<any>;

  constructor( private router: Router,
              private route: ActivatedRoute,
              private itemService: ItemService,
              private appComponent: AppComponent,
              private blacksmithService: BlacksmithService) { }

  ngOnInit() {
    if(sessionStorage.getItem('user_id') == 'undefined') {
      this.router.navigate(['/index']);
    }
    if(sessionStorage.getItem('hero') != null) {
        this.itemService.getUserItems(sessionStorage.getItem('char_id')).subscribe((result: any) => {
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
        });
      this.blacksmithService.getCharStones(sessionStorage.getItem('char_id')).subscribe((result: any) => {
        this.stones = result;
      });
      this.appComponent.getResources();
    } else {
      this.router.navigate(['/character']);
    }
  }

  showItemDetails(item: string, where: string, requestLevel: string) {
    this.item = this.armory.find(iten => iten == item);
    return this.itemService.showItemDetails(this.item, where, requestLevel);
  }

  select(item: any) {
    let level = item.level + 1;
    this.selectedItem = item;
    this.nextLevel = this.showItemDetails(item, 'armory', level);
    this.nextLevel.push("-------");
    this.nextLevel.push("Cost:");
    if(item.level < 4) {
      this.nextLevel.push("Blood stone: 1");
      this.nextLevel.push("Chances for success: 80%");
    }
    if(item.level == 4 || item.level == 9) {
      this.nextLevel.push("Life stone: 1");
      this.nextLevel.push("Chances for success: 50%");
    }
    if(item.level > 4 && item.level < 9) {
      this.nextLevel.push("Heart Stone: 1");
      this.nextLevel.push("Chances for success: 60%");
    }
    if(item.level > 9) {
      this.nextLevel.push("Soul stone: 1");
      this.nextLevel.push("Chances for success: 40%");
    }
  }

  upgradeButton() {
    let success: boolean;
    if(this.selectedItem.level < 4) {
      if(this.stones.blood_stone > 0) {
      this.upgradeItem(this.selectedItem);
    } else {
      console.log("Not enough stones");
    }
  } else if (this.selectedItem.level == 4 || this.selectedItem.level == 9) {
    if(this.stones.life_stone > 0) {
      this.upgradeItem(this.selectedItem);
    } else {
      console.log("Not enough stones");
    }
  } else if (this.selectedItem.level > 4 && this.selectedItem.level < 9) {
    if(this.stones.heart_stone > 0) {
      this.upgradeItem(this.selectedItem);
    } else {
      console.log("Not enough stones");
    }
  } else if (this.selectedItem.level > 9) {
      if(this.stones.soul_stone > 0) {
      this.upgradeItem(this.selectedItem);
    } else {
      console.log("Not enough stones");
    }
    }
  }

  upgradeItem(item: any) {
    this.blacksmithService.upgradeItem(item.item_id, sessionStorage.getItem('char_id')).subscribe((result: boolean) => {
      if(result) {
        console.log("Upgrade was a success");
      } else {
        console.log("Upgrade did not succeed");
      }
    });
  }
}
