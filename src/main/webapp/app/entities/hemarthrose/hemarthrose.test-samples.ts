import { IHemarthrose, NewHemarthrose } from './hemarthrose.model';

export const sampleWithRequiredData: IHemarthrose = {
  id: 27935,
  type: 'PROVOQUE',
  siege: 'drelin oups pleurer',
};

export const sampleWithPartialData: IHemarthrose = {
  id: 29296,
  type: 'PROVOQUE',
  siege: 'vorace',
  frequencePerAn: 8238,
};

export const sampleWithFullData: IHemarthrose = {
  id: 20413,
  reponse: 'NON',
  type: 'PROVOQUE',
  siege: 'prestataire de services près pourpre',
  frequencePerAn: 32630,
};

export const sampleWithNewData: NewHemarthrose = {
  type: 'PROVOQUE',
  siege: 'administration par',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
