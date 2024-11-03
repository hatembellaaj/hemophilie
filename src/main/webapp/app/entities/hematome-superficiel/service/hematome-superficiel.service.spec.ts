import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IHematomeSuperficiel } from '../hematome-superficiel.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../hematome-superficiel.test-samples';

import { HematomeSuperficielService } from './hematome-superficiel.service';

const requireRestSample: IHematomeSuperficiel = {
  ...sampleWithRequiredData,
};

describe('HematomeSuperficiel Service', () => {
  let service: HematomeSuperficielService;
  let httpMock: HttpTestingController;
  let expectedResult: IHematomeSuperficiel | IHematomeSuperficiel[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(HematomeSuperficielService);
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

    it('should create a HematomeSuperficiel', () => {
      const hematomeSuperficiel = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(hematomeSuperficiel).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a HematomeSuperficiel', () => {
      const hematomeSuperficiel = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(hematomeSuperficiel).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a HematomeSuperficiel', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of HematomeSuperficiel', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a HematomeSuperficiel', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addHematomeSuperficielToCollectionIfMissing', () => {
      it('should add a HematomeSuperficiel to an empty array', () => {
        const hematomeSuperficiel: IHematomeSuperficiel = sampleWithRequiredData;
        expectedResult = service.addHematomeSuperficielToCollectionIfMissing([], hematomeSuperficiel);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(hematomeSuperficiel);
      });

      it('should not add a HematomeSuperficiel to an array that contains it', () => {
        const hematomeSuperficiel: IHematomeSuperficiel = sampleWithRequiredData;
        const hematomeSuperficielCollection: IHematomeSuperficiel[] = [
          {
            ...hematomeSuperficiel,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addHematomeSuperficielToCollectionIfMissing(hematomeSuperficielCollection, hematomeSuperficiel);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a HematomeSuperficiel to an array that doesn't contain it", () => {
        const hematomeSuperficiel: IHematomeSuperficiel = sampleWithRequiredData;
        const hematomeSuperficielCollection: IHematomeSuperficiel[] = [sampleWithPartialData];
        expectedResult = service.addHematomeSuperficielToCollectionIfMissing(hematomeSuperficielCollection, hematomeSuperficiel);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(hematomeSuperficiel);
      });

      it('should add only unique HematomeSuperficiel to an array', () => {
        const hematomeSuperficielArray: IHematomeSuperficiel[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const hematomeSuperficielCollection: IHematomeSuperficiel[] = [sampleWithRequiredData];
        expectedResult = service.addHematomeSuperficielToCollectionIfMissing(hematomeSuperficielCollection, ...hematomeSuperficielArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const hematomeSuperficiel: IHematomeSuperficiel = sampleWithRequiredData;
        const hematomeSuperficiel2: IHematomeSuperficiel = sampleWithPartialData;
        expectedResult = service.addHematomeSuperficielToCollectionIfMissing([], hematomeSuperficiel, hematomeSuperficiel2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(hematomeSuperficiel);
        expect(expectedResult).toContain(hematomeSuperficiel2);
      });

      it('should accept null and undefined values', () => {
        const hematomeSuperficiel: IHematomeSuperficiel = sampleWithRequiredData;
        expectedResult = service.addHematomeSuperficielToCollectionIfMissing([], null, hematomeSuperficiel, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(hematomeSuperficiel);
      });

      it('should return initial array if no HematomeSuperficiel is added', () => {
        const hematomeSuperficielCollection: IHematomeSuperficiel[] = [sampleWithRequiredData];
        expectedResult = service.addHematomeSuperficielToCollectionIfMissing(hematomeSuperficielCollection, undefined, null);
        expect(expectedResult).toEqual(hematomeSuperficielCollection);
      });
    });

    describe('compareHematomeSuperficiel', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareHematomeSuperficiel(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareHematomeSuperficiel(entity1, entity2);
        const compareResult2 = service.compareHematomeSuperficiel(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareHematomeSuperficiel(entity1, entity2);
        const compareResult2 = service.compareHematomeSuperficiel(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareHematomeSuperficiel(entity1, entity2);
        const compareResult2 = service.compareHematomeSuperficiel(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
