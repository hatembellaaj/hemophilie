import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IHemorragieVisceres } from '../hemorragie-visceres.model';
import { HemorragieVisceresService } from '../service/hemorragie-visceres.service';

const hemorragieVisceresResolve = (route: ActivatedRouteSnapshot): Observable<null | IHemorragieVisceres> => {
  const id = route.params.id;
  if (id) {
    return inject(HemorragieVisceresService)
      .find(id)
      .pipe(
        mergeMap((hemorragieVisceres: HttpResponse<IHemorragieVisceres>) => {
          if (hemorragieVisceres.body) {
            return of(hemorragieVisceres.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default hemorragieVisceresResolve;
