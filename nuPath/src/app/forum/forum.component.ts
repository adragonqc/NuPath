/**
 * New Post page TypeScript
 * @author Sydney Silverman
*/

import { Component } from '@angular/core';
import { inject } from '@angular/core';
import { Firestore, collectionData, collection } from '@angular/fire/firestore';
import { Observable, Timestamp } from 'rxjs';

interface Post {
  name: string,
  message: string,
  time_stamp: string,

  //comments: ??,
  //likes: ??
  
}

@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.css']
})
export class ForumComponent {
  posts$: Observable<Post[]>;
  firestore: Firestore = inject(Firestore);

  constructor(){
    const postCollection = collection(this.firestore, 'posts');
    this.posts$ = collectionData(collection);
  }

  /*
  onSubmit() {
    // Add the post to the array
    this.posts.push({ displayName: this.displayName, message: this.message });

    // Reset the form after submission
    this.displayName = '';
    this.message = '';
  }
  */
}


