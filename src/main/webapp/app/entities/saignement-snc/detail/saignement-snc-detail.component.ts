import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { ISaignementSNC } from '../saignement-snc.model';

@Component({
  standalone: true,
  selector: 'jhi-saignement-snc-detail',
  templateUrl: './saignement-snc-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class SaignementSNCDetailComponent {
  saignementSNC = input<ISaignementSNC | null>(null);

  previousState(): void {
    window.history.back();
  }
}
