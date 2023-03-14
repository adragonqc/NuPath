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
    // Call API to validate login
    console.log(this.username + ", " + this.password)
    fetch("http://34.125.206.124:80/CreateOldUser?Username=" + this.username + "&Password=" + this.password, {
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
        if (content === this.username) {
          // Redirect to dashboard page
          this.router.navigate(['/home'])
        } else {
          this.errorMessage = 'Invalid username or password';
        }
    })
    .catch((error) => {
      console.error('Error:', error);
      this.errorMessage = 'Something went wrong, please try again';
    });
  }
}
