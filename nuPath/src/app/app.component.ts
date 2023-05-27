import { Component } from '@angular/core';
import { AngularFirestore } from '@angular/fire/compat/firestore';
//import { AngularFirestore } from '@angular/fire/firestore';
import { MatDialog } from '@angular/material/dialog';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'nuPath';


  constructor(private dialog: MatDialog, private store: AngularFirestore) {}


  
}

