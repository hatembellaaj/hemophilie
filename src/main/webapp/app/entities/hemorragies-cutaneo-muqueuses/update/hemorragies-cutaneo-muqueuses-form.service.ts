import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IHemorragiesCutaneoMuqueuses, NewHemorragiesCutaneoMuqueuses } from '../hemorragies-cutaneo-muqueuses.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IHemorragiesCutaneoMuqueuses for edit and NewHemorragiesCutaneoMuqueusesFormGroupInput for create.
 */
type HemorragiesCutaneoMuqueusesFormGroupInput = IHemorragiesCutaneoMuqueuses | PartialWithRequiredKeyOf<NewHemorragiesCutaneoMuqueuses>;

type HemorragiesCutaneoMuqueusesFormDefaults = Pick<NewHemorragiesCutaneoMuqueuses, 'id'>;

type HemorragiesCutaneoMuqueusesFormGroupContent = {
  id: FormControl<IHemorragiesCutaneoMuqueuses['id'] | NewHemorragiesCutaneoMuqueuses['id']>;
  reponse: FormControl<IHemorragiesCutaneoMuqueuses['reponse']>;
  type: FormControl<IHemorragiesCutaneoMuqueuses['type']>;
  frequencePerAn: FormControl<IHemorragiesCutaneoMuqueuses['frequencePerAn']>;
};

export type HemorragiesCutaneoMuqueusesFormGroup = FormGroup<HemorragiesCutaneoMuqueusesFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class HemorragiesCutaneoMuqueusesFormService {
  createHemorragiesCutaneoMuqueusesFormGroup(
    hemorragiesCutaneoMuqueuses: HemorragiesCutaneoMuqueusesFormGroupInput = { id: null },
  ): HemorragiesCutaneoMuqueusesFormGroup {
    const hemorragiesCutaneoMuqueusesRawValue = {
      ...this.getFormDefaults(),
      ...hemorragiesCutaneoMuqueuses,
    };
    return new FormGroup<HemorragiesCutaneoMuqueusesFormGroupContent>({
      id: new FormControl(
        { value: hemorragiesCutaneoMuqueusesRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      reponse: new FormControl(hemorragiesCutaneoMuqueusesRawValue.reponse),
      type: new FormControl(hemorragiesCutaneoMuqueusesRawValue.type),
      frequencePerAn: new FormControl(hemorragiesCutaneoMuqueusesRawValue.frequencePerAn),
    });
  }

  getHemorragiesCutaneoMuqueuses(
    form: HemorragiesCutaneoMuqueusesFormGroup,
  ): IHemorragiesCutaneoMuqueuses | NewHemorragiesCutaneoMuqueuses {
    return form.getRawValue() as IHemorragiesCutaneoMuqueuses | NewHemorragiesCutaneoMuqueuses;
  }

  resetForm(form: HemorragiesCutaneoMuqueusesFormGroup, hemorragiesCutaneoMuqueuses: HemorragiesCutaneoMuqueusesFormGroupInput): void {
    const hemorragiesCutaneoMuqueusesRawValue = { ...this.getFormDefaults(), ...hemorragiesCutaneoMuqueuses };
    form.reset(
      {
        ...hemorragiesCutaneoMuqueusesRawValue,
        id: { value: hemorragiesCutaneoMuqueusesRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): HemorragiesCutaneoMuqueusesFormDefaults {
    return {
      id: null,
    };
  }
}
