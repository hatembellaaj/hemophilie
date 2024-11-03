import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IHemarthrose, NewHemarthrose } from '../hemarthrose.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IHemarthrose for edit and NewHemarthroseFormGroupInput for create.
 */
type HemarthroseFormGroupInput = IHemarthrose | PartialWithRequiredKeyOf<NewHemarthrose>;

type HemarthroseFormDefaults = Pick<NewHemarthrose, 'id'>;

type HemarthroseFormGroupContent = {
  id: FormControl<IHemarthrose['id'] | NewHemarthrose['id']>;
  reponse: FormControl<IHemarthrose['reponse']>;
  type: FormControl<IHemarthrose['type']>;
  siege: FormControl<IHemarthrose['siege']>;
  frequencePerAn: FormControl<IHemarthrose['frequencePerAn']>;
};

export type HemarthroseFormGroup = FormGroup<HemarthroseFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class HemarthroseFormService {
  createHemarthroseFormGroup(hemarthrose: HemarthroseFormGroupInput = { id: null }): HemarthroseFormGroup {
    const hemarthroseRawValue = {
      ...this.getFormDefaults(),
      ...hemarthrose,
    };
    return new FormGroup<HemarthroseFormGroupContent>({
      id: new FormControl(
        { value: hemarthroseRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      reponse: new FormControl(hemarthroseRawValue.reponse),
      type: new FormControl(hemarthroseRawValue.type, {
        validators: [Validators.required],
      }),
      siege: new FormControl(hemarthroseRawValue.siege, {
        validators: [Validators.required],
      }),
      frequencePerAn: new FormControl(hemarthroseRawValue.frequencePerAn),
    });
  }

  getHemarthrose(form: HemarthroseFormGroup): IHemarthrose | NewHemarthrose {
    return form.getRawValue() as IHemarthrose | NewHemarthrose;
  }

  resetForm(form: HemarthroseFormGroup, hemarthrose: HemarthroseFormGroupInput): void {
    const hemarthroseRawValue = { ...this.getFormDefaults(), ...hemarthrose };
    form.reset(
      {
        ...hemarthroseRawValue,
        id: { value: hemarthroseRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): HemarthroseFormDefaults {
    return {
      id: null,
    };
  }
}
