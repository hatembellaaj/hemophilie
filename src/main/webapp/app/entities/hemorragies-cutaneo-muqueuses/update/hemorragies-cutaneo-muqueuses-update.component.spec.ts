import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { HemorragiesCutaneoMuqueusesService } from '../service/hemorragies-cutaneo-muqueuses.service';
import { IHemorragiesCutaneoMuqueuses } from '../hemorragies-cutaneo-muqueuses.model';
import { HemorragiesCutaneoMuqueusesFormService } from './hemorragies-cutaneo-muqueuses-form.service';

import { HemorragiesCutaneoMuqueusesUpdateComponent } from './hemorragies-cutaneo-muqueuses-update.component';

describe('HemorragiesCutaneoMuqueuses Management Update Component', () => {
  let comp: HemorragiesCutaneoMuqueusesUpdateComponent;
  let fixture: ComponentFixture<HemorragiesCutaneoMuqueusesUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let hemorragiesCutaneoMuqueusesFormService: HemorragiesCutaneoMuqueusesFormService;
  let hemorragiesCutaneoMuqueusesService: HemorragiesCutaneoMuqueusesService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HemorragiesCutaneoMuqueusesUpdateComponent],
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
      .overrideTemplate(HemorragiesCutaneoMuqueusesUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(HemorragiesCutaneoMuqueusesUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    hemorragiesCutaneoMuqueusesFormService = TestBed.inject(HemorragiesCutaneoMuqueusesFormService);
    hemorragiesCutaneoMuqueusesService = TestBed.inject(HemorragiesCutaneoMuqueusesService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const hemorragiesCutaneoMuqueuses: IHemorragiesCutaneoMuqueuses = { id: 456 };

      activatedRoute.data = of({ hemorragiesCutaneoMuqueuses });
      comp.ngOnInit();

      expect(comp.hemorragiesCutaneoMuqueuses).toEqual(hemorragiesCutaneoMuqueuses);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHemorragiesCutaneoMuqueuses>>();
      const hemorragiesCutaneoMuqueuses = { id: 123 };
      jest.spyOn(hemorragiesCutaneoMuqueusesFormService, 'getHemorragiesCutaneoMuqueuses').mockReturnValue(hemorragiesCutaneoMuqueuses);
      jest.spyOn(hemorragiesCutaneoMuqueusesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hemorragiesCutaneoMuqueuses });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: hemorragiesCutaneoMuqueuses }));
      saveSubject.complete();

      // THEN
      expect(hemorragiesCutaneoMuqueusesFormService.getHemorragiesCutaneoMuqueuses).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(hemorragiesCutaneoMuqueusesService.update).toHaveBeenCalledWith(expect.objectContaining(hemorragiesCutaneoMuqueuses));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHemorragiesCutaneoMuqueuses>>();
      const hemorragiesCutaneoMuqueuses = { id: 123 };
      jest.spyOn(hemorragiesCutaneoMuqueusesFormService, 'getHemorragiesCutaneoMuqueuses').mockReturnValue({ id: null });
      jest.spyOn(hemorragiesCutaneoMuqueusesService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hemorragiesCutaneoMuqueuses: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: hemorragiesCutaneoMuqueuses }));
      saveSubject.complete();

      // THEN
      expect(hemorragiesCutaneoMuqueusesFormService.getHemorragiesCutaneoMuqueuses).toHaveBeenCalled();
      expect(hemorragiesCutaneoMuqueusesService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHemorragiesCutaneoMuqueuses>>();
      const hemorragiesCutaneoMuqueuses = { id: 123 };
      jest.spyOn(hemorragiesCutaneoMuqueusesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hemorragiesCutaneoMuqueuses });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(hemorragiesCutaneoMuqueusesService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
