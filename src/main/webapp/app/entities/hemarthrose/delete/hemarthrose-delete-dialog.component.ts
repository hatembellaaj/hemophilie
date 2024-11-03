import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IHemarthrose } from '../hemarthrose.model';
import { HemarthroseService } from '../service/hemarthrose.service';

@Component({
  standalone: true,
  templateUrl: './hemarthrose-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class HemarthroseDeleteDialogComponent {
  hemarthrose?: IHemarthrose;

  protected hemarthroseService = inject(HemarthroseService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.hemarthroseService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
