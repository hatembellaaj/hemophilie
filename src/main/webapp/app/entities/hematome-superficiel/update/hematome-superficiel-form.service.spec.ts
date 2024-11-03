import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../hematome-superficiel.test-samples';

import { HematomeSuperficielFormService } from './hematome-superficiel-form.service';

describe('HematomeSuperficiel Form Service', () => {
  let service: HematomeSuperficielFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HematomeSuperficielFormService);
  });

  describe('Service methods', () => {
    describe('createHematomeSuperficielFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createHematomeSuperficielFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            reponse: expect.any(Object),
            type: expect.any(Object),
            siege: expect.any(Object),
          }),
        );
      });

      it('passing IHematomeSuperficiel should create a new form with FormGroup', () => {
        const formGroup = service.createHematomeSuperficielFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            reponse: expect.any(Object),
            type: expect.any(Object),
            siege: expect.any(Object),
          }),
        );
      });
    });

    describe('getHematomeSuperficiel', () => {
      it('should return NewHematomeSuperficiel for default HematomeSuperficiel initial value', () => {
        const formGroup = service.createHematomeSuperficielFormGroup(sampleWithNewData);

        const hematomeSuperficiel = service.getHematomeSuperficiel(formGroup) as any;

        expect(hematomeSuperficiel).toMatchObject(sampleWithNewData);
      });

      it('should return NewHematomeSuperficiel for empty HematomeSuperficiel initial value', () => {
        const formGroup = service.createHematomeSuperficielFormGroup();

        const hematomeSuperficiel = service.getHematomeSuperficiel(formGroup) as any;

        expect(hematomeSuperficiel).toMatchObject({});
      });

      it('should return IHematomeSuperficiel', () => {
        const formGroup = service.createHematomeSuperficielFormGroup(sampleWithRequiredData);

        const hematomeSuperficiel = service.getHematomeSuperficiel(formGroup) as any;

        expect(hematomeSuperficiel).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IHematomeSuperficiel should not enable id FormControl', () => {
        const formGroup = service.createHematomeSuperficielFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewHematomeSuperficiel should disable id FormControl', () => {
        const formGroup = service.createHematomeSuperficielFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
