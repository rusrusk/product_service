import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProdListingComponent } from './prod-listing.component';

describe('ProdListingComponent', () => {
  let component: ProdListingComponent;
  let fixture: ComponentFixture<ProdListingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProdListingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProdListingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
