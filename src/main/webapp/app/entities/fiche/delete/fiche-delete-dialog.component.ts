import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IFiche } from '../fiche.model';
import { FicheService } from '../service/fiche.service';

@Component({
  standalone: true,
  templateUrl: './fiche-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class FicheDeleteDialogComponent {
  fiche?: IFiche;

  protected ficheService = inject(FicheService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ficheService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
