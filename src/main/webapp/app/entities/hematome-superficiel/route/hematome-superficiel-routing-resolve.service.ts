import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IHematomeSuperficiel } from '../hematome-superficiel.model';
import { HematomeSuperficielService } from '../service/hematome-superficiel.service';

const hematomeSuperficielResolve = (route: ActivatedRouteSnapshot): Observable<null | IHematomeSuperficiel> => {
  const id = route.params.id;
  if (id) {
    return inject(HematomeSuperficielService)
      .find(id)
      .pipe(
        mergeMap((hematomeSuperficiel: HttpResponse<IHematomeSuperficiel>) => {
          if (hematomeSuperficiel.body) {
            return of(hematomeSuperficiel.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default hematomeSuperficielResolve;
