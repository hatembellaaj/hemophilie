import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import SaignementSNCResolve from './route/saignement-snc-routing-resolve.service';

const saignementSNCRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/saignement-snc.component').then(m => m.SaignementSNCComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/saignement-snc-detail.component').then(m => m.SaignementSNCDetailComponent),
    resolve: {
      saignementSNC: SaignementSNCResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/saignement-snc-update.component').then(m => m.SaignementSNCUpdateComponent),
    resolve: {
      saignementSNC: SaignementSNCResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/saignement-snc-update.component').then(m => m.SaignementSNCUpdateComponent),
    resolve: {
      saignementSNC: SaignementSNCResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default saignementSNCRoute;
