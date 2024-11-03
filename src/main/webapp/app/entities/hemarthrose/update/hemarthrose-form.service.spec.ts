import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../hemarthrose.test-samples';

import { HemarthroseFormService } from './hemarthrose-form.service';

describe('Hemarthrose Form Service', () => {
  let service: HemarthroseFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HemarthroseFormService);
  });

  describe('Service methods', () => {
    describe('createHemarthroseFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createHemarthroseFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            reponse: expect.any(Object),
            type: expect.any(Object),
            siege: expect.any(Object),
            frequencePerAn: expect.any(Object),
          }),
        );
      });

      it('passing IHemarthrose should create a new form with FormGroup', () => {
        const formGroup = service.createHemarthroseFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            reponse: expect.any(Object),
            type: expect.any(Object),
            siege: expect.any(Object),
            frequencePerAn: expect.any(Object),
          }),
        );
      });
    });

    describe('getHemarthrose', () => {
      it('should return NewHemarthrose for default Hemarthrose initial value', () => {
        const formGroup = service.createHemarthroseFormGroup(sampleWithNewData);

        const hemarthrose = service.getHemarthrose(formGroup) as any;

        expect(hemarthrose).toMatchObject(sampleWithNewData);
      });

      it('should return NewHemarthrose for empty Hemarthrose initial value', () => {
        const formGroup = service.createHemarthroseFormGroup();

        const hemarthrose = service.getHemarthrose(formGroup) as any;

        expect(hemarthrose).toMatchObject({});
      });

      it('should return IHemarthrose', () => {
        const formGroup = service.createHemarthroseFormGroup(sampleWithRequiredData);

        const hemarthrose = service.getHemarthrose(formGroup) as any;

        expect(hemarthrose).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IHemarthrose should not enable id FormControl', () => {
        const formGroup = service.createHemarthroseFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewHemarthrose should disable id FormControl', () => {
        const formGroup = service.createHemarthroseFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
