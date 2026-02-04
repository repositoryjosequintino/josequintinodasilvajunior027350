import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtistaEditarPage } from './artista-editar-page';

describe('ArtistaEditarPage', () => {
  let component: ArtistaEditarPage;
  let fixture: ComponentFixture<ArtistaEditarPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ArtistaEditarPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArtistaEditarPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
