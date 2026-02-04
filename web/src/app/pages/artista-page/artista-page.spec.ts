import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtistaPage } from './artista-page';

describe('ArtistaPage', () => {
  let component: ArtistaPage;
  let fixture: ComponentFixture<ArtistaPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArtistaPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArtistaPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
