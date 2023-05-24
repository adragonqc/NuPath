import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { NgxFileDropModule } from 'ngx-file-drop';
import { MatButtonModule } from '@angular/material/button';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './home-page/home-page.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { CatalystPageComponent } from './catalyst-page/catalyst-page.component';
import { LeaderboardPageComponent } from './leaderboard-page/leaderboard-page.component';
import { CalendarPageComponent } from './calendar-page/calendar-page.component';
import { NearNovolandPageComponent } from './nearnovoland-page/nearnovoland-page.component';
import { SocialsPageComponent } from './socials-page/socials-page.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { DormPageComponent } from './dorm-page/dorm-page.component';
import { FacilitiesPageComponent } from './facilities-page/facilities-page.component';
import { FooterComponent } from './footer/footer.component';
import { PackinglistPageComponent } from './packinglist-page/packinglist-page.component';
import { ForumComponent } from './forum/forum.component';
import { CanvasComponent } from './canvas/canvas.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { HttpClientModule } from '@angular/common/http';
import { LogoutComponent } from './logout/logout.component';
import { AuthGuard } from './auth.guard';
import { NewPostComponent } from './new-post/new-post.component';


import { NgxsReduxDevtoolsPluginModule } from '@ngxs/devtools-plugin';
import { NgxsFormPluginModule } from '@ngxs/form-plugin';
import { NgxsLoggerPluginModule } from '@ngxs/logger-plugin';
import { NgxsStoragePluginModule } from '@ngxs/storage-plugin';
import { NgxsModule } from '@ngxs/store';
import { NgxsResetPluginModule } from 'ngxs-reset-plugin';


@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    NavBarComponent,
    CatalystPageComponent,
    LeaderboardPageComponent,
    CalendarPageComponent,
    NearNovolandPageComponent,
    SocialsPageComponent,
    ProfilePageComponent,
    LoginPageComponent,
    PageNotFoundComponent,
    FooterComponent,
    PackinglistPageComponent,
    CanvasComponent,

    //nulife page contents
    DormPageComponent,
    PackinglistPageComponent,
    FacilitiesPageComponent,
    ForumComponent,
    RegisterPageComponent,
    LogoutComponent,
    NewPostComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    MatSelectModule,
    MatOptionModule,
    MatButtonModule,
    NgxFileDropModule,
    HttpClientModule,
    NgxsModule.forRoot([AppState, SettingsState, UserState], {
      developmentMode: !environment.production
    }),
    NgxsFormPluginModule.forRoot(),
    NgxsStoragePluginModule.forRoot({ key: 'settings' }),
    NgxsResetPluginModule.forRoot(),
    NgxsLoggerPluginModule.forRoot({ disabled: environment.production }),
    NgxsReduxDevtoolsPluginModule.forRoot({ disabled: environment.production }),
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
