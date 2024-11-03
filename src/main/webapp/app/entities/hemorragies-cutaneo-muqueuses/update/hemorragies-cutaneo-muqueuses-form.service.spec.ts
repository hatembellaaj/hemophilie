import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../hemorragies-cutaneo-muqueuses.test-samples';

import { HemorragiesCutaneoMuqueusesFormService } from './hemorragies-cutaneo-muqueuses-form.service';

describe('HemorragiesCutaneoMuqueuses Form Service', () => {
  let service: HemorragiesCutaneoMuqueusesFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HemorragiesCutaneoMuqueusesFormService);
  });

  describe('Service methods', () => {
    describe('createHemorragiesCutaneoMuqueusesFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createHemorragiesCutaneoMuqueusesFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            reponse: expect.any(Object),
            type: expect.any(Object),
            frequencePerAn: expect.any(Object),
          }),
        );
      });

      it('passing IHemorragiesCutaneoMuqueuses should create a new form with FormGroup', () => {
        const formGroup = service.createHemorragiesCutaneoMuqueusesFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            reponse: expect.any(Object),
            type: expect.any(Object),
            frequencePerAn: expect.any(Object),
          }),
        );
      });
    });

    describe('getHemorragiesCutaneoMuqueuses', () => {
      it('should return NewHemorragiesCutaneoMuqueuses for default HemorragiesCutaneoMuqueuses initial value', () => {
        const formGroup = service.createHemorragiesCutaneoMuqueusesFormGroup(sampleWithNewData);

        const hemorragiesCutaneoMuqueuses = service.getHemorragiesCutaneoMuqueuses(formGroup) as any;

        expect(hemorragiesCutaneoMuqueuses).toMatchObject(sampleWithNewData);
      });

      it('should return NewHemorragiesCutaneoMuqueuses for empty HemorragiesCutaneoMuqueuses initial value', () => {
        const formGroup = service.createHemorragiesCutaneoMuqueusesFormGroup();

        const hemorragiesCutaneoMuqueuses = service.getHemorragiesCutaneoMuqueuses(formGroup) as any;

        expect(hemorragiesCutaneoMuqueuses).toMatchObject({});
      });

      it('should return IHemorragiesCutaneoMuqueuses', () => {
        const formGroup = service.createHemorragiesCutaneoMuqueusesFormGroup(sampleWithRequiredData);

        const hemorragiesCutaneoMuqueuses = service.getHemorragiesCutaneoMuqueuses(formGroup) as any;

        expect(hemorragiesCutaneoMuqueuses).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IHemorragiesCutaneoMuqueuses should not enable id FormControl', () => {
        const formGroup = service.createHemorragiesCutaneoMuqueusesFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewHemorragiesCutaneoMuqueuses should disable id FormControl', () => {
        const formGroup = service.createHemorragiesCutaneoMuqueusesFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
