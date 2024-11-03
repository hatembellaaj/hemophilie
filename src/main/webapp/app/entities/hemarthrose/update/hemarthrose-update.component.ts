import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { OuiNonNP } from 'app/entities/enumerations/oui-non-np.model';
import { TypeManifestation } from 'app/entities/enumerations/type-manifestation.model';
import { IHemarthrose } from '../hemarthrose.model';
import { HemarthroseService } from '../service/hemarthrose.service';
import { HemarthroseFormGroup, HemarthroseFormService } from './hemarthrose-form.service';

@Component({
  standalone: true,
  selector: 'jhi-hemarthrose-update',
  templateUrl: './hemarthrose-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class HemarthroseUpdateComponent implements OnInit {
  isSaving = false;
  hemarthrose: IHemarthrose | null = null;
  ouiNonNPValues = Object.keys(OuiNonNP);
  typeManifestationValues = Object.keys(TypeManifestation);

  protected hemarthroseService = inject(HemarthroseService);
  protected hemarthroseFormService = inject(HemarthroseFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: HemarthroseFormGroup = this.hemarthroseFormService.createHemarthroseFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ hemarthrose }) => {
      this.hemarthrose = hemarthrose;
      if (hemarthrose) {
        this.updateForm(hemarthrose);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const hemarthrose = this.hemarthroseFormService.getHemarthrose(this.editForm);
    if (hemarthrose.id !== null) {
      this.subscribeToSaveResponse(this.hemarthroseService.update(hemarthrose));
    } else {
      this.subscribeToSaveResponse(this.hemarthroseService.create(hemarthrose));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHemarthrose>>): void {
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

  protected updateForm(hemarthrose: IHemarthrose): void {
    this.hemarthrose = hemarthrose;
    this.hemarthroseFormService.resetForm(this.editForm, hemarthrose);
  }
}
