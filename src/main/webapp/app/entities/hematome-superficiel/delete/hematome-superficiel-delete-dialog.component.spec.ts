jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { HematomeSuperficielService } from '../service/hematome-superficiel.service';

import { HematomeSuperficielDeleteDialogComponent } from './hematome-superficiel-delete-dialog.component';

describe('HematomeSuperficiel Management Delete Component', () => {
  let comp: HematomeSuperficielDeleteDialogComponent;
  let fixture: ComponentFixture<HematomeSuperficielDeleteDialogComponent>;
  let service: HematomeSuperficielService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HematomeSuperficielDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(HematomeSuperficielDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(HematomeSuperficielDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(HematomeSuperficielService);
    mockActiveModal = TestBed.inject(NgbActiveModal);
  });

  describe('confirmDelete', () => {
    it('Should call delete service on confirmDelete', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(service, 'delete').mockReturnValue(of(new HttpResponse({ body: {} })));

        // WHEN
        comp.confirmDelete(123);
        tick();

        // THEN
        expect(service.delete).toHaveBeenCalledWith(123);
        expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
      }),
    ));

    it('Should not call delete service on clear', () => {
      // GIVEN
      jest.spyOn(service, 'delete');

      // WHEN
      comp.cancel();

      // THEN
      expect(service.delete).not.toHaveBeenCalled();
      expect(mockActiveModal.close).not.toHaveBeenCalled();
      expect(mockActiveModal.dismiss).toHaveBeenCalled();
    });
  });
});
