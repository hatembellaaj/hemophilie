import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { IFiche } from '../fiche.model';

@Component({
  standalone: true,
  selector: 'jhi-fiche-detail',
  templateUrl: './fiche-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class FicheDetailComponent {
  fiche = input<IFiche | null>(null);

  previousState(): void {
    window.history.back();
  }
}
