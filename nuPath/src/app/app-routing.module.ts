import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AcademicPageComponent } from './academic-page/academic-page.component';
import { CalendarPageComponent } from './calendar-page/calendar-page.component';
import { CatalystPageComponent } from './catalyst-page/catalyst-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { LeaderboardPageComponent } from './leaderboard-page/leaderboard-page.component';
import { NearNovolandPageComponent } from './nearnovoland-page/nearnovoland-page.component';
import { NovolandPageComponent } from './novoland-page/novoland-page.component';
import { NulifePageComponent } from './nulife-page/nulife-page.component';
import { SocialsPageComponent } from './socials-page/socials-page.component';

//test
import { DormPageComponent } from './dorm-page/dorm-page.component';
import { PackingListPageComponent } from './packinglist-page/packinglist-page.component';
import { FacilitiesPageComponent } from './facilities-page/facilities-page.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomePageComponent },
  { path: 'catalyst', component: CatalystPageComponent },
  { path: 'leaderboard', component: LeaderboardPageComponent},
  { path: 'nulife', component: NulifePageComponent },
  { path: 'calendar', component: CalendarPageComponent },
  { path: 'nearnovoland', component: NearNovolandPageComponent},
  { path: 'socials', component: SocialsPageComponent},
  { path: 'academic', component: AcademicPageComponent},
  { path: 'novoland', component: NovolandPageComponent},

  //test
  { path: 'dorms', component: DormPageComponent},
  { path: 'packinglist', component: PackingListPageComponent}
  { path: 'facilities', component: FacilitiesPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
