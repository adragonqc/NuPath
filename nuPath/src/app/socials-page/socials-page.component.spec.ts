import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SocialsPageComponent } from './socials-page.component';

describe('SocialsPageComponent', () => {
  let component: SocialsPageComponent;
  let fixture: ComponentFixture<SocialsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SocialsPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SocialsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
