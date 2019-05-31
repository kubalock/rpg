import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GuildService {
  public API = '//localhost:8080';
  public GUILD_API = this.API + '/guilds/';

  constructor(private http: HttpClient) { }

  createGuild(name: String, description: String, id: String) {
    return this.http.post(this.API + '/newGuild/' + name + '/' + description + '/' + id);
  }

  getAllGuilds() {
    return this.http.get(this.API + '/getAllGuilds');
  }

  getGuild(id: String) {
    return this.http.get(this.API + '/getGuild/' + id);
  }

  deleteGuild(id: String) {
    return this.http.delete(this.API + /deleteGuild/ + id);
  }

  joinGuild(id: String, myId: String) {
    return this.http.put(this.API + '/joinGuild/' + id + '/' + myId);
  }

  leaveGuild(id: String) {
    return this.http.put(this.API + '/leaveGuild/' + id);
  }

  attackGuild(ourId: String, theirId: String) {
    return this.http.get(this.API + '/guildWar/' + ourId + '/' + theirId);
  }


}
