import { ISaignementSNC, NewSaignementSNC } from './saignement-snc.model';

export const sampleWithRequiredData: ISaignementSNC = {
  id: 3378,
};

export const sampleWithPartialData: ISaignementSNC = {
  id: 24107,
};

export const sampleWithFullData: ISaignementSNC = {
  id: 2026,
  reponse: 'NON',
  evolution: 'REGRESSION',
};

export const sampleWithNewData: NewSaignementSNC = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
