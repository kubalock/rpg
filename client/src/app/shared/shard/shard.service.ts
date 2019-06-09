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
}
