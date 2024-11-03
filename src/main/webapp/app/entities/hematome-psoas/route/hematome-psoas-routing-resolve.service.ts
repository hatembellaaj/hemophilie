import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IHematomePsoas } from '../hematome-psoas.model';
import { HematomePsoasService } from '../service/hematome-psoas.service';

const hematomePsoasResolve = (route: ActivatedRouteSnapshot): Observable<null | IHematomePsoas> => {
  const id = route.params.id;
  if (id) {
    return inject(HematomePsoasService)
      .find(id)
      .pipe(
        mergeMap((hematomePsoas: HttpResponse<IHematomePsoas>) => {
          if (hematomePsoas.body) {
            return of(hematomePsoas.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default hematomePsoasResolve;
