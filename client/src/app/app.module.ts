import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';

import { MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatToolbarModule, MatRadioModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { FormsModule } from '@angular/forms';

import { OverlayModule } from '@angular/cdk/overlay';

import { IndexComponent } from './index/index.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CharacterInfoComponent } from './character-info/character-info.component';
import { CharacterMasteryComponent } from './character-mastery/character-mastery.component';
import { MasteryComponent } from './mastery/mastery.component';
import { ArmoryComponent } from './armory/armory.component';
import { CharacterSearchComponent } from './character-search/character-search.component';
import { CharacterSelectedComponent } from './character-selected/character-selected.component';
import { ExploreComponent } from './explore/explore.component';
import { ExploreResultComponent } from './explore-result/explore-result.component';
import { ShopComponent } from './shop/shop.component';
import { GuildComponent } from './guild/guild.component';
import { GuildListComponent } from './guild-list/guild-list.component';
import { GuildInfoComponent } from './guild-info/guild-info.component';

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    LoginComponent,
    RegisterComponent,
    DashboardComponent,
    CharacterInfoComponent,
    CharacterMasteryComponent,
    MasteryComponent,
    ArmoryComponent,
    CharacterSearchComponent,
    CharacterSelectedComponent,
    ExploreComponent,
    ExploreResultComponent,
    ShopComponent,
    GuildComponent,
    GuildListComponent,
    GuildInfoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    MatRadioModule,
    FormsModule,
    OverlayModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
