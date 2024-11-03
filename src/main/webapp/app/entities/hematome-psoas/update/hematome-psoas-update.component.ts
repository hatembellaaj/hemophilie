import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { OuiNonNP } from 'app/entities/enumerations/oui-non-np.model';
import { TypeManifestation } from 'app/entities/enumerations/type-manifestation.model';
import { IHematomePsoas } from '../hematome-psoas.model';
import { HematomePsoasService } from '../service/hematome-psoas.service';
import { HematomePsoasFormGroup, HematomePsoasFormService } from './hematome-psoas-form.service';

@Component({
  standalone: true,
  selector: 'jhi-hematome-psoas-update',
  templateUrl: './hematome-psoas-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class HematomePsoasUpdateComponent implements OnInit {
  isSaving = false;
  hematomePsoas: IHematomePsoas | null = null;
  ouiNonNPValues = Object.keys(OuiNonNP);
  typeManifestationValues = Object.keys(TypeManifestation);

  protected hematomePsoasService = inject(HematomePsoasService);
  protected hematomePsoasFormService = inject(HematomePsoasFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: HematomePsoasFormGroup = this.hematomePsoasFormService.createHematomePsoasFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ hematomePsoas }) => {
      this.hematomePsoas = hematomePsoas;
      if (hematomePsoas) {
        this.updateForm(hematomePsoas);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const hematomePsoas = this.hematomePsoasFormService.getHematomePsoas(this.editForm);
    if (hematomePsoas.id !== null) {
      this.subscribeToSaveResponse(this.hematomePsoasService.update(hematomePsoas));
    } else {
      this.subscribeToSaveResponse(this.hematomePsoasService.create(hematomePsoas));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHematomePsoas>>): void {
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

  protected updateForm(hematomePsoas: IHematomePsoas): void {
    this.hematomePsoas = hematomePsoas;
    this.hematomePsoasFormService.resetForm(this.editForm, hematomePsoas);
  }
}
