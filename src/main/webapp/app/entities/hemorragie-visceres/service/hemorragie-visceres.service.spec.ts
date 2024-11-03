import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IHemorragieVisceres } from '../hemorragie-visceres.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../hemorragie-visceres.test-samples';

import { HemorragieVisceresService } from './hemorragie-visceres.service';

const requireRestSample: IHemorragieVisceres = {
  ...sampleWithRequiredData,
};

describe('HemorragieVisceres Service', () => {
  let service: HemorragieVisceresService;
  let httpMock: HttpTestingController;
  let expectedResult: IHemorragieVisceres | IHemorragieVisceres[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(HemorragieVisceresService);
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

    it('should create a HemorragieVisceres', () => {
      const hemorragieVisceres = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(hemorragieVisceres).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a HemorragieVisceres', () => {
      const hemorragieVisceres = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(hemorragieVisceres).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a HemorragieVisceres', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of HemorragieVisceres', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a HemorragieVisceres', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addHemorragieVisceresToCollectionIfMissing', () => {
      it('should add a HemorragieVisceres to an empty array', () => {
        const hemorragieVisceres: IHemorragieVisceres = sampleWithRequiredData;
        expectedResult = service.addHemorragieVisceresToCollectionIfMissing([], hemorragieVisceres);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(hemorragieVisceres);
      });

      it('should not add a HemorragieVisceres to an array that contains it', () => {
        const hemorragieVisceres: IHemorragieVisceres = sampleWithRequiredData;
        const hemorragieVisceresCollection: IHemorragieVisceres[] = [
          {
            ...hemorragieVisceres,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addHemorragieVisceresToCollectionIfMissing(hemorragieVisceresCollection, hemorragieVisceres);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a HemorragieVisceres to an array that doesn't contain it", () => {
        const hemorragieVisceres: IHemorragieVisceres = sampleWithRequiredData;
        const hemorragieVisceresCollection: IHemorragieVisceres[] = [sampleWithPartialData];
        expectedResult = service.addHemorragieVisceresToCollectionIfMissing(hemorragieVisceresCollection, hemorragieVisceres);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(hemorragieVisceres);
      });

      it('should add only unique HemorragieVisceres to an array', () => {
        const hemorragieVisceresArray: IHemorragieVisceres[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const hemorragieVisceresCollection: IHemorragieVisceres[] = [sampleWithRequiredData];
        expectedResult = service.addHemorragieVisceresToCollectionIfMissing(hemorragieVisceresCollection, ...hemorragieVisceresArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const hemorragieVisceres: IHemorragieVisceres = sampleWithRequiredData;
        const hemorragieVisceres2: IHemorragieVisceres = sampleWithPartialData;
        expectedResult = service.addHemorragieVisceresToCollectionIfMissing([], hemorragieVisceres, hemorragieVisceres2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(hemorragieVisceres);
        expect(expectedResult).toContain(hemorragieVisceres2);
      });

      it('should accept null and undefined values', () => {
        const hemorragieVisceres: IHemorragieVisceres = sampleWithRequiredData;
        expectedResult = service.addHemorragieVisceresToCollectionIfMissing([], null, hemorragieVisceres, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(hemorragieVisceres);
      });

      it('should return initial array if no HemorragieVisceres is added', () => {
        const hemorragieVisceresCollection: IHemorragieVisceres[] = [sampleWithRequiredData];
        expectedResult = service.addHemorragieVisceresToCollectionIfMissing(hemorragieVisceresCollection, undefined, null);
        expect(expectedResult).toEqual(hemorragieVisceresCollection);
      });
    });

    describe('compareHemorragieVisceres', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareHemorragieVisceres(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareHemorragieVisceres(entity1, entity2);
        const compareResult2 = service.compareHemorragieVisceres(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareHemorragieVisceres(entity1, entity2);
        const compareResult2 = service.compareHemorragieVisceres(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareHemorragieVisceres(entity1, entity2);
        const compareResult2 = service.compareHemorragieVisceres(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
