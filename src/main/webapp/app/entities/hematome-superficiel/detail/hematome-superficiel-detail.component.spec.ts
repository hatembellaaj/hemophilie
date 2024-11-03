import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { HematomeSuperficielDetailComponent } from './hematome-superficiel-detail.component';

describe('HematomeSuperficiel Management Detail Component', () => {
  let comp: HematomeSuperficielDetailComponent;
  let fixture: ComponentFixture<HematomeSuperficielDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HematomeSuperficielDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./hematome-superficiel-detail.component').then(m => m.HematomeSuperficielDetailComponent),
              resolve: { hematomeSuperficiel: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(HematomeSuperficielDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HematomeSuperficielDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load hematomeSuperficiel on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', HematomeSuperficielDetailComponent);

      // THEN
      expect(instance.hematomeSuperficiel()).toEqual(expect.objectContaining({ id: 123 }));
    });
  });

  describe('PreviousState', () => {
    it('Should navigate to previous state', () => {
      jest.spyOn(window.history, 'back');
      comp.previousState();
      expect(window.history.back).toHaveBeenCalled();
    });
  });
});
