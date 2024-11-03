import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IHemorragiesCutaneoMuqueuses } from '../hemorragies-cutaneo-muqueuses.model';
import { HemorragiesCutaneoMuqueusesService } from '../service/hemorragies-cutaneo-muqueuses.service';

import hemorragiesCutaneoMuqueusesResolve from './hemorragies-cutaneo-muqueuses-routing-resolve.service';

describe('HemorragiesCutaneoMuqueuses routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: HemorragiesCutaneoMuqueusesService;
  let resultHemorragiesCutaneoMuqueuses: IHemorragiesCutaneoMuqueuses | null | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(),
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    service = TestBed.inject(HemorragiesCutaneoMuqueusesService);
    resultHemorragiesCutaneoMuqueuses = undefined;
  });

  describe('resolve', () => {
    it('should return IHemorragiesCutaneoMuqueuses returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        hemorragiesCutaneoMuqueusesResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultHemorragiesCutaneoMuqueuses = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultHemorragiesCutaneoMuqueuses).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        hemorragiesCutaneoMuqueusesResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultHemorragiesCutaneoMuqueuses = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultHemorragiesCutaneoMuqueuses).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IHemorragiesCutaneoMuqueuses>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        hemorragiesCutaneoMuqueusesResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultHemorragiesCutaneoMuqueuses = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultHemorragiesCutaneoMuqueuses).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
