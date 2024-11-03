import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { IHemarthrose } from 'app/entities/hemarthrose/hemarthrose.model';
import { HemarthroseService } from 'app/entities/hemarthrose/service/hemarthrose.service';
import { IHematomeSuperficiel } from 'app/entities/hematome-superficiel/hematome-superficiel.model';
import { HematomeSuperficielService } from 'app/entities/hematome-superficiel/service/hematome-superficiel.service';
import { IHematomePsoas } from 'app/entities/hematome-psoas/hematome-psoas.model';
import { HematomePsoasService } from 'app/entities/hematome-psoas/service/hematome-psoas.service';
import { IHemorragiesCutaneoMuqueuses } from 'app/entities/hemorragies-cutaneo-muqueuses/hemorragies-cutaneo-muqueuses.model';
import { HemorragiesCutaneoMuqueusesService } from 'app/entities/hemorragies-cutaneo-muqueuses/service/hemorragies-cutaneo-muqueuses.service';
import { IHemorragieVisceres } from 'app/entities/hemorragie-visceres/hemorragie-visceres.model';
import { HemorragieVisceresService } from 'app/entities/hemorragie-visceres/service/hemorragie-visceres.service';
import { ISaignementSNC } from 'app/entities/saignement-snc/saignement-snc.model';
import { SaignementSNCService } from 'app/entities/saignement-snc/service/saignement-snc.service';
import { IUser } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/service/user.service';
import { IPatient } from 'app/entities/patient/patient.model';
import { PatientService } from 'app/entities/patient/service/patient.service';
import { IFiche } from '../fiche.model';
import { FicheService } from '../service/fiche.service';
import { FicheFormService } from './fiche-form.service';

import { FicheUpdateComponent } from './fiche-update.component';

describe('Fiche Management Update Component', () => {
  let comp: FicheUpdateComponent;
  let fixture: ComponentFixture<FicheUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let ficheFormService: FicheFormService;
  let ficheService: FicheService;
  let hemarthroseService: HemarthroseService;
  let hematomeSuperficielService: HematomeSuperficielService;
  let hematomePsoasService: HematomePsoasService;
  let hemorragiesCutaneoMuqueusesService: HemorragiesCutaneoMuqueusesService;
  let hemorragieVisceresService: HemorragieVisceresService;
  let saignementSNCService: SaignementSNCService;
  let userService: UserService;
  let patientService: PatientService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [FicheUpdateComponent],
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
      .overrideTemplate(FicheUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FicheUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    ficheFormService = TestBed.inject(FicheFormService);
    ficheService = TestBed.inject(FicheService);
    hemarthroseService = TestBed.inject(HemarthroseService);
    hematomeSuperficielService = TestBed.inject(HematomeSuperficielService);
    hematomePsoasService = TestBed.inject(HematomePsoasService);
    hemorragiesCutaneoMuqueusesService = TestBed.inject(HemorragiesCutaneoMuqueusesService);
    hemorragieVisceresService = TestBed.inject(HemorragieVisceresService);
    saignementSNCService = TestBed.inject(SaignementSNCService);
    userService = TestBed.inject(UserService);
    patientService = TestBed.inject(PatientService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call hemarthrose query and add missing value', () => {
      const fiche: IFiche = { id: 456 };
      const hemarthrose: IHemarthrose = { id: 4967 };
      fiche.hemarthrose = hemarthrose;

      const hemarthroseCollection: IHemarthrose[] = [{ id: 30759 }];
      jest.spyOn(hemarthroseService, 'query').mockReturnValue(of(new HttpResponse({ body: hemarthroseCollection })));
      const expectedCollection: IHemarthrose[] = [hemarthrose, ...hemarthroseCollection];
      jest.spyOn(hemarthroseService, 'addHemarthroseToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ fiche });
      comp.ngOnInit();

      expect(hemarthroseService.query).toHaveBeenCalled();
      expect(hemarthroseService.addHemarthroseToCollectionIfMissing).toHaveBeenCalledWith(hemarthroseCollection, hemarthrose);
      expect(comp.hemarthrosesCollection).toEqual(expectedCollection);
    });

    it('Should call hematomeSuperficiel query and add missing value', () => {
      const fiche: IFiche = { id: 456 };
      const hematomeSuperficiel: IHematomeSuperficiel = { id: 25553 };
      fiche.hematomeSuperficiel = hematomeSuperficiel;

      const hematomeSuperficielCollection: IHematomeSuperficiel[] = [{ id: 20734 }];
      jest.spyOn(hematomeSuperficielService, 'query').mockReturnValue(of(new HttpResponse({ body: hematomeSuperficielCollection })));
      const expectedCollection: IHematomeSuperficiel[] = [hematomeSuperficiel, ...hematomeSuperficielCollection];
      jest.spyOn(hematomeSuperficielService, 'addHematomeSuperficielToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ fiche });
      comp.ngOnInit();

      expect(hematomeSuperficielService.query).toHaveBeenCalled();
      expect(hematomeSuperficielService.addHematomeSuperficielToCollectionIfMissing).toHaveBeenCalledWith(
        hematomeSuperficielCollection,
        hematomeSuperficiel,
      );
      expect(comp.hematomeSuperficielsCollection).toEqual(expectedCollection);
    });

    it('Should call hematomePsoas query and add missing value', () => {
      const fiche: IFiche = { id: 456 };
      const hematomePsoas: IHematomePsoas = { id: 8532 };
      fiche.hematomePsoas = hematomePsoas;

      const hematomePsoasCollection: IHematomePsoas[] = [{ id: 21299 }];
      jest.spyOn(hematomePsoasService, 'query').mockReturnValue(of(new HttpResponse({ body: hematomePsoasCollection })));
      const expectedCollection: IHematomePsoas[] = [hematomePsoas, ...hematomePsoasCollection];
      jest.spyOn(hematomePsoasService, 'addHematomePsoasToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ fiche });
      comp.ngOnInit();

      expect(hematomePsoasService.query).toHaveBeenCalled();
      expect(hematomePsoasService.addHematomePsoasToCollectionIfMissing).toHaveBeenCalledWith(hematomePsoasCollection, hematomePsoas);
      expect(comp.hematomePsoasCollection).toEqual(expectedCollection);
    });

    it('Should call hemorragiesCutaneoMuqueuses query and add missing value', () => {
      const fiche: IFiche = { id: 456 };
      const hemorragiesCutaneoMuqueuses: IHemorragiesCutaneoMuqueuses = { id: 9212 };
      fiche.hemorragiesCutaneoMuqueuses = hemorragiesCutaneoMuqueuses;

      const hemorragiesCutaneoMuqueusesCollection: IHemorragiesCutaneoMuqueuses[] = [{ id: 14021 }];
      jest
        .spyOn(hemorragiesCutaneoMuqueusesService, 'query')
        .mockReturnValue(of(new HttpResponse({ body: hemorragiesCutaneoMuqueusesCollection })));
      const expectedCollection: IHemorragiesCutaneoMuqueuses[] = [hemorragiesCutaneoMuqueuses, ...hemorragiesCutaneoMuqueusesCollection];
      jest
        .spyOn(hemorragiesCutaneoMuqueusesService, 'addHemorragiesCutaneoMuqueusesToCollectionIfMissing')
        .mockReturnValue(expectedCollection);

      activatedRoute.data = of({ fiche });
      comp.ngOnInit();

      expect(hemorragiesCutaneoMuqueusesService.query).toHaveBeenCalled();
      expect(hemorragiesCutaneoMuqueusesService.addHemorragiesCutaneoMuqueusesToCollectionIfMissing).toHaveBeenCalledWith(
        hemorragiesCutaneoMuqueusesCollection,
        hemorragiesCutaneoMuqueuses,
      );
      expect(comp.hemorragiesCutaneoMuqueusesCollection).toEqual(expectedCollection);
    });

    it('Should call hemorragieVisceres query and add missing value', () => {
      const fiche: IFiche = { id: 456 };
      const hemorragieVisceres: IHemorragieVisceres = { id: 18442 };
      fiche.hemorragieVisceres = hemorragieVisceres;

      const hemorragieVisceresCollection: IHemorragieVisceres[] = [{ id: 19691 }];
      jest.spyOn(hemorragieVisceresService, 'query').mockReturnValue(of(new HttpResponse({ body: hemorragieVisceresCollection })));
      const expectedCollection: IHemorragieVisceres[] = [hemorragieVisceres, ...hemorragieVisceresCollection];
      jest.spyOn(hemorragieVisceresService, 'addHemorragieVisceresToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ fiche });
      comp.ngOnInit();

      expect(hemorragieVisceresService.query).toHaveBeenCalled();
      expect(hemorragieVisceresService.addHemorragieVisceresToCollectionIfMissing).toHaveBeenCalledWith(
        hemorragieVisceresCollection,
        hemorragieVisceres,
      );
      expect(comp.hemorragieVisceresCollection).toEqual(expectedCollection);
    });

    it('Should call saignementSNC query and add missing value', () => {
      const fiche: IFiche = { id: 456 };
      const saignementSNC: ISaignementSNC = { id: 19306 };
      fiche.saignementSNC = saignementSNC;

      const saignementSNCCollection: ISaignementSNC[] = [{ id: 10970 }];
      jest.spyOn(saignementSNCService, 'query').mockReturnValue(of(new HttpResponse({ body: saignementSNCCollection })));
      const expectedCollection: ISaignementSNC[] = [saignementSNC, ...saignementSNCCollection];
      jest.spyOn(saignementSNCService, 'addSaignementSNCToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ fiche });
      comp.ngOnInit();

      expect(saignementSNCService.query).toHaveBeenCalled();
      expect(saignementSNCService.addSaignementSNCToCollectionIfMissing).toHaveBeenCalledWith(saignementSNCCollection, saignementSNC);
      expect(comp.saignementSNCSCollection).toEqual(expectedCollection);
    });

    it('Should call User query and add missing value', () => {
      const fiche: IFiche = { id: 456 };
      const user: IUser = { id: 22605 };
      fiche.user = user;

      const userCollection: IUser[] = [{ id: 23403 }];
      jest.spyOn(userService, 'query').mockReturnValue(of(new HttpResponse({ body: userCollection })));
      const additionalUsers = [user];
      const expectedCollection: IUser[] = [...additionalUsers, ...userCollection];
      jest.spyOn(userService, 'addUserToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ fiche });
      comp.ngOnInit();

      expect(userService.query).toHaveBeenCalled();
      expect(userService.addUserToCollectionIfMissing).toHaveBeenCalledWith(
        userCollection,
        ...additionalUsers.map(expect.objectContaining),
      );
      expect(comp.usersSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Patient query and add missing value', () => {
      const fiche: IFiche = { id: 456 };
      const patient: IPatient = { id: 4867 };
      fiche.patient = patient;

      const patientCollection: IPatient[] = [{ id: 23922 }];
      jest.spyOn(patientService, 'query').mockReturnValue(of(new HttpResponse({ body: patientCollection })));
      const additionalPatients = [patient];
      const expectedCollection: IPatient[] = [...additionalPatients, ...patientCollection];
      jest.spyOn(patientService, 'addPatientToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ fiche });
      comp.ngOnInit();

      expect(patientService.query).toHaveBeenCalled();
      expect(patientService.addPatientToCollectionIfMissing).toHaveBeenCalledWith(
        patientCollection,
        ...additionalPatients.map(expect.objectContaining),
      );
      expect(comp.patientsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const fiche: IFiche = { id: 456 };
      const hemarthrose: IHemarthrose = { id: 22698 };
      fiche.hemarthrose = hemarthrose;
      const hematomeSuperficiel: IHematomeSuperficiel = { id: 445 };
      fiche.hematomeSuperficiel = hematomeSuperficiel;
      const hematomePsoas: IHematomePsoas = { id: 3535 };
      fiche.hematomePsoas = hematomePsoas;
      const hemorragiesCutaneoMuqueuses: IHemorragiesCutaneoMuqueuses = { id: 3819 };
      fiche.hemorragiesCutaneoMuqueuses = hemorragiesCutaneoMuqueuses;
      const hemorragieVisceres: IHemorragieVisceres = { id: 32159 };
      fiche.hemorragieVisceres = hemorragieVisceres;
      const saignementSNC: ISaignementSNC = { id: 6238 };
      fiche.saignementSNC = saignementSNC;
      const user: IUser = { id: 23865 };
      fiche.user = user;
      const patient: IPatient = { id: 26711 };
      fiche.patient = patient;

      activatedRoute.data = of({ fiche });
      comp.ngOnInit();

      expect(comp.hemarthrosesCollection).toContain(hemarthrose);
      expect(comp.hematomeSuperficielsCollection).toContain(hematomeSuperficiel);
      expect(comp.hematomePsoasCollection).toContain(hematomePsoas);
      expect(comp.hemorragiesCutaneoMuqueusesCollection).toContain(hemorragiesCutaneoMuqueuses);
      expect(comp.hemorragieVisceresCollection).toContain(hemorragieVisceres);
      expect(comp.saignementSNCSCollection).toContain(saignementSNC);
      expect(comp.usersSharedCollection).toContain(user);
      expect(comp.patientsSharedCollection).toContain(patient);
      expect(comp.fiche).toEqual(fiche);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFiche>>();
      const fiche = { id: 123 };
      jest.spyOn(ficheFormService, 'getFiche').mockReturnValue(fiche);
      jest.spyOn(ficheService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fiche });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: fiche }));
      saveSubject.complete();

      // THEN
      expect(ficheFormService.getFiche).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(ficheService.update).toHaveBeenCalledWith(expect.objectContaining(fiche));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFiche>>();
      const fiche = { id: 123 };
      jest.spyOn(ficheFormService, 'getFiche').mockReturnValue({ id: null });
      jest.spyOn(ficheService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fiche: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: fiche }));
      saveSubject.complete();

      // THEN
      expect(ficheFormService.getFiche).toHaveBeenCalled();
      expect(ficheService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFiche>>();
      const fiche = { id: 123 };
      jest.spyOn(ficheService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fiche });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(ficheService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareHemarthrose', () => {
      it('Should forward to hemarthroseService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(hemarthroseService, 'compareHemarthrose');
        comp.compareHemarthrose(entity, entity2);
        expect(hemarthroseService.compareHemarthrose).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareHematomeSuperficiel', () => {
      it('Should forward to hematomeSuperficielService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(hematomeSuperficielService, 'compareHematomeSuperficiel');
        comp.compareHematomeSuperficiel(entity, entity2);
        expect(hematomeSuperficielService.compareHematomeSuperficiel).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareHematomePsoas', () => {
      it('Should forward to hematomePsoasService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(hematomePsoasService, 'compareHematomePsoas');
        comp.compareHematomePsoas(entity, entity2);
        expect(hematomePsoasService.compareHematomePsoas).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareHemorragiesCutaneoMuqueuses', () => {
      it('Should forward to hemorragiesCutaneoMuqueusesService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(hemorragiesCutaneoMuqueusesService, 'compareHemorragiesCutaneoMuqueuses');
        comp.compareHemorragiesCutaneoMuqueuses(entity, entity2);
        expect(hemorragiesCutaneoMuqueusesService.compareHemorragiesCutaneoMuqueuses).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareHemorragieVisceres', () => {
      it('Should forward to hemorragieVisceresService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(hemorragieVisceresService, 'compareHemorragieVisceres');
        comp.compareHemorragieVisceres(entity, entity2);
        expect(hemorragieVisceresService.compareHemorragieVisceres).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareSaignementSNC', () => {
      it('Should forward to saignementSNCService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(saignementSNCService, 'compareSaignementSNC');
        comp.compareSaignementSNC(entity, entity2);
        expect(saignementSNCService.compareSaignementSNC).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareUser', () => {
      it('Should forward to userService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(userService, 'compareUser');
        comp.compareUser(entity, entity2);
        expect(userService.compareUser).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('comparePatient', () => {
      it('Should forward to patientService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(patientService, 'comparePatient');
        comp.comparePatient(entity, entity2);
        expect(patientService.comparePatient).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
