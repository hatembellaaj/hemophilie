import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { OuiNonNP } from 'app/entities/enumerations/oui-non-np.model';
import { TypeHemorragie } from 'app/entities/enumerations/type-hemorragie.model';
import { IHemorragiesCutaneoMuqueuses } from '../hemorragies-cutaneo-muqueuses.model';
import { HemorragiesCutaneoMuqueusesService } from '../service/hemorragies-cutaneo-muqueuses.service';
import { HemorragiesCutaneoMuqueusesFormGroup, HemorragiesCutaneoMuqueusesFormService } from './hemorragies-cutaneo-muqueuses-form.service';

@Component({
  standalone: true,
  selector: 'jhi-hemorragies-cutaneo-muqueuses-update',
  templateUrl: './hemorragies-cutaneo-muqueuses-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class HemorragiesCutaneoMuqueusesUpdateComponent implements OnInit {
  isSaving = false;
  hemorragiesCutaneoMuqueuses: IHemorragiesCutaneoMuqueuses | null = null;
  ouiNonNPValues = Object.keys(OuiNonNP);
  typeHemorragieValues = Object.keys(TypeHemorragie);

  protected hemorragiesCutaneoMuqueusesService = inject(HemorragiesCutaneoMuqueusesService);
  protected hemorragiesCutaneoMuqueusesFormService = inject(HemorragiesCutaneoMuqueusesFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: HemorragiesCutaneoMuqueusesFormGroup = this.hemorragiesCutaneoMuqueusesFormService.createHemorragiesCutaneoMuqueusesFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ hemorragiesCutaneoMuqueuses }) => {
      this.hemorragiesCutaneoMuqueuses = hemorragiesCutaneoMuqueuses;
      if (hemorragiesCutaneoMuqueuses) {
        this.updateForm(hemorragiesCutaneoMuqueuses);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const hemorragiesCutaneoMuqueuses = this.hemorragiesCutaneoMuqueusesFormService.getHemorragiesCutaneoMuqueuses(this.editForm);
    if (hemorragiesCutaneoMuqueuses.id !== null) {
      this.subscribeToSaveResponse(this.hemorragiesCutaneoMuqueusesService.update(hemorragiesCutaneoMuqueuses));
    } else {
      this.subscribeToSaveResponse(this.hemorragiesCutaneoMuqueusesService.create(hemorragiesCutaneoMuqueuses));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHemorragiesCutaneoMuqueuses>>): void {
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

  protected updateForm(hemorragiesCutaneoMuqueuses: IHemorragiesCutaneoMuqueuses): void {
    this.hemorragiesCutaneoMuqueuses = hemorragiesCutaneoMuqueuses;
    this.hemorragiesCutaneoMuqueusesFormService.resetForm(this.editForm, hemorragiesCutaneoMuqueuses);
  }
}
