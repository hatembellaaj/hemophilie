import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IHematomePsoas, NewHematomePsoas } from '../hematome-psoas.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IHematomePsoas for edit and NewHematomePsoasFormGroupInput for create.
 */
type HematomePsoasFormGroupInput = IHematomePsoas | PartialWithRequiredKeyOf<NewHematomePsoas>;

type HematomePsoasFormDefaults = Pick<NewHematomePsoas, 'id'>;

type HematomePsoasFormGroupContent = {
  id: FormControl<IHematomePsoas['id'] | NewHematomePsoas['id']>;
  reponse: FormControl<IHematomePsoas['reponse']>;
  type: FormControl<IHematomePsoas['type']>;
  recidive: FormControl<IHematomePsoas['recidive']>;
};

export type HematomePsoasFormGroup = FormGroup<HematomePsoasFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class HematomePsoasFormService {
  createHematomePsoasFormGroup(hematomePsoas: HematomePsoasFormGroupInput = { id: null }): HematomePsoasFormGroup {
    const hematomePsoasRawValue = {
      ...this.getFormDefaults(),
      ...hematomePsoas,
    };
    return new FormGroup<HematomePsoasFormGroupContent>({
      id: new FormControl(
        { value: hematomePsoasRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      reponse: new FormControl(hematomePsoasRawValue.reponse),
      type: new FormControl(hematomePsoasRawValue.type),
      recidive: new FormControl(hematomePsoasRawValue.recidive),
    });
  }

  getHematomePsoas(form: HematomePsoasFormGroup): IHematomePsoas | NewHematomePsoas {
    return form.getRawValue() as IHematomePsoas | NewHematomePsoas;
  }

  resetForm(form: HematomePsoasFormGroup, hematomePsoas: HematomePsoasFormGroupInput): void {
    const hematomePsoasRawValue = { ...this.getFormDefaults(), ...hematomePsoas };
    form.reset(
      {
        ...hematomePsoasRawValue,
        id: { value: hematomePsoasRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): HematomePsoasFormDefaults {
    return {
      id: null,
    };
  }
}
