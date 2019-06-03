import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BlacksmithService {
  public API = '//localhost:8080';

  constructor(private http: HttpClient) { }

  getCharStones(id: String) {
      return this.http.get(this.API + '/getCharStones/' + id);
  }

  upgradeItem(id: stirng, char_id: string) {
    return this.http.get(this.API + '/upgradeItem/' + id + '/' + char_id);
  }
}
