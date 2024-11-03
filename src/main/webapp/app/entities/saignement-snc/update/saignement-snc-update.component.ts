import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { OuiNonNP } from 'app/entities/enumerations/oui-non-np.model';
import { EvolutionSNC } from 'app/entities/enumerations/evolution-snc.model';
import { ISaignementSNC } from '../saignement-snc.model';
import { SaignementSNCService } from '../service/saignement-snc.service';
import { SaignementSNCFormGroup, SaignementSNCFormService } from './saignement-snc-form.service';

@Component({
  standalone: true,
  selector: 'jhi-saignement-snc-update',
  templateUrl: './saignement-snc-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class SaignementSNCUpdateComponent implements OnInit {
  isSaving = false;
  saignementSNC: ISaignementSNC | null = null;
  ouiNonNPValues = Object.keys(OuiNonNP);
  evolutionSNCValues = Object.keys(EvolutionSNC);

  protected saignementSNCService = inject(SaignementSNCService);
  protected saignementSNCFormService = inject(SaignementSNCFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: SaignementSNCFormGroup = this.saignementSNCFormService.createSaignementSNCFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ saignementSNC }) => {
      this.saignementSNC = saignementSNC;
      if (saignementSNC) {
        this.updateForm(saignementSNC);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const saignementSNC = this.saignementSNCFormService.getSaignementSNC(this.editForm);
    if (saignementSNC.id !== null) {
      this.subscribeToSaveResponse(this.saignementSNCService.update(saignementSNC));
    } else {
      this.subscribeToSaveResponse(this.saignementSNCService.create(saignementSNC));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISaignementSNC>>): void {
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

  protected updateForm(saignementSNC: ISaignementSNC): void {
    this.saignementSNC = saignementSNC;
    this.saignementSNCFormService.resetForm(this.editForm, saignementSNC);
  }
}
