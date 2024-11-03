import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import HemarthroseResolve from './route/hemarthrose-routing-resolve.service';

const hemarthroseRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/hemarthrose.component').then(m => m.HemarthroseComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/hemarthrose-detail.component').then(m => m.HemarthroseDetailComponent),
    resolve: {
      hemarthrose: HemarthroseResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/hemarthrose-update.component').then(m => m.HemarthroseUpdateComponent),
    resolve: {
      hemarthrose: HemarthroseResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/hemarthrose-update.component').then(m => m.HemarthroseUpdateComponent),
    resolve: {
      hemarthrose: HemarthroseResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default hemarthroseRoute;
