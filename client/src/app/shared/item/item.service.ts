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
}
