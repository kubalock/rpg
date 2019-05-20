import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExplorationService {
  public API = '//localhost:8080';

  constructor(private http: HttpClient) { }

  explore(level: String, id: String) {
    return this.http.get(this.API + '/explore/' + level + '/' + id);
  }
}
