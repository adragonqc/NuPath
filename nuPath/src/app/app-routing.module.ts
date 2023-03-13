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
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { SocialsPageComponent } from './socials-page/socials-page.component';

//nulife page contents
import { DormPageComponent } from './dorm-page/dorm-page.component';
import { PackingListPageComponent } from './packinglist-page/packinglist-page.component';
import { FacilitiesPageComponent } from './facilities-page/facilities-page.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

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
  { path: 'profile', component: ProfilePageComponent},

  //nulife page routing
  //{ path: '', redirectTo: 'dorms', pathMatch: 'full', outlet: 'nulife-contents' }, //nulife page default to dorms??
  { path: 'dorms', component: DormPageComponent, outlet: 'nulife-contents' },
  { path: 'packinglist', component: PackingListPageComponent, outlet: 'nulife-contents' },
  { path: 'facilities', component: FacilitiesPageComponent, outlet: 'nulife-contents' },

  { path: '**', component: PageNotFoundComponent }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
