import { Component, ElementRef, HostListener } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent {

  showDropdown = false;

  constructor(private elementRef: ElementRef) {}

  toggleDropdown() {
    this.showDropdown = !this.showDropdown;
  }

  dropdownClick(event: { stopPropagation: () => void; }) {
    event.stopPropagation();
  }

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: { target: any; }) {
    if (!this.elementRef.nativeElement.contains(event.target)) {
      this.showDropdown = false;
    }
  }
}

