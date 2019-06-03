import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { MasteryComponent } from './mastery/mastery.component';
import { CharacterInfoComponent } from './character-info/character-info.component';
import { CharacterSelectedComponent } from './character-selected/character-selected.component';
import { CharacterSearchComponent } from './character-search/character-search.component';
import { ArmoryComponent } from './armory/armory.component';
import { ExploreComponent } from './explore/explore.component';
import { ExploreResultComponent } from './explore-result/explore-result.component';
import { GuildComponent } from './guild/guild.component';
import { GuildInfoComponent } from './guild-info/guild-info.component';
import { GuildListComponent } from './guild-list/guild-list.component';
import { BlacksmithComponent } from './blacksmith/blacksmith.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  {
    path: 'index',
    component: IndexComponent
  },
  {
    path: 'blacksmith',
    component: BlacksmithComponent
  },
  {
    path: 'guild/:no',
    component: GuildInfoComponent
  },
  {
    path: 'guild-list',
    component: GuildListComponent
  },
  {
    path: 'guild',
    component: GuildComponent
  },
  {
    path: 'explore/:no',
    component: ExploreResultComponent
  },
  {
    path: 'explore',
    component: ExploreComponent
  },
  {
    path: 'search',
    component: CharacterSearchComponent
  },
  {
    path: 'character/:id',
    component: CharacterSelectedComponent
  },
  {
    path: 'armory',
    component: ArmoryComponent
  },
  {
    path: 'mastery',
    component: MasteryComponent
  },
  {
    path: 'character',
    component: CharacterInfoComponent
  },
  {
    path: 'dashboard',
    component: DashboardComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
