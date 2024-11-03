import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { HemorragieVisceresDetailComponent } from './hemorragie-visceres-detail.component';

describe('HemorragieVisceres Management Detail Component', () => {
  let comp: HemorragieVisceresDetailComponent;
  let fixture: ComponentFixture<HemorragieVisceresDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HemorragieVisceresDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./hemorragie-visceres-detail.component').then(m => m.HemorragieVisceresDetailComponent),
              resolve: { hemorragieVisceres: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(HemorragieVisceresDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HemorragieVisceresDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load hemorragieVisceres on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', HemorragieVisceresDetailComponent);

      // THEN
      expect(instance.hemorragieVisceres()).toEqual(expect.objectContaining({ id: 123 }));
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
