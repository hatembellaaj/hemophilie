import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IHemarthrose } from '../hemarthrose.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../hemarthrose.test-samples';

import { HemarthroseService } from './hemarthrose.service';

const requireRestSample: IHemarthrose = {
  ...sampleWithRequiredData,
};

describe('Hemarthrose Service', () => {
  let service: HemarthroseService;
  let httpMock: HttpTestingController;
  let expectedResult: IHemarthrose | IHemarthrose[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(HemarthroseService);
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

    it('should create a Hemarthrose', () => {
      const hemarthrose = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(hemarthrose).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Hemarthrose', () => {
      const hemarthrose = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(hemarthrose).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Hemarthrose', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Hemarthrose', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Hemarthrose', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addHemarthroseToCollectionIfMissing', () => {
      it('should add a Hemarthrose to an empty array', () => {
        const hemarthrose: IHemarthrose = sampleWithRequiredData;
        expectedResult = service.addHemarthroseToCollectionIfMissing([], hemarthrose);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(hemarthrose);
      });

      it('should not add a Hemarthrose to an array that contains it', () => {
        const hemarthrose: IHemarthrose = sampleWithRequiredData;
        const hemarthroseCollection: IHemarthrose[] = [
          {
            ...hemarthrose,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addHemarthroseToCollectionIfMissing(hemarthroseCollection, hemarthrose);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Hemarthrose to an array that doesn't contain it", () => {
        const hemarthrose: IHemarthrose = sampleWithRequiredData;
        const hemarthroseCollection: IHemarthrose[] = [sampleWithPartialData];
        expectedResult = service.addHemarthroseToCollectionIfMissing(hemarthroseCollection, hemarthrose);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(hemarthrose);
      });

      it('should add only unique Hemarthrose to an array', () => {
        const hemarthroseArray: IHemarthrose[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const hemarthroseCollection: IHemarthrose[] = [sampleWithRequiredData];
        expectedResult = service.addHemarthroseToCollectionIfMissing(hemarthroseCollection, ...hemarthroseArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const hemarthrose: IHemarthrose = sampleWithRequiredData;
        const hemarthrose2: IHemarthrose = sampleWithPartialData;
        expectedResult = service.addHemarthroseToCollectionIfMissing([], hemarthrose, hemarthrose2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(hemarthrose);
        expect(expectedResult).toContain(hemarthrose2);
      });

      it('should accept null and undefined values', () => {
        const hemarthrose: IHemarthrose = sampleWithRequiredData;
        expectedResult = service.addHemarthroseToCollectionIfMissing([], null, hemarthrose, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(hemarthrose);
      });

      it('should return initial array if no Hemarthrose is added', () => {
        const hemarthroseCollection: IHemarthrose[] = [sampleWithRequiredData];
        expectedResult = service.addHemarthroseToCollectionIfMissing(hemarthroseCollection, undefined, null);
        expect(expectedResult).toEqual(hemarthroseCollection);
      });
    });

    describe('compareHemarthrose', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareHemarthrose(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareHemarthrose(entity1, entity2);
        const compareResult2 = service.compareHemarthrose(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareHemarthrose(entity1, entity2);
        const compareResult2 = service.compareHemarthrose(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareHemarthrose(entity1, entity2);
        const compareResult2 = service.compareHemarthrose(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
