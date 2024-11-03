import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IHemorragieVisceres, NewHemorragieVisceres } from '../hemorragie-visceres.model';

export type PartialUpdateHemorragieVisceres = Partial<IHemorragieVisceres> & Pick<IHemorragieVisceres, 'id'>;

export type EntityResponseType = HttpResponse<IHemorragieVisceres>;
export type EntityArrayResponseType = HttpResponse<IHemorragieVisceres[]>;

@Injectable({ providedIn: 'root' })
export class HemorragieVisceresService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/hemorragie-visceres');

  create(hemorragieVisceres: NewHemorragieVisceres): Observable<EntityResponseType> {
    return this.http.post<IHemorragieVisceres>(this.resourceUrl, hemorragieVisceres, { observe: 'response' });
  }

  update(hemorragieVisceres: IHemorragieVisceres): Observable<EntityResponseType> {
    return this.http.put<IHemorragieVisceres>(
      `${this.resourceUrl}/${this.getHemorragieVisceresIdentifier(hemorragieVisceres)}`,
      hemorragieVisceres,
      { observe: 'response' },
    );
  }

  partialUpdate(hemorragieVisceres: PartialUpdateHemorragieVisceres): Observable<EntityResponseType> {
    return this.http.patch<IHemorragieVisceres>(
      `${this.resourceUrl}/${this.getHemorragieVisceresIdentifier(hemorragieVisceres)}`,
      hemorragieVisceres,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IHemorragieVisceres>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IHemorragieVisceres[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getHemorragieVisceresIdentifier(hemorragieVisceres: Pick<IHemorragieVisceres, 'id'>): number {
    return hemorragieVisceres.id;
  }

  compareHemorragieVisceres(o1: Pick<IHemorragieVisceres, 'id'> | null, o2: Pick<IHemorragieVisceres, 'id'> | null): boolean {
    return o1 && o2 ? this.getHemorragieVisceresIdentifier(o1) === this.getHemorragieVisceresIdentifier(o2) : o1 === o2;
  }

  addHemorragieVisceresToCollectionIfMissing<Type extends Pick<IHemorragieVisceres, 'id'>>(
    hemorragieVisceresCollection: Type[],
    ...hemorragieVisceresToCheck: (Type | null | undefined)[]
  ): Type[] {
    const hemorragieVisceres: Type[] = hemorragieVisceresToCheck.filter(isPresent);
    if (hemorragieVisceres.length > 0) {
      const hemorragieVisceresCollectionIdentifiers = hemorragieVisceresCollection.map(hemorragieVisceresItem =>
        this.getHemorragieVisceresIdentifier(hemorragieVisceresItem),
      );
      const hemorragieVisceresToAdd = hemorragieVisceres.filter(hemorragieVisceresItem => {
        const hemorragieVisceresIdentifier = this.getHemorragieVisceresIdentifier(hemorragieVisceresItem);
        if (hemorragieVisceresCollectionIdentifiers.includes(hemorragieVisceresIdentifier)) {
          return false;
        }
        hemorragieVisceresCollectionIdentifiers.push(hemorragieVisceresIdentifier);
        return true;
      });
      return [...hemorragieVisceresToAdd, ...hemorragieVisceresCollection];
    }
    return hemorragieVisceresCollection;
  }
}
