import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IHemorragiesCutaneoMuqueuses } from '../hemorragies-cutaneo-muqueuses.model';
import { HemorragiesCutaneoMuqueusesService } from '../service/hemorragies-cutaneo-muqueuses.service';

@Component({
  standalone: true,
  templateUrl: './hemorragies-cutaneo-muqueuses-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class HemorragiesCutaneoMuqueusesDeleteDialogComponent {
  hemorragiesCutaneoMuqueuses?: IHemorragiesCutaneoMuqueuses;

  protected hemorragiesCutaneoMuqueusesService = inject(HemorragiesCutaneoMuqueusesService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.hemorragiesCutaneoMuqueusesService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
