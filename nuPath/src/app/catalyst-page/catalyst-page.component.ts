import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-catalyst-page',
  templateUrl: './catalyst-page.component.html',
  styleUrls: ['./catalyst-page.component.css']
})
export class CatalystPageComponent {
  
  iframeUrl: any;

  constructor(private sanitizer: DomSanitizer) {

    this.iframeUrl = this.sanitizer.bypassSecurityTrustResourceUrl('https://ncfcatalyst.com');
  }

}