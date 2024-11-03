import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../saignement-snc.test-samples';

import { SaignementSNCFormService } from './saignement-snc-form.service';

describe('SaignementSNC Form Service', () => {
  let service: SaignementSNCFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SaignementSNCFormService);
  });

  describe('Service methods', () => {
    describe('createSaignementSNCFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSaignementSNCFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            reponse: expect.any(Object),
            evolution: expect.any(Object),
          }),
        );
      });

      it('passing ISaignementSNC should create a new form with FormGroup', () => {
        const formGroup = service.createSaignementSNCFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            reponse: expect.any(Object),
            evolution: expect.any(Object),
          }),
        );
      });
    });

    describe('getSaignementSNC', () => {
      it('should return NewSaignementSNC for default SaignementSNC initial value', () => {
        const formGroup = service.createSaignementSNCFormGroup(sampleWithNewData);

        const saignementSNC = service.getSaignementSNC(formGroup) as any;

        expect(saignementSNC).toMatchObject(sampleWithNewData);
      });

      it('should return NewSaignementSNC for empty SaignementSNC initial value', () => {
        const formGroup = service.createSaignementSNCFormGroup();

        const saignementSNC = service.getSaignementSNC(formGroup) as any;

        expect(saignementSNC).toMatchObject({});
      });

      it('should return ISaignementSNC', () => {
        const formGroup = service.createSaignementSNCFormGroup(sampleWithRequiredData);

        const saignementSNC = service.getSaignementSNC(formGroup) as any;

        expect(saignementSNC).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISaignementSNC should not enable id FormControl', () => {
        const formGroup = service.createSaignementSNCFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSaignementSNC should disable id FormControl', () => {
        const formGroup = service.createSaignementSNCFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
