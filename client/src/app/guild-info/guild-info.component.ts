import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { CharacterService } from '../shared/character/character.service';
import { GuildService } from '../shared/guild/guild.service';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-guild-info',
  templateUrl: './guild-info.component.html',
  styleUrls: ['./guild-info.component.css']
})
export class GuildInfoComponent implements OnInit {

  sub: Subscription;

  haveGuild = false;

  guild: any = {};
  heroes: Array<any>;
  guildSize: any;
  id: any;
  myId: any;
  myGuild: any = {};

  ourInfo: any = [];
  ourHero: any = [];
  yourInfo: any = [];
  yourHero: any = [];

  first: any = 2;
  last: any = 18;

  warResult: Array<any>;

  constructor(private guildService: GuildService,
              private router: Router,
              private route: ActivatedRoute,
              private characterService: CharacterService,
              private appComponent: AppComponent) { }

  ngOnInit() {
    if(sessionStorage.getItem('user_id') == 'undefined') {
      this.router.navigate(['/index']);
    }
    this.sub = this.route.params.subscribe(params => {
    this.myId = sessionStorage.getItem('char_id');
    this.id = params['no'];
    this.guildService.getGuild(this.id).subscribe((guild: any) => {
      this.guild = guild;
      this.characterService.getGuildHeroes(this.guild.guild_id).subscribe((heroes: any) => {
        this.heroes = heroes;
        this.guildSize = this.heroes.length;
        this.characterService.getHeroGuild(sessionStorage.getItem('hero')).subscribe((guild: any) => {
          if(guild != null) {
            this.haveGuild = true;
            this.myGuild = guild;
          }
        });
      });
    });
  });
  this.appComponent.getResources();
  }

  joinGuild() {
    this.guildService.joinGuild(this.guild.guild_id, this.myId).subscribe();

    this.router.navigate(['/dashboard']);
  }

  attackGuild() {
    this.guildService.attackGuild(this.myGuild.guild_id, this.guild.guild_id).subscribe((result: any) => {
      this.warResult = result;
      for(let e = 1; e <= this.warResult[0]; e++) {
      this.ourHero = this.warResult.slice(this.first ,this.last);
      this.first = this.first + 16;
      this.last = this.last + 16;
      this.ourInfo.push(this.ourHero);
      }
      for(let i = 1; i <= this.warResult[1]; i++) {
        this.yourHero = this.warResult.slice(this.first, this.last);
        this.first = this.first + 16;
        this.last = this.last + 16;
        this.yourInfo.push(this.yourHero);
      }
      this.last = this.last - 16;
      this.warResult = this.warResult.splice(this.last);
    });

  }
}
