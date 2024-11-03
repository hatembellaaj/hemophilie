import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: 'da4fb658-378c-4879-9112-a6f5beab01e1',
};

export const sampleWithPartialData: IAuthority = {
  name: 'abcd588d-bc98-45ec-874d-6e0ad2e5941f',
};

export const sampleWithFullData: IAuthority = {
  name: '8f1393a9-a6c5-45d0-acde-2f318ce0070a',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
