import { IHemorragieVisceres, NewHemorragieVisceres } from './hemorragie-visceres.model';

export const sampleWithRequiredData: IHemorragieVisceres = {
  id: 12017,
};

export const sampleWithPartialData: IHemorragieVisceres = {
  id: 5533,
  reponse: 'OUI',
  type: 'RECTORRAGIE',
  exploration: 'NON',
  examenLesion: 'triangulaire sitôt que',
};

export const sampleWithFullData: IHemorragieVisceres = {
  id: 10571,
  reponse: 'NP',
  type: 'HEMATEMESE',
  exploration: 'OUI',
  examenLesion: 'chut membre de l’équipe',
};

export const sampleWithNewData: NewHemorragieVisceres = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
