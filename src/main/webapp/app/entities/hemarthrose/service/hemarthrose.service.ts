import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IHemarthrose, NewHemarthrose } from '../hemarthrose.model';

export type PartialUpdateHemarthrose = Partial<IHemarthrose> & Pick<IHemarthrose, 'id'>;

export type EntityResponseType = HttpResponse<IHemarthrose>;
export type EntityArrayResponseType = HttpResponse<IHemarthrose[]>;

@Injectable({ providedIn: 'root' })
export class HemarthroseService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/hemarthroses');

  create(hemarthrose: NewHemarthrose): Observable<EntityResponseType> {
    return this.http.post<IHemarthrose>(this.resourceUrl, hemarthrose, { observe: 'response' });
  }

  update(hemarthrose: IHemarthrose): Observable<EntityResponseType> {
    return this.http.put<IHemarthrose>(`${this.resourceUrl}/${this.getHemarthroseIdentifier(hemarthrose)}`, hemarthrose, {
      observe: 'response',
    });
  }

  partialUpdate(hemarthrose: PartialUpdateHemarthrose): Observable<EntityResponseType> {
    return this.http.patch<IHemarthrose>(`${this.resourceUrl}/${this.getHemarthroseIdentifier(hemarthrose)}`, hemarthrose, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IHemarthrose>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IHemarthrose[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getHemarthroseIdentifier(hemarthrose: Pick<IHemarthrose, 'id'>): number {
    return hemarthrose.id;
  }

  compareHemarthrose(o1: Pick<IHemarthrose, 'id'> | null, o2: Pick<IHemarthrose, 'id'> | null): boolean {
    return o1 && o2 ? this.getHemarthroseIdentifier(o1) === this.getHemarthroseIdentifier(o2) : o1 === o2;
  }

  addHemarthroseToCollectionIfMissing<Type extends Pick<IHemarthrose, 'id'>>(
    hemarthroseCollection: Type[],
    ...hemarthrosesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const hemarthroses: Type[] = hemarthrosesToCheck.filter(isPresent);
    if (hemarthroses.length > 0) {
      const hemarthroseCollectionIdentifiers = hemarthroseCollection.map(hemarthroseItem => this.getHemarthroseIdentifier(hemarthroseItem));
      const hemarthrosesToAdd = hemarthroses.filter(hemarthroseItem => {
        const hemarthroseIdentifier = this.getHemarthroseIdentifier(hemarthroseItem);
        if (hemarthroseCollectionIdentifiers.includes(hemarthroseIdentifier)) {
          return false;
        }
        hemarthroseCollectionIdentifiers.push(hemarthroseIdentifier);
        return true;
      });
      return [...hemarthrosesToAdd, ...hemarthroseCollection];
    }
    return hemarthroseCollection;
  }
}
