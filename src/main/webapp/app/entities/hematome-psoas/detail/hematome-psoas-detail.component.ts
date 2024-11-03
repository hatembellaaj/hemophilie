import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { IHematomePsoas } from '../hematome-psoas.model';

@Component({
  standalone: true,
  selector: 'jhi-hematome-psoas-detail',
  templateUrl: './hematome-psoas-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class HematomePsoasDetailComponent {
  hematomePsoas = input<IHematomePsoas | null>(null);

  previousState(): void {
    window.history.back();
  }
}
