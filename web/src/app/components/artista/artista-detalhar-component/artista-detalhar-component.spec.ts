import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtistaDetalharComponent } from './artista-detalhar-component';

describe('ArtistaDetalharComponent', () => {
  let component: ArtistaDetalharComponent;
  let fixture: ComponentFixture<ArtistaDetalharComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ArtistaDetalharComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArtistaDetalharComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
