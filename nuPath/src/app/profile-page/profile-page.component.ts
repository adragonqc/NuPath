import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {
  currentUser: string = "";
  username: string = "";
  displayName: string = "";
  contactInformation: string = "";
  interests: string = "";
  foodSelection: string = "";
  facultySelection: string = "";
  facilitiySelection: string = "";
  dormSelection: string = "";
  aboutMe: string = "";
  profilePicture: string = "";
  photos: string[] = [];

  originalDisplayName = '';
  editingDisplayName = false;

  originalInterests = '';
  editingInterests = false;
  
  originalAboutMe = '';
  editingAboutMe = false;

  ip = "http://35.188.8.151:80/";

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.currentUser = sessionStorage.getItem('currentUser') as string;

    this.username = this.route.snapshot.paramMap.get('username') as string;

    // check for existing user
    fetch(this.ip + "ReturnUsername?Username=" + this.username, {
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
      if(content === this.username) {
            // set displayName
        fetch(this.ip + "ReturnDisplayName?Username=" + this.username, {
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
        fetch(this.ip + "ReturnContactInformation?Username=" + this.username, {
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
        fetch(this.ip + "ReturnInterests?Username=" + this.username, {
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
        fetch(this.ip + "ReturnFood?Username=" + this.username, {
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
        fetch(this.ip + "ReturnFaculty?Username=" + this.username, {
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
        fetch(this.ip + "ReturnFacilities?Username=" + this.username, {
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
        fetch(this.ip + "ReturnDorm?Username=" + this.username, {
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
        fetch(this.ip + "ReturnAboutMe?Username=" + this.username, {
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
        fetch(this.ip + "ReturnPFP?Username=" + this.username, {
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
    var dataURI = "";
    // convert the base64 string to a data URI
    if(!this.profilePicture.startsWith("data:image/png;base64")){
      dataURI = `data:image/png;base64,${this.profilePicture}`;
    } else {
      dataURI = this.profilePicture;
    }
  
    return dataURI;
  }

  onProfilePictureSelected(event: any) {
    const file = event.target.files[0];
  
    // Convert the selected file to a base64 string
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      const base64String = reader.result as string;
      this.profilePicture = base64String;
  
      // Code to upload the base64 string to the server and update the profile picture
      fetch(this.ip + "UploadPFP?Username=" + this.username + "&NewPFPFile=" + this.profilePicture)
      .then(() => {
        window.location.reload();
      })

      event.target.style.display = 'none';
    };
  }

  onEditProfilePictureClick() {
    document.getElementById('profile-picture-input')?.click();
  }
  
  
  editInterests() {
    this.originalInterests = this.interests;
    this.editingInterests = true;
  }

  saveInterests() {
    // Update interests in your database or API
    fetch(this.ip + "UpdateInterests?Username=" + this.username + "&Interest=" + this.interests);
    console.log(this.ip + "UpdateInterests?Username=" + this.username + "&Interest=" + this.interests)
    this.editingInterests = false;
  }

  cancelInterests() {
    this.interests = this.originalInterests;
    this.editingInterests = false;
  }

  editDisplayName() {
    this.originalDisplayName = this.displayName;
    this.editingDisplayName = true;
  }

  saveDisplayName() {
    // Update DisplayName in your database or API
    fetch(this.ip + "UpdateDisplayName?Username=" + this.username + "&NewDisplayName=" + this.displayName);
    this.editingDisplayName = false;
  }

  cancelDisplayName() {
    this.displayName = this.originalDisplayName;
    this.editingDisplayName = false;
  }

  editAboutMe() {
    this.originalAboutMe = this.aboutMe;
    this.editingAboutMe = true;
  }

  saveAboutMe() {
    fetch(this.ip + "UpdateAboutme?Username=" + this.username + "&NewAboutme=" + this.aboutMe);
    console.log(this.ip + "UpdateAboutme?Username=" + this.username + "&NewAboutme=" + this.aboutMe);
    this.editingAboutMe = false;
  }

  cancelAboutMe() {
    this.aboutMe = this.originalAboutMe;
    this.editingAboutMe = false;
  }
}

