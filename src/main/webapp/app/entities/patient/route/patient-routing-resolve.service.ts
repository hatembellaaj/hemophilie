import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IPatient } from '../patient.model';
import { PatientService } from '../service/patient.service';

const patientResolve = (route: ActivatedRouteSnapshot): Observable<null | IPatient> => {
  const id = route.params.id;
  if (id) {
    return inject(PatientService)
      .find(id)
      .pipe(
        mergeMap((patient: HttpResponse<IPatient>) => {
          if (patient.body) {
            return of(patient.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default patientResolve;
