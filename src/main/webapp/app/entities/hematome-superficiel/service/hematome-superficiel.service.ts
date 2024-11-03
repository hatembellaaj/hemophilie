import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IHematomeSuperficiel, NewHematomeSuperficiel } from '../hematome-superficiel.model';

export type PartialUpdateHematomeSuperficiel = Partial<IHematomeSuperficiel> & Pick<IHematomeSuperficiel, 'id'>;

export type EntityResponseType = HttpResponse<IHematomeSuperficiel>;
export type EntityArrayResponseType = HttpResponse<IHematomeSuperficiel[]>;

@Injectable({ providedIn: 'root' })
export class HematomeSuperficielService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/hematome-superficiels');

  create(hematomeSuperficiel: NewHematomeSuperficiel): Observable<EntityResponseType> {
    return this.http.post<IHematomeSuperficiel>(this.resourceUrl, hematomeSuperficiel, { observe: 'response' });
  }

  update(hematomeSuperficiel: IHematomeSuperficiel): Observable<EntityResponseType> {
    return this.http.put<IHematomeSuperficiel>(
      `${this.resourceUrl}/${this.getHematomeSuperficielIdentifier(hematomeSuperficiel)}`,
      hematomeSuperficiel,
      { observe: 'response' },
    );
  }

  partialUpdate(hematomeSuperficiel: PartialUpdateHematomeSuperficiel): Observable<EntityResponseType> {
    return this.http.patch<IHematomeSuperficiel>(
      `${this.resourceUrl}/${this.getHematomeSuperficielIdentifier(hematomeSuperficiel)}`,
      hematomeSuperficiel,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IHematomeSuperficiel>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IHematomeSuperficiel[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getHematomeSuperficielIdentifier(hematomeSuperficiel: Pick<IHematomeSuperficiel, 'id'>): number {
    return hematomeSuperficiel.id;
  }

  compareHematomeSuperficiel(o1: Pick<IHematomeSuperficiel, 'id'> | null, o2: Pick<IHematomeSuperficiel, 'id'> | null): boolean {
    return o1 && o2 ? this.getHematomeSuperficielIdentifier(o1) === this.getHematomeSuperficielIdentifier(o2) : o1 === o2;
  }

  addHematomeSuperficielToCollectionIfMissing<Type extends Pick<IHematomeSuperficiel, 'id'>>(
    hematomeSuperficielCollection: Type[],
    ...hematomeSuperficielsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const hematomeSuperficiels: Type[] = hematomeSuperficielsToCheck.filter(isPresent);
    if (hematomeSuperficiels.length > 0) {
      const hematomeSuperficielCollectionIdentifiers = hematomeSuperficielCollection.map(hematomeSuperficielItem =>
        this.getHematomeSuperficielIdentifier(hematomeSuperficielItem),
      );
      const hematomeSuperficielsToAdd = hematomeSuperficiels.filter(hematomeSuperficielItem => {
        const hematomeSuperficielIdentifier = this.getHematomeSuperficielIdentifier(hematomeSuperficielItem);
        if (hematomeSuperficielCollectionIdentifiers.includes(hematomeSuperficielIdentifier)) {
          return false;
        }
        hematomeSuperficielCollectionIdentifiers.push(hematomeSuperficielIdentifier);
        return true;
      });
      return [...hematomeSuperficielsToAdd, ...hematomeSuperficielCollection];
    }
    return hematomeSuperficielCollection;
  }
}
