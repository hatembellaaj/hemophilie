import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISaignementSNC, NewSaignementSNC } from '../saignement-snc.model';

export type PartialUpdateSaignementSNC = Partial<ISaignementSNC> & Pick<ISaignementSNC, 'id'>;

export type EntityResponseType = HttpResponse<ISaignementSNC>;
export type EntityArrayResponseType = HttpResponse<ISaignementSNC[]>;

@Injectable({ providedIn: 'root' })
export class SaignementSNCService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/saignement-sncs');

  create(saignementSNC: NewSaignementSNC): Observable<EntityResponseType> {
    return this.http.post<ISaignementSNC>(this.resourceUrl, saignementSNC, { observe: 'response' });
  }

  update(saignementSNC: ISaignementSNC): Observable<EntityResponseType> {
    return this.http.put<ISaignementSNC>(`${this.resourceUrl}/${this.getSaignementSNCIdentifier(saignementSNC)}`, saignementSNC, {
      observe: 'response',
    });
  }

  partialUpdate(saignementSNC: PartialUpdateSaignementSNC): Observable<EntityResponseType> {
    return this.http.patch<ISaignementSNC>(`${this.resourceUrl}/${this.getSaignementSNCIdentifier(saignementSNC)}`, saignementSNC, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISaignementSNC>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISaignementSNC[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSaignementSNCIdentifier(saignementSNC: Pick<ISaignementSNC, 'id'>): number {
    return saignementSNC.id;
  }

  compareSaignementSNC(o1: Pick<ISaignementSNC, 'id'> | null, o2: Pick<ISaignementSNC, 'id'> | null): boolean {
    return o1 && o2 ? this.getSaignementSNCIdentifier(o1) === this.getSaignementSNCIdentifier(o2) : o1 === o2;
  }

  addSaignementSNCToCollectionIfMissing<Type extends Pick<ISaignementSNC, 'id'>>(
    saignementSNCCollection: Type[],
    ...saignementSNCSToCheck: (Type | null | undefined)[]
  ): Type[] {
    const saignementSNCS: Type[] = saignementSNCSToCheck.filter(isPresent);
    if (saignementSNCS.length > 0) {
      const saignementSNCCollectionIdentifiers = saignementSNCCollection.map(saignementSNCItem =>
        this.getSaignementSNCIdentifier(saignementSNCItem),
      );
      const saignementSNCSToAdd = saignementSNCS.filter(saignementSNCItem => {
        const saignementSNCIdentifier = this.getSaignementSNCIdentifier(saignementSNCItem);
        if (saignementSNCCollectionIdentifiers.includes(saignementSNCIdentifier)) {
          return false;
        }
        saignementSNCCollectionIdentifiers.push(saignementSNCIdentifier);
        return true;
      });
      return [...saignementSNCSToAdd, ...saignementSNCCollection];
    }
    return saignementSNCCollection;
  }
}
