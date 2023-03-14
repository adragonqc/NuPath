import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent {
  username!: string;
  email!: string;
  password!: string;
  confirmPassword!: string;
  displayName!: string;
  errorMessage!: string;

  constructor(private router: Router) {}

  onSubmit() {
    // Call API to register new user
    fetch("https://34.125.206.124:80/CreateNewUser?Username=" + this.username + "&DisplayName=" + this.displayName + "&ContactInformation=" + this.email + "&Password=" + this.password,  {
        method: 'POST',
        body: JSON.stringify({
            username: this.username,
            displayName: this.displayName,
            email: this.email,
            password: this.password,
            confirmPassword: this.confirmPassword
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
        // If registration is successful, redirect to login page
        // Otherwise, display error message
        if (content === 'success') {
          // Redirect to login page
          this.router.navigate(['/login'])
        } else {
          this.errorMessage = 'Registration failed';
        }
    })
    .catch((error) => {
      console.error('Error:', error);
      this.errorMessage = 'Something went wrong, please try again';
    });
  }
}
