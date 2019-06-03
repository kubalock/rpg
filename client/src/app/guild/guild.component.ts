import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { CharacterService } from '../shared/character/character.service';
import { GuildService } from '../shared/guild/guild.service';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-guild',
  templateUrl: './guild.component.html',
  styleUrls: ['./guild.component.css']
})
export class GuildComponent implements OnInit {

  haveGuild = false;
  guildLeader = false;

  guild: any = {};
  heroes: Array<any>;
  guildSize: any;

  constructor(private guildService: GuildService,
              private router: Router,
              private route: ActivatedRoute,
              private characterService: CharacterService,
              private appComponent: AppComponent) { }

  ngOnInit() {
    if(sessionStorage.getItem('user_id') == 'undefined') {
      this.router.navigate(['/index']);
    }
    this.characterService.getHeroGuild(sessionStorage.getItem('hero')).subscribe((guild: any) => {
        if(guild != null) {
          this.guild = guild;
          this.haveGuild = true;
          if(this.guild.leader_id == sessionStorage.getItem('char_id')) {
            this.guildLeader = true;
          }
          this.characterService.getGuildHeroes(this.guild.guild_id).subscribe((heroes: any) => {
            this.heroes = heroes;
            this.guildSize = this.heroes.length;
          });
        }
    });
    this.appComponent.getResources();
  }


  createGuild(form: NgForm) {
    this.guildService.createGuild(form.value.name, form.value.description, sessionStorage.getItem('char_id')).subscribe();

    this.router.navigate(['/dashboard']);
  }

  deleteGuild() {
    this.guildService.deleteGuild(this.guild.guild_id).subscribe();

    this.router.navigate(['/dashboard']);
  }

  leaveGuild() {
    this.guildService.leaveGuild(sessionStorage.getItem('char_id')).subscribe();

    this.router.navigate(['/dashboard']);
  }

}
