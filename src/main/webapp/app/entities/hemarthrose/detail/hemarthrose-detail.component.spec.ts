import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { HemarthroseDetailComponent } from './hemarthrose-detail.component';

describe('Hemarthrose Management Detail Component', () => {
  let comp: HemarthroseDetailComponent;
  let fixture: ComponentFixture<HemarthroseDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HemarthroseDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./hemarthrose-detail.component').then(m => m.HemarthroseDetailComponent),
              resolve: { hemarthrose: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(HemarthroseDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HemarthroseDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load hemarthrose on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', HemarthroseDetailComponent);

      // THEN
      expect(instance.hemarthrose()).toEqual(expect.objectContaining({ id: 123 }));
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
