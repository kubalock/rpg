import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { CharacterService } from '../shared/character/character.service';
import { UserService } from '../shared/user/user.service';
import { ExplorationService } from '../shared/exploration/exploration.service';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-explore-result',
  templateUrl: './explore-result.component.html',
  styleUrls: ['./explore-result.component.css']
})
export class ExploreResultComponent implements OnInit {

  public html: string = '<span class="btn btn-danger">Your HTML here</span>';

  sub: Subscription;

  char_id: string;
  no: string;

  fightResult: Array<any>;

  heroInfo: any = [];
  monstersInfo: any = [];
  monsterInfo: any = [];

  let: any;

  first: any = 17;
  last: any = 33;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private explorationService: ExplorationService) { }

  ngOnInit() {
    if(sessionStorage.getItem('user_id') == 'undefined') {
      this.router.navigate(['/index']);
    }
    this.char_id = sessionStorage.getItem('char_id');
    this.sub = this.route.params.subscribe(params => {
      this.no = params['no'];
      this.explorationService.explore(this.no, this.char_id).subscribe((result: any) => {
        this.fightResult = result;
        this.heroInfo = this.fightResult.slice(1,17);
        console.log(this.fightResult[0]);
        for(let i = 1; i <= this.fightResult[0]; i++) {
          this.monsterInfo = this.fightResult.slice(this.first, this.last);
          this.first = this.first + 16;
          this.last = this.last + 16;
          this.monstersInfo.push(this.monsterInfo);
        }
        this.last = this.last - 16;
        this.fightResult = this.fightResult.splice(this.last);
      });
    });
  }

}
