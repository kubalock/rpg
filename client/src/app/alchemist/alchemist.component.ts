import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemService } from '../shared/item/item.service';
import { AppComponent } from '../app.component';
import { ShardService } from '../shared/shard/shard.service';

@Component({
  selector: 'app-alchemist',
  templateUrl: './alchemist.component.html',
  styleUrls: ['./alchemist.component.css']
})
export class AlchemistComponent implements OnInit {

  shards: Array<any>;
  sameShards: Array<any>;
  assembleShards: Array<any>;

  shard: any = {};

  armory: Array<any>;
  item: any = {};

  selectedShard: any = {};

  assembleTrue = false;
  enchanceTrue = false;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private itemService: ItemService,
              private appComponent: AppComponent,
              private shardService: ShardService) { }

  ngOnInit() {
    if(sessionStorage.getItem('user_id') == 'undefined') {
      this.router.navigate(['/index']);
    }
    if(sessionStorage.getItem('hero') != null) {
      this.shardService.getCharShards(sessionStorage.getItem('char_id')).subscribe((result: any) => {
        this.shards = result;
      });

      this.appComponent.getResources();
    } else {
      this.router.navigate(['/character']);
    }
  }

  enchanceItem(item: any) {
    this.shardService.enchanceItem(this.selectedShard.char_shards_id, item.item_id).subscribe();
    this.router.navigate(['/dashboard']);
  }

  assembleTwo(shard: any) {
    this.shardService.assembleShards(this.selectedShard.char_shards_id, shard.char_shards_id).subscribe();
    this.shardService.deleteShard(shard.char_shards_id).subscribe();
    this.router.navigate(['/dashboard']);
  }

  assemble(shard: any) {
    this.selectedShard = shard;
    this.shardService.getAssembleShards(sessionStorage.getItem('char_id'), this.selectedShard.shard.shard_id, this.selectedShard.char_shards_id).subscribe((result: any) => {
      this.assembleShards = result;
    });
    this.assembleTrue = true;
    this.enchanceTrue = false;
  }

  enchance(shard: any) {
    this.selectedShard = shard;
    this.itemService.getItemToEnchance(sessionStorage.getItem('char_id'), this.selectedShard.shard.shard_id).subscribe((result: any) => {
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
    this.enchanceTrue = true;
    this.assembleTrue = false;
  }

  showShardDetails(shard: any) {
  this.shard = this.shards.find(shardd => shardd == shard);
  return this.shardService.showShardDetails(this.shard);
  }

  showItemDetails(item: string, where: string, requestLevel: string) {
    this.item = this.armory.find(iten => iten == item);
    let array = this.itemService.showItemDetails(this.item, where, requestLevel);
    if(this.item.shard_id != null) {
      array.push("-------");
      this.shard = this.shards.find(shardd => shardd == this.item.shard_id);
      for(let line of this.shardService.showShardDetails(this.item.shard_id)) {
        array.push(line);
      }
    }
    return array;
  }
}
