import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IHemorragieVisceres, NewHemorragieVisceres } from '../hemorragie-visceres.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IHemorragieVisceres for edit and NewHemorragieVisceresFormGroupInput for create.
 */
type HemorragieVisceresFormGroupInput = IHemorragieVisceres | PartialWithRequiredKeyOf<NewHemorragieVisceres>;

type HemorragieVisceresFormDefaults = Pick<NewHemorragieVisceres, 'id'>;

type HemorragieVisceresFormGroupContent = {
  id: FormControl<IHemorragieVisceres['id'] | NewHemorragieVisceres['id']>;
  reponse: FormControl<IHemorragieVisceres['reponse']>;
  type: FormControl<IHemorragieVisceres['type']>;
  exploration: FormControl<IHemorragieVisceres['exploration']>;
  examenLesion: FormControl<IHemorragieVisceres['examenLesion']>;
};

export type HemorragieVisceresFormGroup = FormGroup<HemorragieVisceresFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class HemorragieVisceresFormService {
  createHemorragieVisceresFormGroup(hemorragieVisceres: HemorragieVisceresFormGroupInput = { id: null }): HemorragieVisceresFormGroup {
    const hemorragieVisceresRawValue = {
      ...this.getFormDefaults(),
      ...hemorragieVisceres,
    };
    return new FormGroup<HemorragieVisceresFormGroupContent>({
      id: new FormControl(
        { value: hemorragieVisceresRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      reponse: new FormControl(hemorragieVisceresRawValue.reponse),
      type: new FormControl(hemorragieVisceresRawValue.type),
      exploration: new FormControl(hemorragieVisceresRawValue.exploration),
      examenLesion: new FormControl(hemorragieVisceresRawValue.examenLesion),
    });
  }

  getHemorragieVisceres(form: HemorragieVisceresFormGroup): IHemorragieVisceres | NewHemorragieVisceres {
    return form.getRawValue() as IHemorragieVisceres | NewHemorragieVisceres;
  }

  resetForm(form: HemorragieVisceresFormGroup, hemorragieVisceres: HemorragieVisceresFormGroupInput): void {
    const hemorragieVisceresRawValue = { ...this.getFormDefaults(), ...hemorragieVisceres };
    form.reset(
      {
        ...hemorragieVisceresRawValue,
        id: { value: hemorragieVisceresRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): HemorragieVisceresFormDefaults {
    return {
      id: null,
    };
  }
}
