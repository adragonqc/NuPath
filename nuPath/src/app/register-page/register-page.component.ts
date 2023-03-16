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

    const ip = "http://35.188.8.151:80/"

    if((this.username != "" && this.password != "") || (this.username != "") || (this.password != "")) {
// Call API to register new user
fetch(ip +"CreateNewUser?Username=" + this.username + "&DisplayName=" + this.displayName + "&ContactInformation=" + this.email + "&Password=" + this.password,  {
  method: "POST",
  body: JSON.stringify({
      username: this.username,
      displayName: this.displayName,
      email: this.email,
      password: this.password,
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
  if (content === 'true') {

    // set interests to " "
    fetch(ip + "UpdateInterests?Username=" + this.username + "&Interest=" + " ", {
      method: "POST",
      body: JSON.stringify({
        username: this.username
      })
    })
    .catch((error) => {
      console.error('Error:', error);
      this.errorMessage = 'Something went wrong, please try again';
    });

    // set foodSelections to " "
    fetch(ip + "LikedFoods?Username=" + this.username + "&LikedFoods=" + " ", {
      method: "POST",
      body: JSON.stringify({
        username: this.username
      })
    })
    .catch((error) => {
      console.error('Error:', error);
      this.errorMessage = 'Something went wrong, please try again';
    });

    // set facultySelections to " "
    fetch(ip + "SelectedFaculty?Username=" + this.username + "&SelectedFaculty=" + " ", {
      method: "POST",
      body: JSON.stringify({
        username: this.username
      })
    })
    .catch((error) => {
      console.error('Error:', error);
      this.errorMessage = 'Something went wrong, please try again';
    });

    // set facilitySelections to " "
    fetch(ip + "LikedFacilities?Username=" + this.username + "&LikedFacilities=" + " ", {
      method: "POST",
      body: JSON.stringify({
        username: this.username
      })
    })
    .catch((error) => {
      console.error('Error:', error);
      this.errorMessage = 'Something went wrong, please try again';
    });

    // set dormSelections to " "
    fetch(ip + "SelectedDorm?Username=" + this.username + "&SelectedDorm=" + " ", {
      method: "POST",
      body: JSON.stringify({
        username: this.username
      })
    })
    .catch((error) => {
      console.error('Error:', error);
      this.errorMessage = 'Something went wrong, please try again';
    });

    // set aboutMe to " "
    fetch(ip + "UpdateAboutme?Username=" + this.username + "&NewAboutme=" + " ", {
      method: "POST",
      body: JSON.stringify({
        username: this.username
      })
    })
    .catch((error) => {
      console.error('Error:', error);
      this.errorMessage = 'Something went wrong, please try again';
    });

    // set PFP to " "
    fetch(ip + "UploadPFP?Username=" + this.username + "&NewPFPFile=" + "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", {
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
    .catch((error) => {
      console.error('Error:', error);
      this.errorMessage = 'Something went wrong, please try again';
    });

    // set catalystNotes to " "
    fetch(ip + "UpdateCatalystNotes?Username=" + this.username + "&Notes=" + " ", {
      method: "POST",
      body: JSON.stringify({
        username: this.username
      })
    })
    .catch((error) => {
      console.error('Error:', error);
      this.errorMessage = 'Something went wrong, please try again';
    });
    

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
}
