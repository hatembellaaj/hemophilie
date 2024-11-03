import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { HematomePsoasDetailComponent } from './hematome-psoas-detail.component';

describe('HematomePsoas Management Detail Component', () => {
  let comp: HematomePsoasDetailComponent;
  let fixture: ComponentFixture<HematomePsoasDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HematomePsoasDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./hematome-psoas-detail.component').then(m => m.HematomePsoasDetailComponent),
              resolve: { hematomePsoas: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(HematomePsoasDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HematomePsoasDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load hematomePsoas on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', HematomePsoasDetailComponent);

      // THEN
      expect(instance.hematomePsoas()).toEqual(expect.objectContaining({ id: 123 }));
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
