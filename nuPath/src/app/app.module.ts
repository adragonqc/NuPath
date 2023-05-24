import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
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

import { FormsModule } from '@angular/forms';
import { NewPostComponent } from './new-post/new-post.component';

/*
//import { AngularFireModule } from '@angular/fire';
import { AngularFireModule } from '@angular/fire';
//import { AngularFireAnalyticsModule } from '@angular/fire/analytics';
import { AnalyticsModule } from '@angular/fire/analytics';
//import { AngularFirestoreModule } from '@angular/fire/firestore';
import { FirestoreModule } from '@angular/fire/firestore';
*/

import { provideFirebaseApp, getApp, initializeApp } from '@angular/fire/app'
import { getFirestore, provideFirestore } from '@angular/fire/firestore'

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
    RegisterPageComponent,
    LogoutComponent,

    ForumComponent,
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

    /*
    AngularFireModule.initializeApp(< firebaseConfig >),
    AngularFireAnalyticsModule,
    AngularFirestoreModule
    */
   
    provideFirebaseApp(() => initializeApp( firebaseConfig )),
    provideFirestore(() => getFirestore())
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }

/**
 * this goes here???
 */
const firebaseConfig = {
  apiKey: "AIzaSyBT7-20UcmSAAL1SLJotFwqhp1Y7IUQFuY",
  authDomain: "nupath-7d671.firebaseapp.com",
  projectId: "nupath-7d671",
  storageBucket: "nupath-7d671.appspot.com",
  messagingSenderId: "377658026566",
  appId: "1:377658026566:web:7bfdcdd4365534bf077847",
  measurementId: "G-266VMGNZZV"
};