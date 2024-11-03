import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ISaignementSNC } from '../saignement-snc.model';
import { SaignementSNCService } from '../service/saignement-snc.service';

@Component({
  standalone: true,
  templateUrl: './saignement-snc-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class SaignementSNCDeleteDialogComponent {
  saignementSNC?: ISaignementSNC;

  protected saignementSNCService = inject(SaignementSNCService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.saignementSNCService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
