import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NulifeSidebarComponent } from './nulife-sidebar.component';

describe('NulifeSidebarComponent', () => {
  let component: NulifeSidebarComponent;
  let fixture: ComponentFixture<NulifeSidebarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NulifeSidebarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NulifeSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
