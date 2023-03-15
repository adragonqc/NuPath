import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-leaderboard-page',
  templateUrl: './leaderboard-page.component.html',
  styleUrls: ['./leaderboard-page.component.css']
})
export class LeaderboardPageComponent implements OnInit{

  users!: string;
  userArray!: Array<string>;

  ngOnInit(): void {
      
    fetch("http://35.188.8.151:80/ReturnLBInformation")
    .then((response) => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.text();
    })
    .then((content) => {
      this.userArray = content.split(',');
      this.userArray.pop();
      this.userArray.reverse();
    });

  }
}
