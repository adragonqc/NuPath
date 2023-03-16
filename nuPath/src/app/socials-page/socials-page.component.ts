import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-socials-page',
  templateUrl: './socials-page.component.html',
  styleUrls: ['./socials-page.component.css']
})


export class SocialsPageComponent implements OnInit{

  usernameArray!: Array<String>;
  userArray!: Array<User>;

  ngOnInit(): void {
    fetch("http://35.188.8.151:80/GetAllUserNames")
    .then((response) => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.text();
    })
    .then((content) => {
      this.usernameArray = content.split(", ");
      this.usernameArray.pop()
      this.usernameArray.forEach((username) => {
        console.log(username)
        var newUser = new User(username,"","","");

        
        // fetch displayName
        fetch("http://35.188.8.151:80/ReturnDisplayName?Username=" + username, {
        })
        .then((response) => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          return response.text();
        })
        .then((content) => {
          newUser.displayName = content
        });
        
        // fetch email
        fetch("http://35.188.8.151:80/ReturnContactInfo?Username=" + username, {
        })
        .then((response) => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          return response.text();
        })
        .then((content) => {
          newUser.email = content
        });

        // fetch interests
        fetch("http://35.188.8.151:80/ReturnInterests?Username=" + username, {
        })
        .then((response) => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          return response.text();
        })
        .then((content) => {
          newUser.interests = content
          this.userArray.push(newUser)
        });
      })
    });
  }
}

export class User {
  username: String = "";
  displayName: String = "";
  email: String = "";
  interests: String = "";

  constructor(username: String, displayName: String, email: String, interests: String) {
    this.username = username;
    this.displayName = displayName;
    this.email = email;
    this.interests = interests;
  }

}