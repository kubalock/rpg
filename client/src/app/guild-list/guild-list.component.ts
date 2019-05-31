import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { GuildService } from '../shared/guild/guild.service';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-guild-list',
  templateUrl: './guild-list.component.html',
  styleUrls: ['./guild-list.component.css']
})
export class GuildListComponent implements OnInit {

  guilds: Array<any>;

  constructor(private guildService: GuildService,
              private router: Router,
              private route: ActivatedRoute,
              private appComponent: AppComponent) { }

  ngOnInit() {
    if(sessionStorage.getItem('user_id') == 'undefined') {
      this.router.navigate(['/index']);
    }
    this.guildService.getAllGuilds().subscribe((guilds: any) => {
      this.guilds = guilds;
    });
    this.appComponent.getResources();
  }

}
