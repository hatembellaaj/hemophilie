import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'Authorities' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'patient',
    data: { pageTitle: 'Patients' },
    loadChildren: () => import('./patient/patient.routes'),
  },
  {
    path: 'fiche',
    data: { pageTitle: 'Fiches' },
    loadChildren: () => import('./fiche/fiche.routes'),
  },
  {
    path: 'hemarthrose',
    data: { pageTitle: 'Hemarthroses' },
    loadChildren: () => import('./hemarthrose/hemarthrose.routes'),
  },
  {
    path: 'hematome-superficiel',
    data: { pageTitle: 'HematomeSuperficiels' },
    loadChildren: () => import('./hematome-superficiel/hematome-superficiel.routes'),
  },
  {
    path: 'hematome-psoas',
    data: { pageTitle: 'HematomePsoas' },
    loadChildren: () => import('./hematome-psoas/hematome-psoas.routes'),
  },
  {
    path: 'hemorragies-cutaneo-muqueuses',
    data: { pageTitle: 'HemorragiesCutaneoMuqueuses' },
    loadChildren: () => import('./hemorragies-cutaneo-muqueuses/hemorragies-cutaneo-muqueuses.routes'),
  },
  {
    path: 'hemorragie-visceres',
    data: { pageTitle: 'HemorragieVisceres' },
    loadChildren: () => import('./hemorragie-visceres/hemorragie-visceres.routes'),
  },
  {
    path: 'saignement-snc',
    data: { pageTitle: 'SaignementSNCS' },
    loadChildren: () => import('./saignement-snc/saignement-snc.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
