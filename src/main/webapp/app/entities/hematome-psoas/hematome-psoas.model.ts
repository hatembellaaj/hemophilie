import { OuiNonNP } from 'app/entities/enumerations/oui-non-np.model';
import { TypeManifestation } from 'app/entities/enumerations/type-manifestation.model';

export interface IHematomePsoas {
  id: number;
  reponse?: keyof typeof OuiNonNP | null;
  type?: keyof typeof TypeManifestation | null;
  recidive?: keyof typeof OuiNonNP | null;
}

export type NewHematomePsoas = Omit<IHematomePsoas, 'id'> & { id: null };
