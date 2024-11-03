import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { IHemarthrose } from '../hemarthrose.model';

@Component({
  standalone: true,
  selector: 'jhi-hemarthrose-detail',
  templateUrl: './hemarthrose-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class HemarthroseDetailComponent {
  hemarthrose = input<IHemarthrose | null>(null);

  previousState(): void {
    window.history.back();
  }
}
