import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { IHemorragiesCutaneoMuqueuses } from '../hemorragies-cutaneo-muqueuses.model';

@Component({
  standalone: true,
  selector: 'jhi-hemorragies-cutaneo-muqueuses-detail',
  templateUrl: './hemorragies-cutaneo-muqueuses-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class HemorragiesCutaneoMuqueusesDetailComponent {
  hemorragiesCutaneoMuqueuses = input<IHemorragiesCutaneoMuqueuses | null>(null);

  previousState(): void {
    window.history.back();
  }
}
