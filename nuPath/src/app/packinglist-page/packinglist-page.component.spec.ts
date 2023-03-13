import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PackinglistPageComponent } from './packinglist-page.component';

describe('PackinglistPageComponent', () => {
  let component: PackinglistPageComponent;
  let fixture: ComponentFixture<PackinglistPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PackinglistPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PackinglistPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
