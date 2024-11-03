import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IHematomePsoas } from '../hematome-psoas.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../hematome-psoas.test-samples';

import { HematomePsoasService } from './hematome-psoas.service';

const requireRestSample: IHematomePsoas = {
  ...sampleWithRequiredData,
};

describe('HematomePsoas Service', () => {
  let service: HematomePsoasService;
  let httpMock: HttpTestingController;
  let expectedResult: IHematomePsoas | IHematomePsoas[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(HematomePsoasService);
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

    it('should create a HematomePsoas', () => {
      const hematomePsoas = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(hematomePsoas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a HematomePsoas', () => {
      const hematomePsoas = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(hematomePsoas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a HematomePsoas', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of HematomePsoas', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a HematomePsoas', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addHematomePsoasToCollectionIfMissing', () => {
      it('should add a HematomePsoas to an empty array', () => {
        const hematomePsoas: IHematomePsoas = sampleWithRequiredData;
        expectedResult = service.addHematomePsoasToCollectionIfMissing([], hematomePsoas);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(hematomePsoas);
      });

      it('should not add a HematomePsoas to an array that contains it', () => {
        const hematomePsoas: IHematomePsoas = sampleWithRequiredData;
        const hematomePsoasCollection: IHematomePsoas[] = [
          {
            ...hematomePsoas,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addHematomePsoasToCollectionIfMissing(hematomePsoasCollection, hematomePsoas);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a HematomePsoas to an array that doesn't contain it", () => {
        const hematomePsoas: IHematomePsoas = sampleWithRequiredData;
        const hematomePsoasCollection: IHematomePsoas[] = [sampleWithPartialData];
        expectedResult = service.addHematomePsoasToCollectionIfMissing(hematomePsoasCollection, hematomePsoas);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(hematomePsoas);
      });

      it('should add only unique HematomePsoas to an array', () => {
        const hematomePsoasArray: IHematomePsoas[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const hematomePsoasCollection: IHematomePsoas[] = [sampleWithRequiredData];
        expectedResult = service.addHematomePsoasToCollectionIfMissing(hematomePsoasCollection, ...hematomePsoasArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const hematomePsoas: IHematomePsoas = sampleWithRequiredData;
        const hematomePsoas2: IHematomePsoas = sampleWithPartialData;
        expectedResult = service.addHematomePsoasToCollectionIfMissing([], hematomePsoas, hematomePsoas2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(hematomePsoas);
        expect(expectedResult).toContain(hematomePsoas2);
      });

      it('should accept null and undefined values', () => {
        const hematomePsoas: IHematomePsoas = sampleWithRequiredData;
        expectedResult = service.addHematomePsoasToCollectionIfMissing([], null, hematomePsoas, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(hematomePsoas);
      });

      it('should return initial array if no HematomePsoas is added', () => {
        const hematomePsoasCollection: IHematomePsoas[] = [sampleWithRequiredData];
        expectedResult = service.addHematomePsoasToCollectionIfMissing(hematomePsoasCollection, undefined, null);
        expect(expectedResult).toEqual(hematomePsoasCollection);
      });
    });

    describe('compareHematomePsoas', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareHematomePsoas(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareHematomePsoas(entity1, entity2);
        const compareResult2 = service.compareHematomePsoas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareHematomePsoas(entity1, entity2);
        const compareResult2 = service.compareHematomePsoas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareHematomePsoas(entity1, entity2);
        const compareResult2 = service.compareHematomePsoas(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
