import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

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
import { DiagnosticType } from 'app/entities/enumerations/diagnostic-type.model';
import { OuiNonNP } from 'app/entities/enumerations/oui-non-np.model';
import { FormeHemophilie } from 'app/entities/enumerations/forme-hemophilie.model';
import { TestStatus } from 'app/entities/enumerations/test-status.model';
import { CircumstanceDecouverte } from 'app/entities/enumerations/circumstance-decouverte.model';
import { PriseEnChargeType } from 'app/entities/enumerations/prise-en-charge-type.model';
import { InjectionType } from 'app/entities/enumerations/injection-type.model';
import { TraitementType } from 'app/entities/enumerations/traitement-type.model';
import { TestResult } from 'app/entities/enumerations/test-result.model';
import { VieSocialeStatus } from 'app/entities/enumerations/vie-sociale-status.model';
import { FicheService } from '../service/fiche.service';
import { IFiche } from '../fiche.model';
import { FicheFormGroup, FicheFormService } from './fiche-form.service';

@Component({
  standalone: true,
  selector: 'jhi-fiche-update',
  templateUrl: './fiche-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FicheUpdateComponent implements OnInit {
  isSaving = false;
  fiche: IFiche | null = null;
  diagnosticTypeValues = Object.keys(DiagnosticType);
  ouiNonNPValues = Object.keys(OuiNonNP);
  formeHemophilieValues = Object.keys(FormeHemophilie);
  testStatusValues = Object.keys(TestStatus);
  circumstanceDecouverteValues = Object.keys(CircumstanceDecouverte);
  priseEnChargeTypeValues = Object.keys(PriseEnChargeType);
  injectionTypeValues = Object.keys(InjectionType);
  traitementTypeValues = Object.keys(TraitementType);
  testResultValues = Object.keys(TestResult);
  vieSocialeStatusValues = Object.keys(VieSocialeStatus);

  hemarthrosesCollection: IHemarthrose[] = [];
  hematomeSuperficielsCollection: IHematomeSuperficiel[] = [];
  hematomePsoasCollection: IHematomePsoas[] = [];
  hemorragiesCutaneoMuqueusesCollection: IHemorragiesCutaneoMuqueuses[] = [];
  hemorragieVisceresCollection: IHemorragieVisceres[] = [];
  saignementSNCSCollection: ISaignementSNC[] = [];
  usersSharedCollection: IUser[] = [];
  patientsSharedCollection: IPatient[] = [];

  protected ficheService = inject(FicheService);
  protected ficheFormService = inject(FicheFormService);
  protected hemarthroseService = inject(HemarthroseService);
  protected hematomeSuperficielService = inject(HematomeSuperficielService);
  protected hematomePsoasService = inject(HematomePsoasService);
  protected hemorragiesCutaneoMuqueusesService = inject(HemorragiesCutaneoMuqueusesService);
  protected hemorragieVisceresService = inject(HemorragieVisceresService);
  protected saignementSNCService = inject(SaignementSNCService);
  protected userService = inject(UserService);
  protected patientService = inject(PatientService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: FicheFormGroup = this.ficheFormService.createFicheFormGroup();

  compareHemarthrose = (o1: IHemarthrose | null, o2: IHemarthrose | null): boolean => this.hemarthroseService.compareHemarthrose(o1, o2);

  compareHematomeSuperficiel = (o1: IHematomeSuperficiel | null, o2: IHematomeSuperficiel | null): boolean =>
    this.hematomeSuperficielService.compareHematomeSuperficiel(o1, o2);

  compareHematomePsoas = (o1: IHematomePsoas | null, o2: IHematomePsoas | null): boolean =>
    this.hematomePsoasService.compareHematomePsoas(o1, o2);

  compareHemorragiesCutaneoMuqueuses = (o1: IHemorragiesCutaneoMuqueuses | null, o2: IHemorragiesCutaneoMuqueuses | null): boolean =>
    this.hemorragiesCutaneoMuqueusesService.compareHemorragiesCutaneoMuqueuses(o1, o2);

  compareHemorragieVisceres = (o1: IHemorragieVisceres | null, o2: IHemorragieVisceres | null): boolean =>
    this.hemorragieVisceresService.compareHemorragieVisceres(o1, o2);

  compareSaignementSNC = (o1: ISaignementSNC | null, o2: ISaignementSNC | null): boolean =>
    this.saignementSNCService.compareSaignementSNC(o1, o2);

  compareUser = (o1: IUser | null, o2: IUser | null): boolean => this.userService.compareUser(o1, o2);

  comparePatient = (o1: IPatient | null, o2: IPatient | null): boolean => this.patientService.comparePatient(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fiche }) => {
      this.fiche = fiche;
      if (fiche) {
        this.updateForm(fiche);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fiche = this.ficheFormService.getFiche(this.editForm);
    if (fiche.id !== null) {
      this.subscribeToSaveResponse(this.ficheService.update(fiche));
    } else {
      this.subscribeToSaveResponse(this.ficheService.create(fiche));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFiche>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(fiche: IFiche): void {
    this.fiche = fiche;
    this.ficheFormService.resetForm(this.editForm, fiche);

    this.hemarthrosesCollection = this.hemarthroseService.addHemarthroseToCollectionIfMissing<IHemarthrose>(
      this.hemarthrosesCollection,
      fiche.hemarthrose,
    );
    this.hematomeSuperficielsCollection = this.hematomeSuperficielService.addHematomeSuperficielToCollectionIfMissing<IHematomeSuperficiel>(
      this.hematomeSuperficielsCollection,
      fiche.hematomeSuperficiel,
    );
    this.hematomePsoasCollection = this.hematomePsoasService.addHematomePsoasToCollectionIfMissing<IHematomePsoas>(
      this.hematomePsoasCollection,
      fiche.hematomePsoas,
    );
    this.hemorragiesCutaneoMuqueusesCollection =
      this.hemorragiesCutaneoMuqueusesService.addHemorragiesCutaneoMuqueusesToCollectionIfMissing<IHemorragiesCutaneoMuqueuses>(
        this.hemorragiesCutaneoMuqueusesCollection,
        fiche.hemorragiesCutaneoMuqueuses,
      );
    this.hemorragieVisceresCollection = this.hemorragieVisceresService.addHemorragieVisceresToCollectionIfMissing<IHemorragieVisceres>(
      this.hemorragieVisceresCollection,
      fiche.hemorragieVisceres,
    );
    this.saignementSNCSCollection = this.saignementSNCService.addSaignementSNCToCollectionIfMissing<ISaignementSNC>(
      this.saignementSNCSCollection,
      fiche.saignementSNC,
    );
    this.usersSharedCollection = this.userService.addUserToCollectionIfMissing<IUser>(this.usersSharedCollection, fiche.user);
    this.patientsSharedCollection = this.patientService.addPatientToCollectionIfMissing<IPatient>(
      this.patientsSharedCollection,
      fiche.patient,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.hemarthroseService
      .query({ filter: 'fiche-is-null' })
      .pipe(map((res: HttpResponse<IHemarthrose[]>) => res.body ?? []))
      .pipe(
        map((hemarthroses: IHemarthrose[]) =>
          this.hemarthroseService.addHemarthroseToCollectionIfMissing<IHemarthrose>(hemarthroses, this.fiche?.hemarthrose),
        ),
      )
      .subscribe((hemarthroses: IHemarthrose[]) => (this.hemarthrosesCollection = hemarthroses));

    this.hematomeSuperficielService
      .query({ filter: 'fiche-is-null' })
      .pipe(map((res: HttpResponse<IHematomeSuperficiel[]>) => res.body ?? []))
      .pipe(
        map((hematomeSuperficiels: IHematomeSuperficiel[]) =>
          this.hematomeSuperficielService.addHematomeSuperficielToCollectionIfMissing<IHematomeSuperficiel>(
            hematomeSuperficiels,
            this.fiche?.hematomeSuperficiel,
          ),
        ),
      )
      .subscribe((hematomeSuperficiels: IHematomeSuperficiel[]) => (this.hematomeSuperficielsCollection = hematomeSuperficiels));

    this.hematomePsoasService
      .query({ filter: 'fiche-is-null' })
      .pipe(map((res: HttpResponse<IHematomePsoas[]>) => res.body ?? []))
      .pipe(
        map((hematomePsoas: IHematomePsoas[]) =>
          this.hematomePsoasService.addHematomePsoasToCollectionIfMissing<IHematomePsoas>(hematomePsoas, this.fiche?.hematomePsoas),
        ),
      )
      .subscribe((hematomePsoas: IHematomePsoas[]) => (this.hematomePsoasCollection = hematomePsoas));

    this.hemorragiesCutaneoMuqueusesService
      .query({ filter: 'fiche-is-null' })
      .pipe(map((res: HttpResponse<IHemorragiesCutaneoMuqueuses[]>) => res.body ?? []))
      .pipe(
        map((hemorragiesCutaneoMuqueuses: IHemorragiesCutaneoMuqueuses[]) =>
          this.hemorragiesCutaneoMuqueusesService.addHemorragiesCutaneoMuqueusesToCollectionIfMissing<IHemorragiesCutaneoMuqueuses>(
            hemorragiesCutaneoMuqueuses,
            this.fiche?.hemorragiesCutaneoMuqueuses,
          ),
        ),
      )
      .subscribe(
        (hemorragiesCutaneoMuqueuses: IHemorragiesCutaneoMuqueuses[]) =>
          (this.hemorragiesCutaneoMuqueusesCollection = hemorragiesCutaneoMuqueuses),
      );

    this.hemorragieVisceresService
      .query({ filter: 'fiche-is-null' })
      .pipe(map((res: HttpResponse<IHemorragieVisceres[]>) => res.body ?? []))
      .pipe(
        map((hemorragieVisceres: IHemorragieVisceres[]) =>
          this.hemorragieVisceresService.addHemorragieVisceresToCollectionIfMissing<IHemorragieVisceres>(
            hemorragieVisceres,
            this.fiche?.hemorragieVisceres,
          ),
        ),
      )
      .subscribe((hemorragieVisceres: IHemorragieVisceres[]) => (this.hemorragieVisceresCollection = hemorragieVisceres));

    this.saignementSNCService
      .query({ filter: 'fiche-is-null' })
      .pipe(map((res: HttpResponse<ISaignementSNC[]>) => res.body ?? []))
      .pipe(
        map((saignementSNCS: ISaignementSNC[]) =>
          this.saignementSNCService.addSaignementSNCToCollectionIfMissing<ISaignementSNC>(saignementSNCS, this.fiche?.saignementSNC),
        ),
      )
      .subscribe((saignementSNCS: ISaignementSNC[]) => (this.saignementSNCSCollection = saignementSNCS));

    this.userService
      .query()
      .pipe(map((res: HttpResponse<IUser[]>) => res.body ?? []))
      .pipe(map((users: IUser[]) => this.userService.addUserToCollectionIfMissing<IUser>(users, this.fiche?.user)))
      .subscribe((users: IUser[]) => (this.usersSharedCollection = users));

    this.patientService
      .query()
      .pipe(map((res: HttpResponse<IPatient[]>) => res.body ?? []))
      .pipe(map((patients: IPatient[]) => this.patientService.addPatientToCollectionIfMissing<IPatient>(patients, this.fiche?.patient)))
      .subscribe((patients: IPatient[]) => (this.patientsSharedCollection = patients));
  }
}
