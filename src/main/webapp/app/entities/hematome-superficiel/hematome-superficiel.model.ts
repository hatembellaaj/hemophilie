import { OuiNonNP } from 'app/entities/enumerations/oui-non-np.model';
import { TypeManifestation } from 'app/entities/enumerations/type-manifestation.model';

export interface IHematomeSuperficiel {
  id: number;
  reponse?: keyof typeof OuiNonNP | null;
  type?: keyof typeof TypeManifestation | null;
  siege?: string | null;
}

export type NewHematomeSuperficiel = Omit<IHematomeSuperficiel, 'id'> & { id: null };
