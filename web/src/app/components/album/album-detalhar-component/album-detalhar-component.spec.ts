import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlbumDetalharComponent } from './album-detalhar-component';

describe('AlbumDetalharComponent', () => {
  let component: AlbumDetalharComponent;
  let fixture: ComponentFixture<AlbumDetalharComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AlbumDetalharComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlbumDetalharComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
