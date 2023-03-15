import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CalendarPageComponent } from './calendar-page/calendar-page.component';
import { CatalystPageComponent } from './catalyst-page/catalyst-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { LeaderboardPageComponent } from './leaderboard-page/leaderboard-page.component';
import { NearNovolandPageComponent } from './nearnovoland-page/nearnovoland-page.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { SocialsPageComponent } from './socials-page/socials-page.component';
import { DormPageComponent } from './dorm-page/dorm-page.component';
import { FacilitiesPageComponent } from './facilities-page/facilities-page.component';
import { PackinglistPageComponent } from './packinglist-page/packinglist-page.component';

import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomePageComponent },
  { path: 'catalyst', component: CatalystPageComponent },
  { path: 'leaderboard', component: LeaderboardPageComponent},
  { path: 'calendar', component: CalendarPageComponent },
  { path: 'nearnovoland', component: NearNovolandPageComponent},
  { path: 'socials', component: SocialsPageComponent},
  { path: 'profile', component: ProfilePageComponent},
  { path: 'dorms', component: DormPageComponent },
  { path: 'facilities', component: FacilitiesPageComponent },
  { path: 'packinglist', component: PackinglistPageComponent },

  { path: '**', component: PageNotFoundComponent }  //always keep at end
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
