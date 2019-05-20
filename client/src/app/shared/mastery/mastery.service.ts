import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MasteryService {
  public API = '//localhost:8080';
  public MASTERY_API = this.API + '/masteries/';

  constructor(private http: HttpClient) { }

  getMasteries(id: String) {
    return this.http.get(this.API + '/getMasteries/' + id);
  }

  getListOfMasteries() {
    return this.http.get(this.API + '/getAllMasteries/');
  }

  getAvailableMasteries(id: String) {
    return this.http.get(this.API + '/getAvailableMasteries/' + id);
  }

  updateMastery(mastery: any) {
    return this.http.put(this.API + '/updateMastery/' + mastery.id + '/' + mastery.mastery_level, mastery);
  }

  selectMastery(mastery_id: String, char_id: String) {
    return this.http.post(this.API + '/selectMastery/' + char_id + '/' + mastery_id);
  }
}
