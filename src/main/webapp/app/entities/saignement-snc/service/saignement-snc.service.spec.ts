import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { ISaignementSNC } from '../saignement-snc.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../saignement-snc.test-samples';

import { SaignementSNCService } from './saignement-snc.service';

const requireRestSample: ISaignementSNC = {
  ...sampleWithRequiredData,
};

describe('SaignementSNC Service', () => {
  let service: SaignementSNCService;
  let httpMock: HttpTestingController;
  let expectedResult: ISaignementSNC | ISaignementSNC[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(SaignementSNCService);
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

    it('should create a SaignementSNC', () => {
      const saignementSNC = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(saignementSNC).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a SaignementSNC', () => {
      const saignementSNC = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(saignementSNC).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a SaignementSNC', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of SaignementSNC', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a SaignementSNC', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSaignementSNCToCollectionIfMissing', () => {
      it('should add a SaignementSNC to an empty array', () => {
        const saignementSNC: ISaignementSNC = sampleWithRequiredData;
        expectedResult = service.addSaignementSNCToCollectionIfMissing([], saignementSNC);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(saignementSNC);
      });

      it('should not add a SaignementSNC to an array that contains it', () => {
        const saignementSNC: ISaignementSNC = sampleWithRequiredData;
        const saignementSNCCollection: ISaignementSNC[] = [
          {
            ...saignementSNC,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSaignementSNCToCollectionIfMissing(saignementSNCCollection, saignementSNC);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a SaignementSNC to an array that doesn't contain it", () => {
        const saignementSNC: ISaignementSNC = sampleWithRequiredData;
        const saignementSNCCollection: ISaignementSNC[] = [sampleWithPartialData];
        expectedResult = service.addSaignementSNCToCollectionIfMissing(saignementSNCCollection, saignementSNC);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(saignementSNC);
      });

      it('should add only unique SaignementSNC to an array', () => {
        const saignementSNCArray: ISaignementSNC[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const saignementSNCCollection: ISaignementSNC[] = [sampleWithRequiredData];
        expectedResult = service.addSaignementSNCToCollectionIfMissing(saignementSNCCollection, ...saignementSNCArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const saignementSNC: ISaignementSNC = sampleWithRequiredData;
        const saignementSNC2: ISaignementSNC = sampleWithPartialData;
        expectedResult = service.addSaignementSNCToCollectionIfMissing([], saignementSNC, saignementSNC2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(saignementSNC);
        expect(expectedResult).toContain(saignementSNC2);
      });

      it('should accept null and undefined values', () => {
        const saignementSNC: ISaignementSNC = sampleWithRequiredData;
        expectedResult = service.addSaignementSNCToCollectionIfMissing([], null, saignementSNC, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(saignementSNC);
      });

      it('should return initial array if no SaignementSNC is added', () => {
        const saignementSNCCollection: ISaignementSNC[] = [sampleWithRequiredData];
        expectedResult = service.addSaignementSNCToCollectionIfMissing(saignementSNCCollection, undefined, null);
        expect(expectedResult).toEqual(saignementSNCCollection);
      });
    });

    describe('compareSaignementSNC', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSaignementSNC(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareSaignementSNC(entity1, entity2);
        const compareResult2 = service.compareSaignementSNC(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareSaignementSNC(entity1, entity2);
        const compareResult2 = service.compareSaignementSNC(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareSaignementSNC(entity1, entity2);
        const compareResult2 = service.compareSaignementSNC(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
