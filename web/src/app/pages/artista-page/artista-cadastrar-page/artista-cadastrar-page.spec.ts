import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtistaCadastrarPage } from './artista-cadastrar-page';

describe('ArtistaCadastrarPage', () => {
  let component: ArtistaCadastrarPage;
  let fixture: ComponentFixture<ArtistaCadastrarPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArtistaCadastrarPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArtistaCadastrarPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
