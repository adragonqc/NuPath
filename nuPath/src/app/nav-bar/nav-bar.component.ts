import { Component, ElementRef, HostListener } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent {

  activeLink = '';
  showDropdown = false;
  currentUser = sessionStorage.getItem('currentUser');

  constructor(private elementRef: ElementRef, private router: Router) {}

  ngOnInit() {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.updateActiveLink();
      }
    });
  }

  updateActiveLink() {
    const path = this.router.url;
    const parts = path.split('/');
    this.activeLink = parts[1];
  }

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

  @HostListener('window:storage', ['$event'])
  onStorageChange(event: StorageEvent) {
    if (event.storageArea === sessionStorage && event.key === 'currentUser') {
      // The currentUser item has changed, so reload the component
      window.location.reload();
    }
  }
}

