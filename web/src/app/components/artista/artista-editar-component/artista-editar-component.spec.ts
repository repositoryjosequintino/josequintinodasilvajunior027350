import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtistaEditarComponent } from './artista-editar-component';

describe('ArtistaEditarComponent', () => {
  let component: ArtistaEditarComponent;
  let fixture: ComponentFixture<ArtistaEditarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ArtistaEditarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArtistaEditarComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
