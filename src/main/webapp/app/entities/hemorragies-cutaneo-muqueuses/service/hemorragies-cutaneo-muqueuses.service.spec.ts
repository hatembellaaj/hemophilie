import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IHemorragiesCutaneoMuqueuses } from '../hemorragies-cutaneo-muqueuses.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../hemorragies-cutaneo-muqueuses.test-samples';

import { HemorragiesCutaneoMuqueusesService } from './hemorragies-cutaneo-muqueuses.service';

const requireRestSample: IHemorragiesCutaneoMuqueuses = {
  ...sampleWithRequiredData,
};

describe('HemorragiesCutaneoMuqueuses Service', () => {
  let service: HemorragiesCutaneoMuqueusesService;
  let httpMock: HttpTestingController;
  let expectedResult: IHemorragiesCutaneoMuqueuses | IHemorragiesCutaneoMuqueuses[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(HemorragiesCutaneoMuqueusesService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a HemorragiesCutaneoMuqueuses', () => {
      const hemorragiesCutaneoMuqueuses = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(hemorragiesCutaneoMuqueuses).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a HemorragiesCutaneoMuqueuses', () => {
      const hemorragiesCutaneoMuqueuses = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(hemorragiesCutaneoMuqueuses).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a HemorragiesCutaneoMuqueuses', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of HemorragiesCutaneoMuqueuses', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a HemorragiesCutaneoMuqueuses', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addHemorragiesCutaneoMuqueusesToCollectionIfMissing', () => {
      it('should add a HemorragiesCutaneoMuqueuses to an empty array', () => {
        const hemorragiesCutaneoMuqueuses: IHemorragiesCutaneoMuqueuses = sampleWithRequiredData;
        expectedResult = service.addHemorragiesCutaneoMuqueusesToCollectionIfMissing([], hemorragiesCutaneoMuqueuses);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(hemorragiesCutaneoMuqueuses);
      });

      it('should not add a HemorragiesCutaneoMuqueuses to an array that contains it', () => {
        const hemorragiesCutaneoMuqueuses: IHemorragiesCutaneoMuqueuses = sampleWithRequiredData;
        const hemorragiesCutaneoMuqueusesCollection: IHemorragiesCutaneoMuqueuses[] = [
          {
            ...hemorragiesCutaneoMuqueuses,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addHemorragiesCutaneoMuqueusesToCollectionIfMissing(
          hemorragiesCutaneoMuqueusesCollection,
          hemorragiesCutaneoMuqueuses,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a HemorragiesCutaneoMuqueuses to an array that doesn't contain it", () => {
        const hemorragiesCutaneoMuqueuses: IHemorragiesCutaneoMuqueuses = sampleWithRequiredData;
        const hemorragiesCutaneoMuqueusesCollection: IHemorragiesCutaneoMuqueuses[] = [sampleWithPartialData];
        expectedResult = service.addHemorragiesCutaneoMuqueusesToCollectionIfMissing(
          hemorragiesCutaneoMuqueusesCollection,
          hemorragiesCutaneoMuqueuses,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(hemorragiesCutaneoMuqueuses);
      });

      it('should add only unique HemorragiesCutaneoMuqueuses to an array', () => {
        const hemorragiesCutaneoMuqueusesArray: IHemorragiesCutaneoMuqueuses[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const hemorragiesCutaneoMuqueusesCollection: IHemorragiesCutaneoMuqueuses[] = [sampleWithRequiredData];
        expectedResult = service.addHemorragiesCutaneoMuqueusesToCollectionIfMissing(
          hemorragiesCutaneoMuqueusesCollection,
          ...hemorragiesCutaneoMuqueusesArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const hemorragiesCutaneoMuqueuses: IHemorragiesCutaneoMuqueuses = sampleWithRequiredData;
        const hemorragiesCutaneoMuqueuses2: IHemorragiesCutaneoMuqueuses = sampleWithPartialData;
        expectedResult = service.addHemorragiesCutaneoMuqueusesToCollectionIfMissing(
          [],
          hemorragiesCutaneoMuqueuses,
          hemorragiesCutaneoMuqueuses2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(hemorragiesCutaneoMuqueuses);
        expect(expectedResult).toContain(hemorragiesCutaneoMuqueuses2);
      });

      it('should accept null and undefined values', () => {
        const hemorragiesCutaneoMuqueuses: IHemorragiesCutaneoMuqueuses = sampleWithRequiredData;
        expectedResult = service.addHemorragiesCutaneoMuqueusesToCollectionIfMissing([], null, hemorragiesCutaneoMuqueuses, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(hemorragiesCutaneoMuqueuses);
      });

      it('should return initial array if no HemorragiesCutaneoMuqueuses is added', () => {
        const hemorragiesCutaneoMuqueusesCollection: IHemorragiesCutaneoMuqueuses[] = [sampleWithRequiredData];
        expectedResult = service.addHemorragiesCutaneoMuqueusesToCollectionIfMissing(
          hemorragiesCutaneoMuqueusesCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(hemorragiesCutaneoMuqueusesCollection);
      });
    });

    describe('compareHemorragiesCutaneoMuqueuses', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareHemorragiesCutaneoMuqueuses(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareHemorragiesCutaneoMuqueuses(entity1, entity2);
        const compareResult2 = service.compareHemorragiesCutaneoMuqueuses(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareHemorragiesCutaneoMuqueuses(entity1, entity2);
        const compareResult2 = service.compareHemorragiesCutaneoMuqueuses(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareHemorragiesCutaneoMuqueuses(entity1, entity2);
        const compareResult2 = service.compareHemorragiesCutaneoMuqueuses(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
