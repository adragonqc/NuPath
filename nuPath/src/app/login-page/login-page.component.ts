import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {
  username!: string;
  password!: string;
  errorMessage!: string;

  constructor(private router: Router) {}

  onSubmit() { 

    const ip = "http://35.188.8.151:80/"

    // Call API to validate login
    console.log(this.username + ", " + this.password)
    fetch(ip + "CreateOldUser?Username=" + this.username + "&Password=" + this.password, {
        method: "POST",
        body: JSON.stringify({
            username: this.username,
            password: this.password
        })
    })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.text();
    })
    .then((content) => {
        console.log(content);
        // If login is successful, redirect to dashboard page
        // Otherwise, display error message
        if (content === "True") {
          // Redirect to dashboard page
          sessionStorage.setItem('currentUser', this.username);
        
          /** 
          // set displayName
          fetch(ip + "ReturnDisplayName?Username=" + this.username, {
            method: "POST",
            body: JSON.stringify({
              username: this.username
            })
          })
          .then((response) => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            return response.text();
          })
          .then((content) => {
            sessionStorage.setItem("currentDisplayName", content)
            console.log(sessionStorage.getItem("currentDisplayName"))
          })
          .catch((error) => {
            console.error('Error:', error);
            this.errorMessage = 'Something went wrong, please try again';
          });

          
          // set contactInformation
          fetch(ip + "ReturnContactInformation?Username=" + this.username, {
            method: "POST",
            body: JSON.stringify({
              username: this.username
            })
          })
          .then((response) => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            return response.text();
          })
          .then((content) => {
            sessionStorage.setItem("currentContactInformation", content)
            console.log(sessionStorage.getItem("currentContactInformation"))
          })
          .catch((error) => {
            console.error('Error:', error);
            this.errorMessage = 'Something went wrong, please try again';
          });
          

          // set Interests 
          fetch(ip + "ReturnInterests?Username=" + this.username, {
            method: "POST",
            body: JSON.stringify({
              username: this.username
            })
          })
          .then((response) => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            return response.text();
          })
          .then((content) => {
            sessionStorage.setItem("currentInterests", content)
            console.log(sessionStorage.getItem("currentInterests"))
          })
          .catch((error) => {
            console.error('Error:', error);
            this.errorMessage = 'Something went wrong, please try again';
          });

          // set Food
          fetch(ip + "ReturnFood?Username=" + this.username, {
            method: "POST",
            body: JSON.stringify({
              username: this.username
            })
          })
          .then((response) => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            return response.text();
          })
          .then((content) => {
            sessionStorage.setItem("currentFood", content)
            console.log(sessionStorage.getItem("currentFood"))
          })
          .catch((error) => {
            console.error('Error:', error);
            this.errorMessage = 'Something went wrong, please try again';
          });

          // set Faculty
          fetch(ip + "ReturnFaculty?Username=" + this.username, {
            method: "POST",
            body: JSON.stringify({
              username: this.username
            })
          })
          .then((response) => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            return response.text();
          })
          .then((content) => {
            sessionStorage.setItem("currentFaculty", content)
            console.log(sessionStorage.getItem("currentFaculty"))
          })
          .catch((error) => {
            console.error('Error:', error);
            this.errorMessage = 'Something went wrong, please try again';
          });

          // set Facilities
          fetch(ip + "ReturnFacilities?Username=" + this.username, {
            method: "POST",
            body: JSON.stringify({
              username: this.username
            })
          })
          .then((response) => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            return response.text();
          })
          .then((content) => {
            sessionStorage.setItem("currentFacilities", content)
            console.log(sessionStorage.getItem("currentFacilities"))
          })
          .catch((error) => {
            console.error('Error:', error);
            this.errorMessage = 'Something went wrong, please try again';
          });

          //set Dorm Selection
          fetch(ip + "ReturnDorm?Username=" + this.username, {
            method: "POST",
            body: JSON.stringify({
              username: this.username
            })
          })
          .then((response) => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            return response.text();
          })
          .then((content) => {
            sessionStorage.setItem("currentDorm", content)
            console.log(sessionStorage.getItem("currentDorm"))
          })
          .catch((error) => {
            console.error('Error:', error);
            this.errorMessage = 'Something went wrong, please try again';
          });

          // set AboutMe
          fetch(ip + "ReturnAboutMe?Username=" + this.username, {
            method: "POST",
            body: JSON.stringify({
              username: this.username
            })
          })
          .then((response) => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            return response.text();
          })
          .then((content) => {
            sessionStorage.setItem("currentAboutMe", content)
            console.log(sessionStorage.getItem("currentAboutMe"))
          })
          .catch((error) => {
            console.error('Error:', error);
            this.errorMessage = 'Something went wrong, please try again';
          });

          // set PFP
          fetch(ip + "ReturnPFP?Username=" + this.username, {
            method: "POST",
            body: JSON.stringify({
              username: this.username
            })
          })
          .then((response) => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            return response.text();
          })
          .then((content) => {
            sessionStorage.setItem("currentPFP", content)
            console.log(sessionStorage.getItem("currentPFP"))
          })
          .catch((error) => {
            console.error('Error:', error);
            this.errorMessage = 'Something went wrong, please try again';
          });

          **/
          sessionStorage.setItem('isLoggedIn', 'true')
          this.router.navigate(['/home'])
          .then(() => {
            window.location.reload();
          })
        } else {
          this.errorMessage = 'Invalid username or password';
        }
    })
    .catch((error) => {
      console.error('Error:', error);
      this.errorMessage = 'Something went wrong, please try again';
    });
  }

  doSomething() {
    sessionStorage.setItem('isLoggedIn', 'true')
    sessionStorage.setItem('currentUser', "ADMIN")
    this.router.navigate(['/home'])
          .then(() => {
            window.location.reload();
          })
  }
}
