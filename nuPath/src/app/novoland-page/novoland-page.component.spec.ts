import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NovolandPageComponent } from './novoland-page.component';

describe('NovolandPageComponent', () => {
  let component: NovolandPageComponent;
  let fixture: ComponentFixture<NovolandPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NovolandPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NovolandPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
