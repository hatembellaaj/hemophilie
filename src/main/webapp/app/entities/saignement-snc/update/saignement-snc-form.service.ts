import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { ISaignementSNC, NewSaignementSNC } from '../saignement-snc.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISaignementSNC for edit and NewSaignementSNCFormGroupInput for create.
 */
type SaignementSNCFormGroupInput = ISaignementSNC | PartialWithRequiredKeyOf<NewSaignementSNC>;

type SaignementSNCFormDefaults = Pick<NewSaignementSNC, 'id'>;

type SaignementSNCFormGroupContent = {
  id: FormControl<ISaignementSNC['id'] | NewSaignementSNC['id']>;
  reponse: FormControl<ISaignementSNC['reponse']>;
  evolution: FormControl<ISaignementSNC['evolution']>;
};

export type SaignementSNCFormGroup = FormGroup<SaignementSNCFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SaignementSNCFormService {
  createSaignementSNCFormGroup(saignementSNC: SaignementSNCFormGroupInput = { id: null }): SaignementSNCFormGroup {
    const saignementSNCRawValue = {
      ...this.getFormDefaults(),
      ...saignementSNC,
    };
    return new FormGroup<SaignementSNCFormGroupContent>({
      id: new FormControl(
        { value: saignementSNCRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      reponse: new FormControl(saignementSNCRawValue.reponse),
      evolution: new FormControl(saignementSNCRawValue.evolution),
    });
  }

  getSaignementSNC(form: SaignementSNCFormGroup): ISaignementSNC | NewSaignementSNC {
    return form.getRawValue() as ISaignementSNC | NewSaignementSNC;
  }

  resetForm(form: SaignementSNCFormGroup, saignementSNC: SaignementSNCFormGroupInput): void {
    const saignementSNCRawValue = { ...this.getFormDefaults(), ...saignementSNC };
    form.reset(
      {
        ...saignementSNCRawValue,
        id: { value: saignementSNCRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): SaignementSNCFormDefaults {
    return {
      id: null,
    };
  }
}
