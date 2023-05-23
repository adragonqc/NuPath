/**
 * New Post page TypeScript
 * @author Sydney Silverman
*/

import { Component } from '@angular/core';

@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.css']
})
export class ForumComponent {
  displayName = '';
  message = '';
  posts: { displayName: string, message: string }[] = [];

  onSubmit() {
    // Add the post to the array
    this.posts.push({ displayName: this.displayName, message: this.message });

    // Reset the form after submission
    this.displayName = '';
    this.message = '';
  }
}

export class Post {
  displayName = '';
  message = '';
  posts: { displayName: string, message: string }[] = [];

}
