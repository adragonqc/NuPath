import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {
  username!: string | null;
  displayName!: string;
  contactInformation!: string;
  interests!: string;
  foodSelection!: string;
  facultySelection!: string;
  facilitiySelection!: string;
  dormSelection!: string;
  aboutMe!: string;
  profilePicture!: string;

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.username = this.route.snapshot.paramMap.get('username');

    const ip = "http://35.188.8.151:80/"

    // check for existing user
    fetch(ip + "ReturnUsername?Username=" + this.username, {
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
      console.log(content);
      if(content === this.username) {
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
          this.displayName = content;
        })
        .catch(error => {
          console.error('Error fetching user displayName:', error);
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
          this.contactInformation = content;
        })
        .catch(error => {
          console.error('Error fetching user contactInformation:', error);
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
          this.interests = content;
        })
        .catch(error => {
          console.error('Error fetching user interests:', error);
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
          this.foodSelection = content;
        })
        .catch(error => {
          console.error('Error fetching user foodSelections:', error);
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
          this.facultySelection = content;
        })
        .catch(error => {
          console.error('Error fetching user facultySelections:', error);
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
          this.facilitiySelection = content;
        })
        .catch(error => {
          console.error('Error fetching user facilitySelections:', error);
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
          this.dormSelection = content;
        })
        .catch(error => {
          console.error('Error fetching user dormSelections:', error);
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
          this.aboutMe = content;
        })
        .catch(error => {
          console.error('Error fetching user aboutMe:', error);
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
          this.profilePicture = content;
        })
        .catch(error => {
          console.error('Error fetching user pfp:', error);
        });

      }
      else {
        this.router.navigate(['/**'])
      }
      })
    .catch(error => {
      console.error('Error fetching user userName:', error);
    });

  }

  getProfilePicture(): string {
    // convert the base64 string to a data URI
    const dataURI = `data:image/png;base64,${this.profilePicture}`;
  
    return dataURI;
  }

  handleProfilePictureChange(event: any): void {
    const file = event.target.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      // set the base64 string of the uploaded image to the component property for the profile picture
      if(reader.result != null) {
        this.profilePicture = reader.result.toString();
      }
    };
  }

  saveCanvas(): void {
    // get the canvas element
    const canvas = document.querySelector('canvas') as HTMLCanvasElement;
    // get the image data as a base64 string
    const base64Image = canvas.toDataURL("image/png");
    // create an anchor element with the image data as the href attribute
    const downloadLink = document.createElement("a");
    downloadLink.href = base64Image;
    downloadLink.download = "canvas-image.png";
    // simulate a click on the anchor element to download the image
    downloadLink.click();
  }
  
}
