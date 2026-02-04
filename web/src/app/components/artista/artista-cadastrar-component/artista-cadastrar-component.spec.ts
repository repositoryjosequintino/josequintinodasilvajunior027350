import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtistaCadastrarComponent } from './artista-cadastrar-component';

describe('ArtistaCadastrarComponent', () => {
  let component: ArtistaCadastrarComponent;
  let fixture: ComponentFixture<ArtistaCadastrarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ArtistaCadastrarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArtistaCadastrarComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
