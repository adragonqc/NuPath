import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './home-page/home-page.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { CatalystPageComponent } from './catalyst-page/catalyst-page.component';
import { LeaderboardPageComponent } from './leaderboard-page/leaderboard-page.component';
import { NulifePageComponent } from './nulife-page/nulife-page.component';
import { CalendarPageComponent } from './calendar-page/calendar-page.component';
import { NearNovolandPageComponent } from './nearnovoland-page/nearnovoland-page.component';
import { AcademicPageComponent } from './academic-page/academic-page.component';
import { SocialsPageComponent } from './socials-page/socials-page.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    NavBarComponent,
    CatalystPageComponent,
    LeaderboardPageComponent,
    NulifePageComponent,
    CalendarPageComponent,
    NearNovolandPageComponent,
    AcademicPageComponent,
    SocialsPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
