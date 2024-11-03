import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { SaignementSNCService } from '../service/saignement-snc.service';
import { ISaignementSNC } from '../saignement-snc.model';
import { SaignementSNCFormService } from './saignement-snc-form.service';

import { SaignementSNCUpdateComponent } from './saignement-snc-update.component';

describe('SaignementSNC Management Update Component', () => {
  let comp: SaignementSNCUpdateComponent;
  let fixture: ComponentFixture<SaignementSNCUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let saignementSNCFormService: SaignementSNCFormService;
  let saignementSNCService: SaignementSNCService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [SaignementSNCUpdateComponent],
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
      .overrideTemplate(SaignementSNCUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SaignementSNCUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    saignementSNCFormService = TestBed.inject(SaignementSNCFormService);
    saignementSNCService = TestBed.inject(SaignementSNCService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const saignementSNC: ISaignementSNC = { id: 456 };

      activatedRoute.data = of({ saignementSNC });
      comp.ngOnInit();

      expect(comp.saignementSNC).toEqual(saignementSNC);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISaignementSNC>>();
      const saignementSNC = { id: 123 };
      jest.spyOn(saignementSNCFormService, 'getSaignementSNC').mockReturnValue(saignementSNC);
      jest.spyOn(saignementSNCService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ saignementSNC });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: saignementSNC }));
      saveSubject.complete();

      // THEN
      expect(saignementSNCFormService.getSaignementSNC).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(saignementSNCService.update).toHaveBeenCalledWith(expect.objectContaining(saignementSNC));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISaignementSNC>>();
      const saignementSNC = { id: 123 };
      jest.spyOn(saignementSNCFormService, 'getSaignementSNC').mockReturnValue({ id: null });
      jest.spyOn(saignementSNCService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ saignementSNC: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: saignementSNC }));
      saveSubject.complete();

      // THEN
      expect(saignementSNCFormService.getSaignementSNC).toHaveBeenCalled();
      expect(saignementSNCService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISaignementSNC>>();
      const saignementSNC = { id: 123 };
      jest.spyOn(saignementSNCService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ saignementSNC });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(saignementSNCService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
