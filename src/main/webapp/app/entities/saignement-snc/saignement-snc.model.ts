import { OuiNonNP } from 'app/entities/enumerations/oui-non-np.model';
import { EvolutionSNC } from 'app/entities/enumerations/evolution-snc.model';

export interface ISaignementSNC {
  id: number;
  reponse?: keyof typeof OuiNonNP | null;
  evolution?: keyof typeof EvolutionSNC | null;
}

export type NewSaignementSNC = Omit<ISaignementSNC, 'id'> & { id: null };
