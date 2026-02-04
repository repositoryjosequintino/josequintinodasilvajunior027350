import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlbumDetalharPage } from './album-detalhar-page';

describe('AlbumDetalharPage', () => {
  let component: AlbumDetalharPage;
  let fixture: ComponentFixture<AlbumDetalharPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AlbumDetalharPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlbumDetalharPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
