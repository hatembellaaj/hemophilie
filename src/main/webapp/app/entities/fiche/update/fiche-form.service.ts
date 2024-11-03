import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IFiche, NewFiche } from '../fiche.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFiche for edit and NewFicheFormGroupInput for create.
 */
type FicheFormGroupInput = IFiche | PartialWithRequiredKeyOf<NewFiche>;

type FicheFormDefaults = Pick<NewFiche, 'id' | 'consentementRegistre'>;

type FicheFormGroupContent = {
  id: FormControl<IFiche['id'] | NewFiche['id']>;
  dossierNumber: FormControl<IFiche['dossierNumber']>;
  ordreNumber: FormControl<IFiche['ordreNumber']>;
  indexNumber: FormControl<IFiche['indexNumber']>;
  anneeDiagnostic: FormControl<IFiche['anneeDiagnostic']>;
  diagnostic: FormControl<IFiche['diagnostic']>;
  autreDiagnostic: FormControl<IFiche['autreDiagnostic']>;
  dateEnregistrementRegistre: FormControl<IFiche['dateEnregistrementRegistre']>;
  consentementRegistre: FormControl<IFiche['consentementRegistre']>;
  parentsConsanguins: FormControl<IFiche['parentsConsanguins']>;
  degreParenteConsanguins: FormControl<IFiche['degreParenteConsanguins']>;
  casSimilairesFamille: FormControl<IFiche['casSimilairesFamille']>;
  nbreCasSimilaires: FormControl<IFiche['nbreCasSimilaires']>;
  degreParenteCasSimilaires: FormControl<IFiche['degreParenteCasSimilaires']>;
  casDecesSyndromeHemorragique: FormControl<IFiche['casDecesSyndromeHemorragique']>;
  nbreCasDeces: FormControl<IFiche['nbreCasDeces']>;
  formeHemophilie: FormControl<IFiche['formeHemophilie']>;
  nbreFreres: FormControl<IFiche['nbreFreres']>;
  nbreSoeurs: FormControl<IFiche['nbreSoeurs']>;
  dateTestConfirmation: FormControl<IFiche['dateTestConfirmation']>;
  hemogrammeHb: FormControl<IFiche['hemogrammeHb']>;
  hemogrammeHt: FormControl<IFiche['hemogrammeHt']>;
  plaquettes: FormControl<IFiche['plaquettes']>;
  tp: FormControl<IFiche['tp']>;
  fibrinogene: FormControl<IFiche['fibrinogene']>;
  tcaMT: FormControl<IFiche['tcaMT']>;
  tcaMT2h: FormControl<IFiche['tcaMT2h']>;
  tcaTemoin2h: FormControl<IFiche['tcaTemoin2h']>;
  ts: FormControl<IFiche['ts']>;
  circumstanceDecouverte: FormControl<IFiche['circumstanceDecouverte']>;
  date1erSigneClinique: FormControl<IFiche['date1erSigneClinique']>;
  ageDiagnostic: FormControl<IFiche['ageDiagnostic']>;
  priseEnCharge: FormControl<IFiche['priseEnCharge']>;
  causePriseEnCharge: FormControl<IFiche['causePriseEnCharge']>;
  doseProphylaxie: FormControl<IFiche['doseProphylaxie']>;
  frequenceProphylaxie: FormControl<IFiche['frequenceProphylaxie']>;
  debutProphylaxie: FormControl<IFiche['debutProphylaxie']>;
  modaliteInjection: FormControl<IFiche['modaliteInjection']>;
  typeTraitementSubstitutif: FormControl<IFiche['typeTraitementSubstitutif']>;
  age1ereSubstitution: FormControl<IFiche['age1ereSubstitution']>;
  psl: FormControl<IFiche['psl']>;
  plasmaFraisCongele: FormControl<IFiche['plasmaFraisCongele']>;
  cryoprecipite: FormControl<IFiche['cryoprecipite']>;
  complicationsOrthopediques: FormControl<IFiche['complicationsOrthopediques']>;
  complicationInhibiteurs: FormControl<IFiche['complicationInhibiteurs']>;
  testRecuperationFAH: FormControl<IFiche['testRecuperationFAH']>;
  resultatTestRecuperation: FormControl<IFiche['resultatTestRecuperation']>;
  vieSociale: FormControl<IFiche['vieSociale']>;
  etatMarital: FormControl<IFiche['etatMarital']>;
  enfants: FormControl<IFiche['enfants']>;
  handicap: FormControl<IFiche['handicap']>;
  typeHandicap: FormControl<IFiche['typeHandicap']>;
  activiteSportive: FormControl<IFiche['activiteSportive']>;
  typeActiviteSportive: FormControl<IFiche['typeActiviteSportive']>;
  decede: FormControl<IFiche['decede']>;
  causeDateDeces: FormControl<IFiche['causeDateDeces']>;
  hemarthrose: FormControl<IFiche['hemarthrose']>;
  hematomeSuperficiel: FormControl<IFiche['hematomeSuperficiel']>;
  hematomePsoas: FormControl<IFiche['hematomePsoas']>;
  hemorragiesCutaneoMuqueuses: FormControl<IFiche['hemorragiesCutaneoMuqueuses']>;
  hemorragieVisceres: FormControl<IFiche['hemorragieVisceres']>;
  saignementSNC: FormControl<IFiche['saignementSNC']>;
  user: FormControl<IFiche['user']>;
  patient: FormControl<IFiche['patient']>;
};

export type FicheFormGroup = FormGroup<FicheFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FicheFormService {
  createFicheFormGroup(fiche: FicheFormGroupInput = { id: null }): FicheFormGroup {
    const ficheRawValue = {
      ...this.getFormDefaults(),
      ...fiche,
    };
    return new FormGroup<FicheFormGroupContent>({
      id: new FormControl(
        { value: ficheRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dossierNumber: new FormControl(ficheRawValue.dossierNumber, {
        validators: [Validators.required],
      }),
      ordreNumber: new FormControl(ficheRawValue.ordreNumber),
      indexNumber: new FormControl(ficheRawValue.indexNumber),
      anneeDiagnostic: new FormControl(ficheRawValue.anneeDiagnostic),
      diagnostic: new FormControl(ficheRawValue.diagnostic, {
        validators: [Validators.required],
      }),
      autreDiagnostic: new FormControl(ficheRawValue.autreDiagnostic),
      dateEnregistrementRegistre: new FormControl(ficheRawValue.dateEnregistrementRegistre),
      consentementRegistre: new FormControl(ficheRawValue.consentementRegistre),
      parentsConsanguins: new FormControl(ficheRawValue.parentsConsanguins),
      degreParenteConsanguins: new FormControl(ficheRawValue.degreParenteConsanguins),
      casSimilairesFamille: new FormControl(ficheRawValue.casSimilairesFamille),
      nbreCasSimilaires: new FormControl(ficheRawValue.nbreCasSimilaires),
      degreParenteCasSimilaires: new FormControl(ficheRawValue.degreParenteCasSimilaires),
      casDecesSyndromeHemorragique: new FormControl(ficheRawValue.casDecesSyndromeHemorragique),
      nbreCasDeces: new FormControl(ficheRawValue.nbreCasDeces),
      formeHemophilie: new FormControl(ficheRawValue.formeHemophilie),
      nbreFreres: new FormControl(ficheRawValue.nbreFreres),
      nbreSoeurs: new FormControl(ficheRawValue.nbreSoeurs),
      dateTestConfirmation: new FormControl(ficheRawValue.dateTestConfirmation),
      hemogrammeHb: new FormControl(ficheRawValue.hemogrammeHb),
      hemogrammeHt: new FormControl(ficheRawValue.hemogrammeHt),
      plaquettes: new FormControl(ficheRawValue.plaquettes),
      tp: new FormControl(ficheRawValue.tp),
      fibrinogene: new FormControl(ficheRawValue.fibrinogene),
      tcaMT: new FormControl(ficheRawValue.tcaMT),
      tcaMT2h: new FormControl(ficheRawValue.tcaMT2h),
      tcaTemoin2h: new FormControl(ficheRawValue.tcaTemoin2h),
      ts: new FormControl(ficheRawValue.ts),
      circumstanceDecouverte: new FormControl(ficheRawValue.circumstanceDecouverte),
      date1erSigneClinique: new FormControl(ficheRawValue.date1erSigneClinique),
      ageDiagnostic: new FormControl(ficheRawValue.ageDiagnostic),
      priseEnCharge: new FormControl(ficheRawValue.priseEnCharge, {
        validators: [Validators.required],
      }),
      causePriseEnCharge: new FormControl(ficheRawValue.causePriseEnCharge),
      doseProphylaxie: new FormControl(ficheRawValue.doseProphylaxie),
      frequenceProphylaxie: new FormControl(ficheRawValue.frequenceProphylaxie),
      debutProphylaxie: new FormControl(ficheRawValue.debutProphylaxie),
      modaliteInjection: new FormControl(ficheRawValue.modaliteInjection),
      typeTraitementSubstitutif: new FormControl(ficheRawValue.typeTraitementSubstitutif),
      age1ereSubstitution: new FormControl(ficheRawValue.age1ereSubstitution),
      psl: new FormControl(ficheRawValue.psl),
      plasmaFraisCongele: new FormControl(ficheRawValue.plasmaFraisCongele),
      cryoprecipite: new FormControl(ficheRawValue.cryoprecipite),
      complicationsOrthopediques: new FormControl(ficheRawValue.complicationsOrthopediques),
      complicationInhibiteurs: new FormControl(ficheRawValue.complicationInhibiteurs),
      testRecuperationFAH: new FormControl(ficheRawValue.testRecuperationFAH),
      resultatTestRecuperation: new FormControl(ficheRawValue.resultatTestRecuperation),
      vieSociale: new FormControl(ficheRawValue.vieSociale),
      etatMarital: new FormControl(ficheRawValue.etatMarital),
      enfants: new FormControl(ficheRawValue.enfants),
      handicap: new FormControl(ficheRawValue.handicap),
      typeHandicap: new FormControl(ficheRawValue.typeHandicap),
      activiteSportive: new FormControl(ficheRawValue.activiteSportive),
      typeActiviteSportive: new FormControl(ficheRawValue.typeActiviteSportive),
      decede: new FormControl(ficheRawValue.decede),
      causeDateDeces: new FormControl(ficheRawValue.causeDateDeces),
      hemarthrose: new FormControl(ficheRawValue.hemarthrose),
      hematomeSuperficiel: new FormControl(ficheRawValue.hematomeSuperficiel),
      hematomePsoas: new FormControl(ficheRawValue.hematomePsoas),
      hemorragiesCutaneoMuqueuses: new FormControl(ficheRawValue.hemorragiesCutaneoMuqueuses),
      hemorragieVisceres: new FormControl(ficheRawValue.hemorragieVisceres),
      saignementSNC: new FormControl(ficheRawValue.saignementSNC),
      user: new FormControl(ficheRawValue.user),
      patient: new FormControl(ficheRawValue.patient),
    });
  }

  getFiche(form: FicheFormGroup): IFiche | NewFiche {
    return form.getRawValue() as IFiche | NewFiche;
  }

  resetForm(form: FicheFormGroup, fiche: FicheFormGroupInput): void {
    const ficheRawValue = { ...this.getFormDefaults(), ...fiche };
    form.reset(
      {
        ...ficheRawValue,
        id: { value: ficheRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): FicheFormDefaults {
    return {
      id: null,
      consentementRegistre: false,
    };
  }
}
