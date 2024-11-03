import { IHemorragiesCutaneoMuqueuses, NewHemorragiesCutaneoMuqueuses } from './hemorragies-cutaneo-muqueuses.model';

export const sampleWithRequiredData: IHemorragiesCutaneoMuqueuses = {
  id: 10137,
};

export const sampleWithPartialData: IHemorragiesCutaneoMuqueuses = {
  id: 20842,
  reponse: 'NP',
  frequencePerAn: 3601,
};

export const sampleWithFullData: IHemorragiesCutaneoMuqueuses = {
  id: 13761,
  reponse: 'NON',
  type: 'CHUTE_DENTAIRE',
  frequencePerAn: 16909,
};

export const sampleWithNewData: NewHemorragiesCutaneoMuqueuses = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
