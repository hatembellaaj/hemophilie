import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { OuiNonNP } from 'app/entities/enumerations/oui-non-np.model';
import { TypeManifestation } from 'app/entities/enumerations/type-manifestation.model';
import { IHematomeSuperficiel } from '../hematome-superficiel.model';
import { HematomeSuperficielService } from '../service/hematome-superficiel.service';
import { HematomeSuperficielFormGroup, HematomeSuperficielFormService } from './hematome-superficiel-form.service';

@Component({
  standalone: true,
  selector: 'jhi-hematome-superficiel-update',
  templateUrl: './hematome-superficiel-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class HematomeSuperficielUpdateComponent implements OnInit {
  isSaving = false;
  hematomeSuperficiel: IHematomeSuperficiel | null = null;
  ouiNonNPValues = Object.keys(OuiNonNP);
  typeManifestationValues = Object.keys(TypeManifestation);

  protected hematomeSuperficielService = inject(HematomeSuperficielService);
  protected hematomeSuperficielFormService = inject(HematomeSuperficielFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: HematomeSuperficielFormGroup = this.hematomeSuperficielFormService.createHematomeSuperficielFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ hematomeSuperficiel }) => {
      this.hematomeSuperficiel = hematomeSuperficiel;
      if (hematomeSuperficiel) {
        this.updateForm(hematomeSuperficiel);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const hematomeSuperficiel = this.hematomeSuperficielFormService.getHematomeSuperficiel(this.editForm);
    if (hematomeSuperficiel.id !== null) {
      this.subscribeToSaveResponse(this.hematomeSuperficielService.update(hematomeSuperficiel));
    } else {
      this.subscribeToSaveResponse(this.hematomeSuperficielService.create(hematomeSuperficiel));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHematomeSuperficiel>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(hematomeSuperficiel: IHematomeSuperficiel): void {
    this.hematomeSuperficiel = hematomeSuperficiel;
    this.hematomeSuperficielFormService.resetForm(this.editForm, hematomeSuperficiel);
  }
}
