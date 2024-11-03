import { Directive, HostListener, Input, contentChild, effect, inject } from '@angular/core';
import { FaIconComponent } from '@fortawesome/angular-fontawesome';
import { IconDefinition, faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';

import { SortDirective } from './sort.directive';

@Directive({
  standalone: true,
  selector: '[jhiSortBy]',
})
export class SortByDirective {
  @Input() jhiSortBy!: string;

  iconComponent = contentChild(FaIconComponent);

  protected sortIcon = faSort;
  protected sortAscIcon = faSortUp;
  protected sortDescIcon = faSortDown;

  private readonly sort = inject(SortDirective, { host: true });

  constructor() {
    effect(() => {
      if (this.iconComponent()) {
        let icon: IconDefinition = this.sortIcon;
        const { predicate, order } = this.sort.sortState();
        if (predicate === this.jhiSortBy && order !== undefined) {
          icon = order === 'asc' ? this.sortAscIcon : this.sortDescIcon;
        }
        this.iconComponent()!.icon = icon.iconName;
        this.iconComponent()!.render();
      }
    });
  }

  @HostListener('click')
  onClick(): void {
    if (this.iconComponent()) {
      this.sort.sort(this.jhiSortBy);
    }
  }
}