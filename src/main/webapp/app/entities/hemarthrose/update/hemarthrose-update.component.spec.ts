import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { HemarthroseService } from '../service/hemarthrose.service';
import { IHemarthrose } from '../hemarthrose.model';
import { HemarthroseFormService } from './hemarthrose-form.service';

import { HemarthroseUpdateComponent } from './hemarthrose-update.component';

describe('Hemarthrose Management Update Component', () => {
  let comp: HemarthroseUpdateComponent;
  let fixture: ComponentFixture<HemarthroseUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let hemarthroseFormService: HemarthroseFormService;
  let hemarthroseService: HemarthroseService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HemarthroseUpdateComponent],
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
      .overrideTemplate(HemarthroseUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(HemarthroseUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    hemarthroseFormService = TestBed.inject(HemarthroseFormService);
    hemarthroseService = TestBed.inject(HemarthroseService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const hemarthrose: IHemarthrose = { id: 456 };

      activatedRoute.data = of({ hemarthrose });
      comp.ngOnInit();

      expect(comp.hemarthrose).toEqual(hemarthrose);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHemarthrose>>();
      const hemarthrose = { id: 123 };
      jest.spyOn(hemarthroseFormService, 'getHemarthrose').mockReturnValue(hemarthrose);
      jest.spyOn(hemarthroseService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hemarthrose });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: hemarthrose }));
      saveSubject.complete();

      // THEN
      expect(hemarthroseFormService.getHemarthrose).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(hemarthroseService.update).toHaveBeenCalledWith(expect.objectContaining(hemarthrose));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHemarthrose>>();
      const hemarthrose = { id: 123 };
      jest.spyOn(hemarthroseFormService, 'getHemarthrose').mockReturnValue({ id: null });
      jest.spyOn(hemarthroseService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hemarthrose: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: hemarthrose }));
      saveSubject.complete();

      // THEN
      expect(hemarthroseFormService.getHemarthrose).toHaveBeenCalled();
      expect(hemarthroseService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHemarthrose>>();
      const hemarthrose = { id: 123 };
      jest.spyOn(hemarthroseService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hemarthrose });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(hemarthroseService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
