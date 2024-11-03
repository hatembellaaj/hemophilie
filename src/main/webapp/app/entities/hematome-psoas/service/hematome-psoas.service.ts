import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IHematomePsoas, NewHematomePsoas } from '../hematome-psoas.model';

export type PartialUpdateHematomePsoas = Partial<IHematomePsoas> & Pick<IHematomePsoas, 'id'>;

export type EntityResponseType = HttpResponse<IHematomePsoas>;
export type EntityArrayResponseType = HttpResponse<IHematomePsoas[]>;

@Injectable({ providedIn: 'root' })
export class HematomePsoasService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/hematome-psoas');

  create(hematomePsoas: NewHematomePsoas): Observable<EntityResponseType> {
    return this.http.post<IHematomePsoas>(this.resourceUrl, hematomePsoas, { observe: 'response' });
  }

  update(hematomePsoas: IHematomePsoas): Observable<EntityResponseType> {
    return this.http.put<IHematomePsoas>(`${this.resourceUrl}/${this.getHematomePsoasIdentifier(hematomePsoas)}`, hematomePsoas, {
      observe: 'response',
    });
  }

  partialUpdate(hematomePsoas: PartialUpdateHematomePsoas): Observable<EntityResponseType> {
    return this.http.patch<IHematomePsoas>(`${this.resourceUrl}/${this.getHematomePsoasIdentifier(hematomePsoas)}`, hematomePsoas, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IHematomePsoas>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IHematomePsoas[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getHematomePsoasIdentifier(hematomePsoas: Pick<IHematomePsoas, 'id'>): number {
    return hematomePsoas.id;
  }

  compareHematomePsoas(o1: Pick<IHematomePsoas, 'id'> | null, o2: Pick<IHematomePsoas, 'id'> | null): boolean {
    return o1 && o2 ? this.getHematomePsoasIdentifier(o1) === this.getHematomePsoasIdentifier(o2) : o1 === o2;
  }

  addHematomePsoasToCollectionIfMissing<Type extends Pick<IHematomePsoas, 'id'>>(
    hematomePsoasCollection: Type[],
    ...hematomePsoasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const hematomePsoas: Type[] = hematomePsoasToCheck.filter(isPresent);
    if (hematomePsoas.length > 0) {
      const hematomePsoasCollectionIdentifiers = hematomePsoasCollection.map(hematomePsoasItem =>
        this.getHematomePsoasIdentifier(hematomePsoasItem),
      );
      const hematomePsoasToAdd = hematomePsoas.filter(hematomePsoasItem => {
        const hematomePsoasIdentifier = this.getHematomePsoasIdentifier(hematomePsoasItem);
        if (hematomePsoasCollectionIdentifiers.includes(hematomePsoasIdentifier)) {
          return false;
        }
        hematomePsoasCollectionIdentifiers.push(hematomePsoasIdentifier);
        return true;
      });
      return [...hematomePsoasToAdd, ...hematomePsoasCollection];
    }
    return hematomePsoasCollection;
  }
}
