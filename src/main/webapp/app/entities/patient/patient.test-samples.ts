import dayjs from 'dayjs/esm';

import { IPatient, NewPatient } from './patient.model';

export const sampleWithRequiredData: IPatient = {
  id: 23788,
  prenom: 'guide insister personnel professionnel',
  nom: 'ah',
  sexe: 'FEMININ',
  etatCivil: 'NP',
  couvertureSociale: 'CNAM',
};

export const sampleWithPartialData: IPatient = {
  id: 10498,
  prenom: 'avant que',
  nom: 'tant',
  sexe: 'FEMININ',
  ageActuel: 10681,
  origine: 'KEBILI',
  autreOrigine: 'au-dedans de drôlement brusque',
  etatCivil: 'MARIE',
  couvertureSociale: 'NP',
  prenomPere: 'd’autant que de manière à',
  professionPere: 'savoir',
  nomPrenomMere: 'économiser référer grrr',
};

export const sampleWithFullData: IPatient = {
  id: 20973,
  prenom: 'de par dès',
  nom: 'large secours triathlète',
  nomJeuneFille: 'afin de',
  profession: 'coac coac',
  sexe: 'FEMININ',
  dateNaissance: dayjs('2024-11-02'),
  ageActuel: 5954,
  origine: 'SOUSSE',
  autreOrigine: 'parlementaire avant que',
  adresse: 'derrière oh',
  telephone: '0456045779',
  etatCivil: 'DIVORCE',
  couvertureSociale: 'INDIGENT',
  prenomPere: 'agréable désagréable',
  professionPere: 'de sorte que areu areu',
  nomPrenomMere: 'partenaire',
  professionMere: 'gratis',
};

export const sampleWithNewData: NewPatient = {
  prenom: 'à même emmener',
  nom: 'puisque fade ah',
  sexe: 'MASCULIN',
  etatCivil: 'DIVORCE',
  couvertureSociale: 'NP',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
