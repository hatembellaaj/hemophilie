import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { HematomePsoasService } from '../service/hematome-psoas.service';
import { IHematomePsoas } from '../hematome-psoas.model';
import { HematomePsoasFormService } from './hematome-psoas-form.service';

import { HematomePsoasUpdateComponent } from './hematome-psoas-update.component';

describe('HematomePsoas Management Update Component', () => {
  let comp: HematomePsoasUpdateComponent;
  let fixture: ComponentFixture<HematomePsoasUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let hematomePsoasFormService: HematomePsoasFormService;
  let hematomePsoasService: HematomePsoasService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HematomePsoasUpdateComponent],
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
      .overrideTemplate(HematomePsoasUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(HematomePsoasUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    hematomePsoasFormService = TestBed.inject(HematomePsoasFormService);
    hematomePsoasService = TestBed.inject(HematomePsoasService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const hematomePsoas: IHematomePsoas = { id: 456 };

      activatedRoute.data = of({ hematomePsoas });
      comp.ngOnInit();

      expect(comp.hematomePsoas).toEqual(hematomePsoas);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHematomePsoas>>();
      const hematomePsoas = { id: 123 };
      jest.spyOn(hematomePsoasFormService, 'getHematomePsoas').mockReturnValue(hematomePsoas);
      jest.spyOn(hematomePsoasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hematomePsoas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: hematomePsoas }));
      saveSubject.complete();

      // THEN
      expect(hematomePsoasFormService.getHematomePsoas).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(hematomePsoasService.update).toHaveBeenCalledWith(expect.objectContaining(hematomePsoas));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHematomePsoas>>();
      const hematomePsoas = { id: 123 };
      jest.spyOn(hematomePsoasFormService, 'getHematomePsoas').mockReturnValue({ id: null });
      jest.spyOn(hematomePsoasService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hematomePsoas: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: hematomePsoas }));
      saveSubject.complete();

      // THEN
      expect(hematomePsoasFormService.getHematomePsoas).toHaveBeenCalled();
      expect(hematomePsoasService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHematomePsoas>>();
      const hematomePsoas = { id: 123 };
      jest.spyOn(hematomePsoasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hematomePsoas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(hematomePsoasService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
