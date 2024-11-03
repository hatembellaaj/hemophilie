import { IHematomeSuperficiel, NewHematomeSuperficiel } from './hematome-superficiel.model';

export const sampleWithRequiredData: IHematomeSuperficiel = {
  id: 30507,
};

export const sampleWithPartialData: IHematomeSuperficiel = {
  id: 924,
  reponse: 'NP',
  type: 'PROVOQUE',
  siege: 'vaincre',
};

export const sampleWithFullData: IHematomeSuperficiel = {
  id: 6641,
  reponse: 'NON',
  type: 'SPONTANE',
  siege: 'adversaire moins',
};

export const sampleWithNewData: NewHematomeSuperficiel = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
