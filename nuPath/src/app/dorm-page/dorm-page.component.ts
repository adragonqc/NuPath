import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dorm-page',
  templateUrl: './dorm-page.component.html',
  styleUrls: ['./dorm-page.component.css']
})
export class DormPageComponent implements OnInit {

  feedback!: string;

  ngOnInit(): void {
    fetch("http://35.188.8.151:80/ReturnDorm?Username=" + sessionStorage.getItem('currentUser'), {
      method: "POST",
      body: JSON.stringify({
        feedback: this.feedback
      })
    })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.text();
    })
    .then((content) => {
      this.feedback = content;
    })
  }

  saveFeedback() {
    fetch("http://35.188.8.151:80/SelectedDorm?Username=" + sessionStorage.getItem('currentUser') + "&SelectedDorm=" + this.feedback, {})
  }
}
