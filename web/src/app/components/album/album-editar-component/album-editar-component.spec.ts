import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlbumEditarComponent } from './album-editar-component';

describe('AlbumEditarComponent', () => {
  let component: AlbumEditarComponent;
  let fixture: ComponentFixture<AlbumEditarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AlbumEditarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlbumEditarComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
