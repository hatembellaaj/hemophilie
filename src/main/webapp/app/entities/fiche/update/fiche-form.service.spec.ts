import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../fiche.test-samples';

import { FicheFormService } from './fiche-form.service';

describe('Fiche Form Service', () => {
  let service: FicheFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FicheFormService);
  });

  describe('Service methods', () => {
    describe('createFicheFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFicheFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dossierNumber: expect.any(Object),
            ordreNumber: expect.any(Object),
            indexNumber: expect.any(Object),
            anneeDiagnostic: expect.any(Object),
            diagnostic: expect.any(Object),
            autreDiagnostic: expect.any(Object),
            dateEnregistrementRegistre: expect.any(Object),
            consentementRegistre: expect.any(Object),
            parentsConsanguins: expect.any(Object),
            degreParenteConsanguins: expect.any(Object),
            casSimilairesFamille: expect.any(Object),
            nbreCasSimilaires: expect.any(Object),
            degreParenteCasSimilaires: expect.any(Object),
            casDecesSyndromeHemorragique: expect.any(Object),
            nbreCasDeces: expect.any(Object),
            formeHemophilie: expect.any(Object),
            nbreFreres: expect.any(Object),
            nbreSoeurs: expect.any(Object),
            dateTestConfirmation: expect.any(Object),
            hemogrammeHb: expect.any(Object),
            hemogrammeHt: expect.any(Object),
            plaquettes: expect.any(Object),
            tp: expect.any(Object),
            fibrinogene: expect.any(Object),
            tcaMT: expect.any(Object),
            tcaMT2h: expect.any(Object),
            tcaTemoin2h: expect.any(Object),
            ts: expect.any(Object),
            circumstanceDecouverte: expect.any(Object),
            date1erSigneClinique: expect.any(Object),
            ageDiagnostic: expect.any(Object),
            priseEnCharge: expect.any(Object),
            causePriseEnCharge: expect.any(Object),
            doseProphylaxie: expect.any(Object),
            frequenceProphylaxie: expect.any(Object),
            debutProphylaxie: expect.any(Object),
            modaliteInjection: expect.any(Object),
            typeTraitementSubstitutif: expect.any(Object),
            age1ereSubstitution: expect.any(Object),
            psl: expect.any(Object),
            plasmaFraisCongele: expect.any(Object),
            cryoprecipite: expect.any(Object),
            complicationsOrthopediques: expect.any(Object),
            complicationInhibiteurs: expect.any(Object),
            testRecuperationFAH: expect.any(Object),
            resultatTestRecuperation: expect.any(Object),
            vieSociale: expect.any(Object),
            etatMarital: expect.any(Object),
            enfants: expect.any(Object),
            handicap: expect.any(Object),
            typeHandicap: expect.any(Object),
            activiteSportive: expect.any(Object),
            typeActiviteSportive: expect.any(Object),
            decede: expect.any(Object),
            causeDateDeces: expect.any(Object),
            hemarthrose: expect.any(Object),
            hematomeSuperficiel: expect.any(Object),
            hematomePsoas: expect.any(Object),
            hemorragiesCutaneoMuqueuses: expect.any(Object),
            hemorragieVisceres: expect.any(Object),
            saignementSNC: expect.any(Object),
            user: expect.any(Object),
            patient: expect.any(Object),
          }),
        );
      });

      it('passing IFiche should create a new form with FormGroup', () => {
        const formGroup = service.createFicheFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dossierNumber: expect.any(Object),
            ordreNumber: expect.any(Object),
            indexNumber: expect.any(Object),
            anneeDiagnostic: expect.any(Object),
            diagnostic: expect.any(Object),
            autreDiagnostic: expect.any(Object),
            dateEnregistrementRegistre: expect.any(Object),
            consentementRegistre: expect.any(Object),
            parentsConsanguins: expect.any(Object),
            degreParenteConsanguins: expect.any(Object),
            casSimilairesFamille: expect.any(Object),
            nbreCasSimilaires: expect.any(Object),
            degreParenteCasSimilaires: expect.any(Object),
            casDecesSyndromeHemorragique: expect.any(Object),
            nbreCasDeces: expect.any(Object),
            formeHemophilie: expect.any(Object),
            nbreFreres: expect.any(Object),
            nbreSoeurs: expect.any(Object),
            dateTestConfirmation: expect.any(Object),
            hemogrammeHb: expect.any(Object),
            hemogrammeHt: expect.any(Object),
            plaquettes: expect.any(Object),
            tp: expect.any(Object),
            fibrinogene: expect.any(Object),
            tcaMT: expect.any(Object),
            tcaMT2h: expect.any(Object),
            tcaTemoin2h: expect.any(Object),
            ts: expect.any(Object),
            circumstanceDecouverte: expect.any(Object),
            date1erSigneClinique: expect.any(Object),
            ageDiagnostic: expect.any(Object),
            priseEnCharge: expect.any(Object),
            causePriseEnCharge: expect.any(Object),
            doseProphylaxie: expect.any(Object),
            frequenceProphylaxie: expect.any(Object),
            debutProphylaxie: expect.any(Object),
            modaliteInjection: expect.any(Object),
            typeTraitementSubstitutif: expect.any(Object),
            age1ereSubstitution: expect.any(Object),
            psl: expect.any(Object),
            plasmaFraisCongele: expect.any(Object),
            cryoprecipite: expect.any(Object),
            complicationsOrthopediques: expect.any(Object),
            complicationInhibiteurs: expect.any(Object),
            testRecuperationFAH: expect.any(Object),
            resultatTestRecuperation: expect.any(Object),
            vieSociale: expect.any(Object),
            etatMarital: expect.any(Object),
            enfants: expect.any(Object),
            handicap: expect.any(Object),
            typeHandicap: expect.any(Object),
            activiteSportive: expect.any(Object),
            typeActiviteSportive: expect.any(Object),
            decede: expect.any(Object),
            causeDateDeces: expect.any(Object),
            hemarthrose: expect.any(Object),
            hematomeSuperficiel: expect.any(Object),
            hematomePsoas: expect.any(Object),
            hemorragiesCutaneoMuqueuses: expect.any(Object),
            hemorragieVisceres: expect.any(Object),
            saignementSNC: expect.any(Object),
            user: expect.any(Object),
            patient: expect.any(Object),
          }),
        );
      });
    });

    describe('getFiche', () => {
      it('should return NewFiche for default Fiche initial value', () => {
        const formGroup = service.createFicheFormGroup(sampleWithNewData);

        const fiche = service.getFiche(formGroup) as any;

        expect(fiche).toMatchObject(sampleWithNewData);
      });

      it('should return NewFiche for empty Fiche initial value', () => {
        const formGroup = service.createFicheFormGroup();

        const fiche = service.getFiche(formGroup) as any;

        expect(fiche).toMatchObject({});
      });

      it('should return IFiche', () => {
        const formGroup = service.createFicheFormGroup(sampleWithRequiredData);

        const fiche = service.getFiche(formGroup) as any;

        expect(fiche).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFiche should not enable id FormControl', () => {
        const formGroup = service.createFicheFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFiche should disable id FormControl', () => {
        const formGroup = service.createFicheFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
