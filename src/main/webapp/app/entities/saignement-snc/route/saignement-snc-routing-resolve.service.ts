import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISaignementSNC } from '../saignement-snc.model';
import { SaignementSNCService } from '../service/saignement-snc.service';

const saignementSNCResolve = (route: ActivatedRouteSnapshot): Observable<null | ISaignementSNC> => {
  const id = route.params.id;
  if (id) {
    return inject(SaignementSNCService)
      .find(id)
      .pipe(
        mergeMap((saignementSNC: HttpResponse<ISaignementSNC>) => {
          if (saignementSNC.body) {
            return of(saignementSNC.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default saignementSNCResolve;
