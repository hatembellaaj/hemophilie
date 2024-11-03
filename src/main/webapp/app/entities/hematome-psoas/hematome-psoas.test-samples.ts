import { IHematomePsoas, NewHematomePsoas } from './hematome-psoas.model';

export const sampleWithRequiredData: IHematomePsoas = {
  id: 31430,
};

export const sampleWithPartialData: IHematomePsoas = {
  id: 20990,
  type: 'SPONTANE',
};

export const sampleWithFullData: IHematomePsoas = {
  id: 1957,
  reponse: 'NON',
  type: 'PROVOQUE',
  recidive: 'OUI',
};

export const sampleWithNewData: NewHematomePsoas = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
