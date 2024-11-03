import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { SaignementSNCDetailComponent } from './saignement-snc-detail.component';

describe('SaignementSNC Management Detail Component', () => {
  let comp: SaignementSNCDetailComponent;
  let fixture: ComponentFixture<SaignementSNCDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SaignementSNCDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./saignement-snc-detail.component').then(m => m.SaignementSNCDetailComponent),
              resolve: { saignementSNC: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(SaignementSNCDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SaignementSNCDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load saignementSNC on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', SaignementSNCDetailComponent);

      // THEN
      expect(instance.saignementSNC()).toEqual(expect.objectContaining({ id: 123 }));
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
