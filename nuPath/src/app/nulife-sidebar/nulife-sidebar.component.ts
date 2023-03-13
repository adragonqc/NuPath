import { Component, ElementRef, HostListener } from '@angular/core';

@Component({
  selector: 'app-nulife-sidebar',
  templateUrl: './nulife-sidebar.component.html',
  styleUrls: ['./nulife-sidebar.component.css']
})
export class NulifeSidebarComponent {

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

