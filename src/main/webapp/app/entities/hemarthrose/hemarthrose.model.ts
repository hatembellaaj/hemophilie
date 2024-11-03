import { OuiNonNP } from 'app/entities/enumerations/oui-non-np.model';
import { TypeManifestation } from 'app/entities/enumerations/type-manifestation.model';

export interface IHemarthrose {
  id: number;
  reponse?: keyof typeof OuiNonNP | null;
  type?: keyof typeof TypeManifestation | null;
  siege?: string | null;
  frequencePerAn?: number | null;
}

export type NewHemarthrose = Omit<IHemarthrose, 'id'> & { id: null };
