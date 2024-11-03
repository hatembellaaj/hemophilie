import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import HemorragiesCutaneoMuqueusesResolve from './route/hemorragies-cutaneo-muqueuses-routing-resolve.service';

const hemorragiesCutaneoMuqueusesRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/hemorragies-cutaneo-muqueuses.component').then(m => m.HemorragiesCutaneoMuqueusesComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () =>
      import('./detail/hemorragies-cutaneo-muqueuses-detail.component').then(m => m.HemorragiesCutaneoMuqueusesDetailComponent),
    resolve: {
      hemorragiesCutaneoMuqueuses: HemorragiesCutaneoMuqueusesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () =>
      import('./update/hemorragies-cutaneo-muqueuses-update.component').then(m => m.HemorragiesCutaneoMuqueusesUpdateComponent),
    resolve: {
      hemorragiesCutaneoMuqueuses: HemorragiesCutaneoMuqueusesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () =>
      import('./update/hemorragies-cutaneo-muqueuses-update.component').then(m => m.HemorragiesCutaneoMuqueusesUpdateComponent),
    resolve: {
      hemorragiesCutaneoMuqueuses: HemorragiesCutaneoMuqueusesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default hemorragiesCutaneoMuqueusesRoute;
