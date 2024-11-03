import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IHematomeSuperficiel } from '../hematome-superficiel.model';
import { HematomeSuperficielService } from '../service/hematome-superficiel.service';

@Component({
  standalone: true,
  templateUrl: './hematome-superficiel-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class HematomeSuperficielDeleteDialogComponent {
  hematomeSuperficiel?: IHematomeSuperficiel;

  protected hematomeSuperficielService = inject(HematomeSuperficielService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.hematomeSuperficielService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
