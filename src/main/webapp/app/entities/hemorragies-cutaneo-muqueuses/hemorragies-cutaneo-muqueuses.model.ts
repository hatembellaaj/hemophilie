import { OuiNonNP } from 'app/entities/enumerations/oui-non-np.model';
import { TypeHemorragie } from 'app/entities/enumerations/type-hemorragie.model';

export interface IHemorragiesCutaneoMuqueuses {
  id: number;
  reponse?: keyof typeof OuiNonNP | null;
  type?: keyof typeof TypeHemorragie | null;
  frequencePerAn?: number | null;
}

export type NewHemorragiesCutaneoMuqueuses = Omit<IHemorragiesCutaneoMuqueuses, 'id'> & { id: null };
