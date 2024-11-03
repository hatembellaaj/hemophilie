import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IHemarthrose } from '../hemarthrose.model';
import { HemarthroseService } from '../service/hemarthrose.service';

const hemarthroseResolve = (route: ActivatedRouteSnapshot): Observable<null | IHemarthrose> => {
  const id = route.params.id;
  if (id) {
    return inject(HemarthroseService)
      .find(id)
      .pipe(
        mergeMap((hemarthrose: HttpResponse<IHemarthrose>) => {
          if (hemarthrose.body) {
            return of(hemarthrose.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default hemarthroseResolve;
