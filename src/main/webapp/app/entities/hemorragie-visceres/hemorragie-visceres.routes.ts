import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import HemorragieVisceresResolve from './route/hemorragie-visceres-routing-resolve.service';

const hemorragieVisceresRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/hemorragie-visceres.component').then(m => m.HemorragieVisceresComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/hemorragie-visceres-detail.component').then(m => m.HemorragieVisceresDetailComponent),
    resolve: {
      hemorragieVisceres: HemorragieVisceresResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/hemorragie-visceres-update.component').then(m => m.HemorragieVisceresUpdateComponent),
    resolve: {
      hemorragieVisceres: HemorragieVisceresResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/hemorragie-visceres-update.component').then(m => m.HemorragieVisceresUpdateComponent),
    resolve: {
      hemorragieVisceres: HemorragieVisceresResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default hemorragieVisceresRoute;
