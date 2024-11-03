import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../hemorragie-visceres.test-samples';

import { HemorragieVisceresFormService } from './hemorragie-visceres-form.service';

describe('HemorragieVisceres Form Service', () => {
  let service: HemorragieVisceresFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HemorragieVisceresFormService);
  });

  describe('Service methods', () => {
    describe('createHemorragieVisceresFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createHemorragieVisceresFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            reponse: expect.any(Object),
            type: expect.any(Object),
            exploration: expect.any(Object),
            examenLesion: expect.any(Object),
          }),
        );
      });

      it('passing IHemorragieVisceres should create a new form with FormGroup', () => {
        const formGroup = service.createHemorragieVisceresFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            reponse: expect.any(Object),
            type: expect.any(Object),
            exploration: expect.any(Object),
            examenLesion: expect.any(Object),
          }),
        );
      });
    });

    describe('getHemorragieVisceres', () => {
      it('should return NewHemorragieVisceres for default HemorragieVisceres initial value', () => {
        const formGroup = service.createHemorragieVisceresFormGroup(sampleWithNewData);

        const hemorragieVisceres = service.getHemorragieVisceres(formGroup) as any;

        expect(hemorragieVisceres).toMatchObject(sampleWithNewData);
      });

      it('should return NewHemorragieVisceres for empty HemorragieVisceres initial value', () => {
        const formGroup = service.createHemorragieVisceresFormGroup();

        const hemorragieVisceres = service.getHemorragieVisceres(formGroup) as any;

        expect(hemorragieVisceres).toMatchObject({});
      });

      it('should return IHemorragieVisceres', () => {
        const formGroup = service.createHemorragieVisceresFormGroup(sampleWithRequiredData);

        const hemorragieVisceres = service.getHemorragieVisceres(formGroup) as any;

        expect(hemorragieVisceres).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IHemorragieVisceres should not enable id FormControl', () => {
        const formGroup = service.createHemorragieVisceresFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewHemorragieVisceres should disable id FormControl', () => {
        const formGroup = service.createHemorragieVisceresFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
