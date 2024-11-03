import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IHematomeSuperficiel, NewHematomeSuperficiel } from '../hematome-superficiel.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IHematomeSuperficiel for edit and NewHematomeSuperficielFormGroupInput for create.
 */
type HematomeSuperficielFormGroupInput = IHematomeSuperficiel | PartialWithRequiredKeyOf<NewHematomeSuperficiel>;

type HematomeSuperficielFormDefaults = Pick<NewHematomeSuperficiel, 'id'>;

type HematomeSuperficielFormGroupContent = {
  id: FormControl<IHematomeSuperficiel['id'] | NewHematomeSuperficiel['id']>;
  reponse: FormControl<IHematomeSuperficiel['reponse']>;
  type: FormControl<IHematomeSuperficiel['type']>;
  siege: FormControl<IHematomeSuperficiel['siege']>;
};

export type HematomeSuperficielFormGroup = FormGroup<HematomeSuperficielFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class HematomeSuperficielFormService {
  createHematomeSuperficielFormGroup(hematomeSuperficiel: HematomeSuperficielFormGroupInput = { id: null }): HematomeSuperficielFormGroup {
    const hematomeSuperficielRawValue = {
      ...this.getFormDefaults(),
      ...hematomeSuperficiel,
    };
    return new FormGroup<HematomeSuperficielFormGroupContent>({
      id: new FormControl(
        { value: hematomeSuperficielRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      reponse: new FormControl(hematomeSuperficielRawValue.reponse),
      type: new FormControl(hematomeSuperficielRawValue.type),
      siege: new FormControl(hematomeSuperficielRawValue.siege),
    });
  }

  getHematomeSuperficiel(form: HematomeSuperficielFormGroup): IHematomeSuperficiel | NewHematomeSuperficiel {
    return form.getRawValue() as IHematomeSuperficiel | NewHematomeSuperficiel;
  }

  resetForm(form: HematomeSuperficielFormGroup, hematomeSuperficiel: HematomeSuperficielFormGroupInput): void {
    const hematomeSuperficielRawValue = { ...this.getFormDefaults(), ...hematomeSuperficiel };
    form.reset(
      {
        ...hematomeSuperficielRawValue,
        id: { value: hematomeSuperficielRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): HematomeSuperficielFormDefaults {
    return {
      id: null,
    };
  }
}
