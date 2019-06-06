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
}
