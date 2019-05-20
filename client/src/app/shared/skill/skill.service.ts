import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SkillService {
  public API = '//localhost:8080';

  constructor(private http: HttpClient) { }

  getMasterySkills(mastery_id: String) {
    return this.http.get(this.API + '/getMasterySkills/' + mastery_id);
  }

  insertSkills(char_id: String, mastery_id: String) {
    return this.http.post(this.API + '/insertSkills/' + char_id + '/' + mastery_id);
  }

  getHeroSkills(char_id: String) {
    return this.http.get(this.API + '/getCharSkills/' + char_id);
  }

  getSkill(skill_id: String) {
    return this.http.get(this.API + '/getSkillInfo/' + skill_id);
  }

  saveSkill(skill: any) {
    return this.http.put(this.API + '/updateSkill/' + skill.char_skill_id + '/' + skill.level, skill);
  }
}
