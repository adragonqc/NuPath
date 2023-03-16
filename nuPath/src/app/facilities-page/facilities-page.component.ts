import { Component } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser'

@Component({
  selector: 'app-facilities-page',
  templateUrl: './facilities-page.component.html',
  styleUrls: ['./facilities-page.component.css']
})
export class FacilitiesPageComponent {
  recUrl!: SafeResourceUrl;
  foodFeedback!: string;
  facilityFeedback!: string;

  constructor(public sanitizer: DomSanitizer){}

  ngOnInit(){
    /* load recreation center location on initilization */
    this.recUrl= this.sanitizer.bypassSecurityTrustResourceUrl(
      "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3542.7628877812385!2d-82.55691138256428!3d27.383119609404627!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x88c33fdbcaa93d81%3A0x9425c2ec91d7d289!2s5639%20General%20Twinning%2C%20Sarasota%2C%20FL%2034243!5e0!3m2!1sen!2sus!4v1678816463812!5m2!1sen!2sus"
    );

    // fetch food
    fetch("http://35.188.8.151:80/ReturnFood?Username=" + sessionStorage.getItem('currentUser'), {
      method: "POST",
      body: JSON.stringify({
        foodFeedback: this.foodFeedback
      })
    })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.text();
    })
    .then((content) => {
      this.foodFeedback = content;
    })

    // fetch facility
    fetch("http://35.188.8.151:80/ReturnFacilities?Username=" + sessionStorage.getItem('currentUser'), {
      method: "POST",
      body: JSON.stringify({
        facilityFeedback: this.facilityFeedback
      })
    })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.text();
    })
    .then((content) => {
      this.facilityFeedback = content;
    })
  }

  setRec( url: string ){
    this.recUrl= this.sanitizer.bypassSecurityTrustResourceUrl(
      url
    );
  }

  saveFoodFeedback() {
    fetch("http://35.188.8.151:80/LikedFoods?Username=" + sessionStorage.getItem('currentUser') + "&LikedFoods=" + this.foodFeedback, {})
  }

  saveFacilityFeedback() {
    fetch("http://35.188.8.151:80/LikedFacilities?Username=" + sessionStorage.getItem('currentUser') + "&LikedFacilities=" + this.facilityFeedback, {})
  }
}
