import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../hematome-psoas.test-samples';

import { HematomePsoasFormService } from './hematome-psoas-form.service';

describe('HematomePsoas Form Service', () => {
  let service: HematomePsoasFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HematomePsoasFormService);
  });

  describe('Service methods', () => {
    describe('createHematomePsoasFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createHematomePsoasFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            reponse: expect.any(Object),
            type: expect.any(Object),
            recidive: expect.any(Object),
          }),
        );
      });

      it('passing IHematomePsoas should create a new form with FormGroup', () => {
        const formGroup = service.createHematomePsoasFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            reponse: expect.any(Object),
            type: expect.any(Object),
            recidive: expect.any(Object),
          }),
        );
      });
    });

    describe('getHematomePsoas', () => {
      it('should return NewHematomePsoas for default HematomePsoas initial value', () => {
        const formGroup = service.createHematomePsoasFormGroup(sampleWithNewData);

        const hematomePsoas = service.getHematomePsoas(formGroup) as any;

        expect(hematomePsoas).toMatchObject(sampleWithNewData);
      });

      it('should return NewHematomePsoas for empty HematomePsoas initial value', () => {
        const formGroup = service.createHematomePsoasFormGroup();

        const hematomePsoas = service.getHematomePsoas(formGroup) as any;

        expect(hematomePsoas).toMatchObject({});
      });

      it('should return IHematomePsoas', () => {
        const formGroup = service.createHematomePsoasFormGroup(sampleWithRequiredData);

        const hematomePsoas = service.getHematomePsoas(formGroup) as any;

        expect(hematomePsoas).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IHematomePsoas should not enable id FormControl', () => {
        const formGroup = service.createHematomePsoasFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewHematomePsoas should disable id FormControl', () => {
        const formGroup = service.createHematomePsoasFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
