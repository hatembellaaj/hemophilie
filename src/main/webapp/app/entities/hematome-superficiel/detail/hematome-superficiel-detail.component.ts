import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { IHematomeSuperficiel } from '../hematome-superficiel.model';

@Component({
  standalone: true,
  selector: 'jhi-hematome-superficiel-detail',
  templateUrl: './hematome-superficiel-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class HematomeSuperficielDetailComponent {
  hematomeSuperficiel = input<IHematomeSuperficiel | null>(null);

  previousState(): void {
    window.history.back();
  }
}
