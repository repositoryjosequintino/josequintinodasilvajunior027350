import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlbumCadastrarPage } from './album-cadastrar-page';

describe('AlbumCadastrarPage', () => {
  let component: AlbumCadastrarPage;
  let fixture: ComponentFixture<AlbumCadastrarPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AlbumCadastrarPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlbumCadastrarPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
