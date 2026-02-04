import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlbumCadastrarComponent } from './album-cadastrar-component';

describe('AlbumCadastrarComponent', () => {
  let component: AlbumCadastrarComponent;
  let fixture: ComponentFixture<AlbumCadastrarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AlbumCadastrarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlbumCadastrarComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
