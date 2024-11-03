import { IUser } from './user.model';

export const sampleWithRequiredData: IUser = {
  id: 20575,
  login: 'lXbv@',
};

export const sampleWithPartialData: IUser = {
  id: 25456,
  login: 'fhNI',
};

export const sampleWithFullData: IUser = {
  id: 4329,
  login: 'OgzHv',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
