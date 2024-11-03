import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import HematomePsoasResolve from './route/hematome-psoas-routing-resolve.service';

const hematomePsoasRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/hematome-psoas.component').then(m => m.HematomePsoasComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/hematome-psoas-detail.component').then(m => m.HematomePsoasDetailComponent),
    resolve: {
      hematomePsoas: HematomePsoasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/hematome-psoas-update.component').then(m => m.HematomePsoasUpdateComponent),
    resolve: {
      hematomePsoas: HematomePsoasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/hematome-psoas-update.component').then(m => m.HematomePsoasUpdateComponent),
    resolve: {
      hematomePsoas: HematomePsoasResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default hematomePsoasRoute;
