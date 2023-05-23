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
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { LogoutComponent } from './logout/logout.component';
import { AuthGuard } from './auth.guard';
import { LoginGuard } from './login.guard';

import { ForumComponent } from './forum/forum.component';
import { NewPostComponent } from './new-post/new-post.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full'},
  { path: 'home', component: HomePageComponent, canActivate: [AuthGuard]},
  { path: 'catalyst', component: CatalystPageComponent, canActivate: [AuthGuard] },
  { path: 'leaderboard', component: LeaderboardPageComponent, canActivate: [AuthGuard] },
  { path: 'calendar', component: CalendarPageComponent, canActivate: [AuthGuard] },
  { path: 'nearnovoland', component: NearNovolandPageComponent, canActivate: [AuthGuard] },
  { path: 'socials', component: SocialsPageComponent, canActivate: [AuthGuard] },
  { path: 'profile/:username', component: ProfilePageComponent, canActivate: [AuthGuard] },
  { path: 'dorms', component: DormPageComponent, canActivate: [AuthGuard] },
  { path: 'facilities', component: FacilitiesPageComponent, canActivate: [AuthGuard] },
  { path: 'packinglist', component: PackinglistPageComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginPageComponent, canActivate: [LoginGuard] },
  { path: 'register', component: RegisterPageComponent, canActivate: [LoginGuard] },
  { path: 'logout', component: LogoutComponent, canActivate: [AuthGuard] },


  //nulife page routing
  { path: 'dorms', component: DormPageComponent, canActivate: [AuthGuard] /*outlet: "nulife-contents"*/},
  { path: 'packinglist', component: PackinglistPageComponent, canActivate: [AuthGuard] /*outlet: "nulife-contents"*/},
  { path: 'facilities', component: FacilitiesPageComponent, canActivate: [AuthGuard] /*outlet: "nulife-contents"*/},

  { path: 'forum', component: ForumComponent, canActivate: [AuthGuard] },
  { path: 'newPost', component: NewPostComponent, canActivate: [AuthGuard] },

  { path: '**', component: PageNotFoundComponent }  //always keep at end
  
];

RouterModule.forRoot(routes, {scrollPositionRestoration: 'enabled'});

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
