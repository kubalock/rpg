import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { CharacterService } from '../shared/character/character.service';
import { UserService } from '../shared/user/user.service';
import { MasteryService } from '../shared/mastery/mastery.service';
import { SkillService } from '../shared/skill/skill.service';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-mastery',
  templateUrl: './mastery.component.html',
  styleUrls: ['./mastery.component.css']
})
export class MasteryComponent implements OnInit {

  hero: any = {};

  firstMastery = false;
  firstMasteryChosen = false;
  secondMastery = false;
  secondMasteryChosen = false;

  masteryPointSpent = false;

  masteryPoints = false;

  masteryOne: any = {};
  masteryOneLevel: any = {};

  masteryTwo: any = {};
  masteryTwoLevel: any = {};

  updatedMastery: any = {};

  allMasteries: Array<any>;
  availableMasteries: Array<any>;

  skill: any = {};
  skillsOne: any = [];
  skillsTwo: any = [];

  skills: any = [];
  masteries: any = [];


  constructor(private characterService: CharacterService,
              private router: Router,
              private route: ActivatedRoute,
              private userService: UserService,
              private masteryService: MasteryService,
              private skillService: SkillService,
              private appComponent: AppComponent) { }

  ngOnInit() {
    if(sessionStorage.getItem('user_id') == 'undefined') {
      this.router.navigate(['/index']);
    }
    if(sessionStorage.getItem('hero') != null) {
      this.characterService.getHeroName(sessionStorage.getItem('hero')).subscribe((result: any) => {
        this.hero = result;
        if(this.hero.mastery_points > 0) {
          this.masteryPoints = true;
        }
        if(this.hero.level > 1) {
          this.firstMastery = true;
          this.masteryService.getMasteries(this.hero.char_id).subscribe((masteries: any) => {
            this.masteries = masteries;
            if(this.masteries[0] != null) {
              this.masteryOneLevel = this.masteries[0];
              this.masteryOne = this.masteries[0].mastery;
              this.firstMasteryChosen = true;
              this.skillService.getHeroSkills(this.hero.char_id).subscribe((skills: any) => {
                this.skills = skills;
                for (var skill of this.skills) {
                  if(skill.skill.mastery.mastery_id == this.masteryOne.mastery_id) {
                    this.skillsOne.push(skill);
                  }
                } });
              } else {
              this.masteryService.getListOfMasteries().subscribe((result: any) => {
                this.allMasteries = result;
              });
            } });
          }
            if(this.hero.level > 7) {
              this.secondMastery = true;
              if(this.masteries[1] != null) {
                this.masteryTwoLevel = this.masteries[1];
                this.masteryTwo = this.masteries[1].mastery;
                this.secondMasteryChosen = true;
            for (var skill of this.skills) {
              if(skill.skill.mastery.mastery_id == this.masteryTwo.mastery_id) {
                this.skillsTwo.push(skill);
              }
            }
          } else {
          this.masteryService.getAvailableMasteries(this.hero.char_id).subscribe((result: any) => {
            this.availableMasteries = result;
          });
        }
          }
        });
              this.appComponent.getResources();
  }
    else {
      this.router.navigate(['/character']);
    }
}


  levelMasteryOne() {
    this.masteryOneLevel.mastery_level = this.masteryOneLevel.mastery_level +1;
    this.hero.strength = this.hero.strength + this.masteryOne.strength_level;
    this.hero.agility = this.hero.agility + this.masteryOne.agility_level;
    this.hero.intelligence = this.hero.intelligence + this.masteryOne.intelligence_level;
    this.hero.health = this.hero.health + this.masteryOne.health_level;
    this.hero.endurance = this.hero.endurance + this.masteryOne.endurance_level;
    this.hero.mastery_points = this.hero.mastery_points - 1;
    if(this.hero.mastery_points < 1) {
      this.masteryPoints = false;
    }
    this.masteryPointSpent = true;
    //console.log(this.masteryOneLevel);
  }

  levelMasteryTwo() {
    this.masteryTwoLevel.mastery_level = this.masteryTwoLevel.mastery_level +1;
    this.hero.strength = this.hero.strength + this.masteryTwo.strength_level;
    this.hero.agility = this.hero.agility + this.masteryTwo.agility_level;
    this.hero.intelligence = this.hero.intelligence + this.masteryTwo.intelligence_level;
    this.hero.health = this.hero.health + this.masteryTwo.health_level;
    this.hero.endurance = this.hero.endurance + this.masteryTwo.endurance_level;
    this.hero.mastery_points = this.hero.mastery_points - 1;
    if(this.hero.mastery_points < 1) {
      this.masteryPoints = false;
    }
    this.masteryPointSpent = true;
    //console.log(this.masteryTwoLevel);
  }

  submitMastery() {
    this.characterService.updateHero(this.hero).subscribe();
    this.updatedMastery.id = this.masteryOneLevel.char_mastery_id;
    this.updatedMastery.mastery_level = this.masteryOneLevel.mastery_level;
    this.masteryService.updateMastery(this.updatedMastery).subscribe();
    for(let skill of this.skillsOne) {
      this.skillService.saveSkill(skill).subscribe();
    }
    if(this.hero.level > 7) {
      this.updatedMastery.id = this.masteryTwoLevel.char_mastery_id;
      this.updatedMastery.mastery_level = this.masteryTwoLevel.mastery_level;
      this.masteryService.updateMastery(this.updatedMastery).subscribe();
      for(let skill of this.skillsOne) {
        this.skillService.saveSkill(skill).subscribe();
      }
    }
    this.masteryPointSpent = false;
  }

  selectMastery(id: String) {
    this.masteryService.selectMastery(id, this.hero.char_id).subscribe();
    this.skillService.insertSkills(this.hero.char_id, id).subscribe();
    this.router.navigate(['/dashboard']);
  }

  calcBonusHPOne() {
    return this.masteryOneLevel.mastery_level * this.masteryOne.health_level;
  }

  calcBonusStrengthOne() {
    return this.masteryOneLevel.mastery_level * this.masteryOne.strength_level;
  }

  calcBonusAgilityOne() {
    return this.masteryOneLevel.mastery_level * this.masteryOne.agility_level;
  }

  calcBonusEnduranceOne() {
    return this.masteryOneLevel.mastery_level * this.masteryOne.endurance_level;
  }

  calcBonusIntelligenceOne() {
    return this.masteryOneLevel.mastery_level * this.masteryOne.intelligence_level;
  }

  calcBonusHPTwo() {
    return this.masteryTwoLevel.mastery_level * this.masteryTwo.health_level;
  }

  calcBonusStrengthTwo() {
    return this.masteryTwoLevel.mastery_level * this.masteryTwo.strength_level;
  }

  calcBonusAgilityTwo() {
    return this.masteryTwoLevel.mastery_level * this.masteryTwo.agility_level;
  }

  calcBonusEnduranceTwo() {
    return this.masteryTwoLevel.mastery_level * this.masteryTwo.endurance_level;
  }

  calcBonusIntelligenceTwo() {
    return this.masteryTwoLevel.mastery_level * this.masteryTwo.intelligence_level;
  }

  levelSkill(id: String, which: String) {
    if(which == "one") {
      for(let skill of this.skillsOne) {
        if(skill.char_skill_id == id) {
        skill.level = skill.level + 1;
        this.hero.attack_speed = this.hero.attack_speed + skill.skill.attack_speed;
        this.hero.min_damage = this.hero.min_damage + skill.skill.damage;
        this.hero.max_damage = this.hero.max_damage + skill.skill.damage;
        this.hero.intelligence_percent = this.hero.intelligence_percent + skill.skill.intelligence;
        this.hero.min_electric = this.hero.min_electric + skill.skill.cold_ele_dmg;
        this.hero.max_electric = this.hero.max_electric + skill.skill.cold_ele_dmg;
        this.hero.min_cold = this.hero.min_cold + skill.skill.cold_ele_dmg;
        this.hero.max_cold = this.hero.max_cold + skill.skill.cold_ele_dmg;
        this.hero.hp_percent = this.hero.hp_percent + skill.skill.hp;
        this.hero.block_chance = this.hero.block_chance + skill.skill.block;
        this.hero.fire_percent = this.hero.fire_percent + skill.skill.fire_percent;
        this.hero.endurance_percent = this.hero.endurance_percent + skill.skill.endurance;
        this.hero.ignore_dmg_percent = this.hero.ignore_dmg_percent + skill.skill.ignore_dmg;
        this.hero.agility_percent = this.hero.agility_percent + skill.skill.agility;
      }
      }
    } else if (which == "two") {
      for(let skill of this.skillsTwo) {
        if(skill.char_skill_id == id) {
        skill.level = skill.level + 1;
        this.hero.attack_speed = this.hero.attack_speed + skill.skill.attack_speed;
        this.hero.min_damage = this.hero.min_damage + skill.skill.damage;
        this.hero.max_damage = this.hero.max_damage + skill.skill.damage;
        this.hero.intelligence_percent = this.hero.intelligence_percent + skill.skill.intelligence;
        this.hero.min_electric = this.hero.min_electric + skill.skill.cold_ele_dmg;
        this.hero.max_electric = this.hero.max_electric + skill.skill.cold_ele_dmg;
        this.hero.min_cold = this.hero.min_cold + skill.skill.cold_ele_dmg;
        this.hero.max_cold = this.hero.max_cold + skill.skill.cold_ele_dmg;
        this.hero.hp_percent = this.hero.hp_percent + skill.skill.hp;
        this.hero.block_chance = this.hero.block_chance + skill.skill.block;
        this.hero.fire_percent = this.hero.fire_percent + skill.skill.fire_percent;
        this.hero.endurance_percent = this.hero.endurance_percent + skill.skill.endurance;
        this.hero.ignore_dmg_percent = this.hero.ignore_dmg_percent + skill.skill.ignore_dmg;
        this.hero.agility_percent = this.hero.agility_percent + skill.skill.agility;
      }
      }
    }
    this.hero.mastery_points = this.hero.mastery_points - 1;
    this.masteryPointSpent = true;
    if(this.hero.mastery_points < 1) {
      this.masteryPoints = false;
    }
  }

  getSkillInfo(id: String, which: String) {
    if(which == "one") {
    for(let skill of this.skillsOne) {
      if(skill.skill.skill_id == id) {
        if(skill.skill.attack_speed) {
          return "Attack speed +" + (skill.skill.attack_speed * skill.level) + "%";
        }
        if(skill.skill.damage) {
          return "Damage +" + (skill.skill.damage * skill.level) + "<--- this needs to be change for percentages";
        }
        if(skill.skill.intelligence) {
          return "Intelligence +" + (skill.skill.intelligence * skill.level) + "%";
        }
        if(skill.skill.cold_ele_dmg) {
          return "Cold and Electric damage +" + (skill.skill.cold_ele_dmg * skill.level);
        }
        if(skill.skill.hp) {
          return "Health +" + (skill.skill.hp * skill.level) + "%";
        }
        if(skill.skill.block) {
          return "Block chance +" + (skill.skill.block * skill.level) + "%";
        }
        if(skill.skill.fire_percent) {
          return "Fire damage +" + (skill.skill.fire_percent * skill.level) + "%";
        }
        if(skill.skill.endurance) {
          return "Endurance +" + (skill.skill.endurance * skill.level) + "%";
        }
        if(skill.skill.ignore_dmg) {
          return "Ignore " + (skill.skill.ignore_dmg * skill.level) + "% of dmg";
        }
        if(skill.skill.agility) {
          return "Agility +" + (skill.skill.agility * skill.level) + "%";
        }
      }
    }
  } else if (which == "two") {
      for(let skill of this.skillsTwo) {
        if(skill.skill.skill_id == id) {
          if(skill.skill.attack_speed) {
            return "Attack speed +" + (skill.skill.attack_speed * skill.level) + "%";
          }
          if(skill.skill.damage) {
            return "Damage +" + (skill.skill.damage * skill.level) + "<--- this needs to be change for percentages";
          }
          if(skill.skill.intelligence) {
            return "Intelligence +" + (skill.skill.intelligence * skill.level) + "%";
          }
          if(skill.skill.cold_ele_dmg) {
            return "Cold and Electric damage +" + (skill.skill.cold_ele_dmg * skill.level);
          }
          if(skill.skill.hp) {
            return "Health +" + (skill.skill.hp * skill.level) + "%";
          }
          if(skill.skill.block) {
            return "Block chance +" + (skill.skill.block * skill.level) + "%";
          }
          if(skill.skill.fire_percent) {
            return "Fire damage +" + (skill.skill.fire_percent * skill.level) + "%";
          }
          if(skill.skill.endurance) {
            return "Endurance +" + (skill.skill.endurance * skill.level) + "%";
          }
          if(skill.skill.ignore_dmg) {
            return "Ignore " + (skill.skill.ignore_dmg * skill.level) + "% of dmg";
          }
          if(skill.skill.agility) {
            return "Agility +" + (skill.skill.agility * skill.level) + "%";
          }
        }
      }
    }
    }
}
