import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtistaDetalharPage } from './artista-detalhar-page';

describe('ArtistaDetalharPage', () => {
  let component: ArtistaDetalharPage;
  let fixture: ComponentFixture<ArtistaDetalharPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ArtistaDetalharPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArtistaDetalharPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
