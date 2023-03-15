import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-catalyst-page',
  templateUrl: './catalyst-page.component.html',
  styleUrls: ['./catalyst-page.component.css']
})
export class CatalystPageComponent implements OnInit {
  
  iframeUrl: any;
  notes!: string;

  ngOnInit(): void {
    fetch("http://35.188.8.151:80/ReturnCatalystNotes?Username=" + sessionStorage.getItem('currentUser'), {
      method: "POST",
      body: JSON.stringify({
        notes: this.notes
      })
    })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.text();
    })
    .then((content) => {
      this.notes = content;
    })
  }

  constructor(private sanitizer: DomSanitizer) {

    this.iframeUrl = this.sanitizer.bypassSecurityTrustResourceUrl('https://ncfcatalyst.com');
  }

  saveNotes(): void {
    fetch("http://35.188.8.151:80/UpdateCatalystNotes?Username=" + sessionStorage.getItem('currentUser') + "&Notes=" + this.notes, {})
    console.log(this.notes)
  }

}