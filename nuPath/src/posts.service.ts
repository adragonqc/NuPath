// Call a method to get the data.

import { Injectable } from '@angular/core';
import {
  AngularFirestore,
  QuerySnapshot
} from '@angular/fire/firestore';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators';
import {
  PostResponse,
} from 'src/app/models/post-response.interface';//change

@Injectable()
export class PostsService {
  constructor(private firestore: AngularFirestore) {}

  getPosts(): Observable<PostResponse[]> {
    return this.firestore
      .collection<PostResponse>('posts')
      .get()
      .pipe(
        map((query: QuerySnapshot<PostResponse>) => {
          return query.docs.map(doc => {
            const post = doc.data();
            post.id = doc.id;
            return post;
          });
        })
      );
  }
}