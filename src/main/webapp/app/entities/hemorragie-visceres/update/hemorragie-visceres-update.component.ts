import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { OuiNonNP } from 'app/entities/enumerations/oui-non-np.model';
import { TypeHemorragieVisceres } from 'app/entities/enumerations/type-hemorragie-visceres.model';
import { IHemorragieVisceres } from '../hemorragie-visceres.model';
import { HemorragieVisceresService } from '../service/hemorragie-visceres.service';
import { HemorragieVisceresFormGroup, HemorragieVisceresFormService } from './hemorragie-visceres-form.service';

@Component({
  standalone: true,
  selector: 'jhi-hemorragie-visceres-update',
  templateUrl: './hemorragie-visceres-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class HemorragieVisceresUpdateComponent implements OnInit {
  isSaving = false;
  hemorragieVisceres: IHemorragieVisceres | null = null;
  ouiNonNPValues = Object.keys(OuiNonNP);
  typeHemorragieVisceresValues = Object.keys(TypeHemorragieVisceres);

  protected hemorragieVisceresService = inject(HemorragieVisceresService);
  protected hemorragieVisceresFormService = inject(HemorragieVisceresFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: HemorragieVisceresFormGroup = this.hemorragieVisceresFormService.createHemorragieVisceresFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ hemorragieVisceres }) => {
      this.hemorragieVisceres = hemorragieVisceres;
      if (hemorragieVisceres) {
        this.updateForm(hemorragieVisceres);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const hemorragieVisceres = this.hemorragieVisceresFormService.getHemorragieVisceres(this.editForm);
    if (hemorragieVisceres.id !== null) {
      this.subscribeToSaveResponse(this.hemorragieVisceresService.update(hemorragieVisceres));
    } else {
      this.subscribeToSaveResponse(this.hemorragieVisceresService.create(hemorragieVisceres));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHemorragieVisceres>>): void {
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

  protected updateForm(hemorragieVisceres: IHemorragieVisceres): void {
    this.hemorragieVisceres = hemorragieVisceres;
    this.hemorragieVisceresFormService.resetForm(this.editForm, hemorragieVisceres);
  }
}
