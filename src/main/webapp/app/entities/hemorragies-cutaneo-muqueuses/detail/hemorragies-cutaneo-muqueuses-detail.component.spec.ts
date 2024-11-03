import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { HemorragiesCutaneoMuqueusesDetailComponent } from './hemorragies-cutaneo-muqueuses-detail.component';

describe('HemorragiesCutaneoMuqueuses Management Detail Component', () => {
  let comp: HemorragiesCutaneoMuqueusesDetailComponent;
  let fixture: ComponentFixture<HemorragiesCutaneoMuqueusesDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HemorragiesCutaneoMuqueusesDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./hemorragies-cutaneo-muqueuses-detail.component').then(m => m.HemorragiesCutaneoMuqueusesDetailComponent),
              resolve: { hemorragiesCutaneoMuqueuses: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(HemorragiesCutaneoMuqueusesDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HemorragiesCutaneoMuqueusesDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load hemorragiesCutaneoMuqueuses on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', HemorragiesCutaneoMuqueusesDetailComponent);

      // THEN
      expect(instance.hemorragiesCutaneoMuqueuses()).toEqual(expect.objectContaining({ id: 123 }));
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
