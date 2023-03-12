import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PackingListPageComponent } from './packinglist-page.component';

describe('PackingListPageComponent', () => {
  let component: PackingListPageComponent;
  let fixture: ComponentFixture<PackingListPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PackingListPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PackingListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
