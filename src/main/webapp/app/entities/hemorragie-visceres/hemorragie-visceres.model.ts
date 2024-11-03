import { OuiNonNP } from 'app/entities/enumerations/oui-non-np.model';
import { TypeHemorragieVisceres } from 'app/entities/enumerations/type-hemorragie-visceres.model';

export interface IHemorragieVisceres {
  id: number;
  reponse?: keyof typeof OuiNonNP | null;
  type?: keyof typeof TypeHemorragieVisceres | null;
  exploration?: keyof typeof OuiNonNP | null;
  examenLesion?: string | null;
}

export type NewHemorragieVisceres = Omit<IHemorragieVisceres, 'id'> & { id: null };
