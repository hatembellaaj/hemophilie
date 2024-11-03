import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IHemorragiesCutaneoMuqueuses } from '../hemorragies-cutaneo-muqueuses.model';
import { HemorragiesCutaneoMuqueusesService } from '../service/hemorragies-cutaneo-muqueuses.service';

const hemorragiesCutaneoMuqueusesResolve = (route: ActivatedRouteSnapshot): Observable<null | IHemorragiesCutaneoMuqueuses> => {
  const id = route.params.id;
  if (id) {
    return inject(HemorragiesCutaneoMuqueusesService)
      .find(id)
      .pipe(
        mergeMap((hemorragiesCutaneoMuqueuses: HttpResponse<IHemorragiesCutaneoMuqueuses>) => {
          if (hemorragiesCutaneoMuqueuses.body) {
            return of(hemorragiesCutaneoMuqueuses.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default hemorragiesCutaneoMuqueusesResolve;
