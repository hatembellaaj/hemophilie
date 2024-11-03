import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { HemorragieVisceresService } from '../service/hemorragie-visceres.service';
import { IHemorragieVisceres } from '../hemorragie-visceres.model';
import { HemorragieVisceresFormService } from './hemorragie-visceres-form.service';

import { HemorragieVisceresUpdateComponent } from './hemorragie-visceres-update.component';

describe('HemorragieVisceres Management Update Component', () => {
  let comp: HemorragieVisceresUpdateComponent;
  let fixture: ComponentFixture<HemorragieVisceresUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let hemorragieVisceresFormService: HemorragieVisceresFormService;
  let hemorragieVisceresService: HemorragieVisceresService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HemorragieVisceresUpdateComponent],
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
      .overrideTemplate(HemorragieVisceresUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(HemorragieVisceresUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    hemorragieVisceresFormService = TestBed.inject(HemorragieVisceresFormService);
    hemorragieVisceresService = TestBed.inject(HemorragieVisceresService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const hemorragieVisceres: IHemorragieVisceres = { id: 456 };

      activatedRoute.data = of({ hemorragieVisceres });
      comp.ngOnInit();

      expect(comp.hemorragieVisceres).toEqual(hemorragieVisceres);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHemorragieVisceres>>();
      const hemorragieVisceres = { id: 123 };
      jest.spyOn(hemorragieVisceresFormService, 'getHemorragieVisceres').mockReturnValue(hemorragieVisceres);
      jest.spyOn(hemorragieVisceresService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hemorragieVisceres });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: hemorragieVisceres }));
      saveSubject.complete();

      // THEN
      expect(hemorragieVisceresFormService.getHemorragieVisceres).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(hemorragieVisceresService.update).toHaveBeenCalledWith(expect.objectContaining(hemorragieVisceres));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHemorragieVisceres>>();
      const hemorragieVisceres = { id: 123 };
      jest.spyOn(hemorragieVisceresFormService, 'getHemorragieVisceres').mockReturnValue({ id: null });
      jest.spyOn(hemorragieVisceresService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hemorragieVisceres: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: hemorragieVisceres }));
      saveSubject.complete();

      // THEN
      expect(hemorragieVisceresFormService.getHemorragieVisceres).toHaveBeenCalled();
      expect(hemorragieVisceresService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHemorragieVisceres>>();
      const hemorragieVisceres = { id: 123 };
      jest.spyOn(hemorragieVisceresService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hemorragieVisceres });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(hemorragieVisceresService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
