import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import HematomeSuperficielResolve from './route/hematome-superficiel-routing-resolve.service';

const hematomeSuperficielRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/hematome-superficiel.component').then(m => m.HematomeSuperficielComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/hematome-superficiel-detail.component').then(m => m.HematomeSuperficielDetailComponent),
    resolve: {
      hematomeSuperficiel: HematomeSuperficielResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/hematome-superficiel-update.component').then(m => m.HematomeSuperficielUpdateComponent),
    resolve: {
      hematomeSuperficiel: HematomeSuperficielResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/hematome-superficiel-update.component').then(m => m.HematomeSuperficielUpdateComponent),
    resolve: {
      hematomeSuperficiel: HematomeSuperficielResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default hematomeSuperficielRoute;
