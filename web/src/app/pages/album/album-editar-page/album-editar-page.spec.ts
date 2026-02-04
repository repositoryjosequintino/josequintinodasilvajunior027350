import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlbumEditarPage } from './album-editar-page';

describe('AlbumEditarPage', () => {
  let component: AlbumEditarPage;
  let fixture: ComponentFixture<AlbumEditarPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AlbumEditarPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlbumEditarPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
