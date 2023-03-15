import { Component } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser'

@Component({
  selector: 'app-nearnovoland-page',
  templateUrl: './nearnovoland-page.component.html',
  styleUrls: ['./nearnovoland-page.component.css']
})
export class NearNovolandPageComponent {
  urlSafe!: SafeResourceUrl;

  constructor(public sanitizer: DomSanitizer){}

  /* load food locations on initilization */
  ngOnInit(){
    this.urlSafe= this.sanitizer.bypassSecurityTrustResourceUrl(
      "https://www.google.com/maps/embed?pb=!1m16!1m12!1m3!1d14170.720805899096!2d-82.56522562203139!3d27.385701184348434!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!2m1!1sfood%20near%205800%20Bay%20Shore%20Road%2C%20Sarasota%2C%20FL%2034243!5e0!3m2!1sen!2sus!4v1678894295758!5m2!1sen!2sus"
    );
  }

  setUrl( url: string ){
    this.urlSafe= this.sanitizer.bypassSecurityTrustResourceUrl(
      url
    );
  }
}
