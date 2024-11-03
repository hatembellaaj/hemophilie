import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { HematomeSuperficielService } from '../service/hematome-superficiel.service';
import { IHematomeSuperficiel } from '../hematome-superficiel.model';
import { HematomeSuperficielFormService } from './hematome-superficiel-form.service';

import { HematomeSuperficielUpdateComponent } from './hematome-superficiel-update.component';

describe('HematomeSuperficiel Management Update Component', () => {
  let comp: HematomeSuperficielUpdateComponent;
  let fixture: ComponentFixture<HematomeSuperficielUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let hematomeSuperficielFormService: HematomeSuperficielFormService;
  let hematomeSuperficielService: HematomeSuperficielService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HematomeSuperficielUpdateComponent],
      providers: [
        provideHttpClient(),
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(HematomeSuperficielUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(HematomeSuperficielUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    hematomeSuperficielFormService = TestBed.inject(HematomeSuperficielFormService);
    hematomeSuperficielService = TestBed.inject(HematomeSuperficielService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const hematomeSuperficiel: IHematomeSuperficiel = { id: 456 };

      activatedRoute.data = of({ hematomeSuperficiel });
      comp.ngOnInit();

      expect(comp.hematomeSuperficiel).toEqual(hematomeSuperficiel);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHematomeSuperficiel>>();
      const hematomeSuperficiel = { id: 123 };
      jest.spyOn(hematomeSuperficielFormService, 'getHematomeSuperficiel').mockReturnValue(hematomeSuperficiel);
      jest.spyOn(hematomeSuperficielService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hematomeSuperficiel });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: hematomeSuperficiel }));
      saveSubject.complete();

      // THEN
      expect(hematomeSuperficielFormService.getHematomeSuperficiel).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(hematomeSuperficielService.update).toHaveBeenCalledWith(expect.objectContaining(hematomeSuperficiel));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHematomeSuperficiel>>();
      const hematomeSuperficiel = { id: 123 };
      jest.spyOn(hematomeSuperficielFormService, 'getHematomeSuperficiel').mockReturnValue({ id: null });
      jest.spyOn(hematomeSuperficielService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hematomeSuperficiel: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: hematomeSuperficiel }));
      saveSubject.complete();

      // THEN
      expect(hematomeSuperficielFormService.getHematomeSuperficiel).toHaveBeenCalled();
      expect(hematomeSuperficielService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHematomeSuperficiel>>();
      const hematomeSuperficiel = { id: 123 };
      jest.spyOn(hematomeSuperficielService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hematomeSuperficiel });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(hematomeSuperficielService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
