import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { IHemorragieVisceres } from '../hemorragie-visceres.model';

@Component({
  standalone: true,
  selector: 'jhi-hemorragie-visceres-detail',
  templateUrl: './hemorragie-visceres-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class HemorragieVisceresDetailComponent {
  hemorragieVisceres = input<IHemorragieVisceres | null>(null);

  previousState(): void {
    window.history.back();
  }
}
