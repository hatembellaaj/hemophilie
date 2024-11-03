import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IHematomePsoas } from '../hematome-psoas.model';
import { HematomePsoasService } from '../service/hematome-psoas.service';

@Component({
  standalone: true,
  templateUrl: './hematome-psoas-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class HematomePsoasDeleteDialogComponent {
  hematomePsoas?: IHematomePsoas;

  protected hematomePsoasService = inject(HematomePsoasService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.hematomePsoasService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
