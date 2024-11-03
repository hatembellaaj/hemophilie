import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IHemorragiesCutaneoMuqueuses, NewHemorragiesCutaneoMuqueuses } from '../hemorragies-cutaneo-muqueuses.model';

export type PartialUpdateHemorragiesCutaneoMuqueuses = Partial<IHemorragiesCutaneoMuqueuses> & Pick<IHemorragiesCutaneoMuqueuses, 'id'>;

export type EntityResponseType = HttpResponse<IHemorragiesCutaneoMuqueuses>;
export type EntityArrayResponseType = HttpResponse<IHemorragiesCutaneoMuqueuses[]>;

@Injectable({ providedIn: 'root' })
export class HemorragiesCutaneoMuqueusesService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/hemorragies-cutaneo-muqueuses');

  create(hemorragiesCutaneoMuqueuses: NewHemorragiesCutaneoMuqueuses): Observable<EntityResponseType> {
    return this.http.post<IHemorragiesCutaneoMuqueuses>(this.resourceUrl, hemorragiesCutaneoMuqueuses, { observe: 'response' });
  }

  update(hemorragiesCutaneoMuqueuses: IHemorragiesCutaneoMuqueuses): Observable<EntityResponseType> {
    return this.http.put<IHemorragiesCutaneoMuqueuses>(
      `${this.resourceUrl}/${this.getHemorragiesCutaneoMuqueusesIdentifier(hemorragiesCutaneoMuqueuses)}`,
      hemorragiesCutaneoMuqueuses,
      { observe: 'response' },
    );
  }

  partialUpdate(hemorragiesCutaneoMuqueuses: PartialUpdateHemorragiesCutaneoMuqueuses): Observable<EntityResponseType> {
    return this.http.patch<IHemorragiesCutaneoMuqueuses>(
      `${this.resourceUrl}/${this.getHemorragiesCutaneoMuqueusesIdentifier(hemorragiesCutaneoMuqueuses)}`,
      hemorragiesCutaneoMuqueuses,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IHemorragiesCutaneoMuqueuses>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IHemorragiesCutaneoMuqueuses[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getHemorragiesCutaneoMuqueusesIdentifier(hemorragiesCutaneoMuqueuses: Pick<IHemorragiesCutaneoMuqueuses, 'id'>): number {
    return hemorragiesCutaneoMuqueuses.id;
  }

  compareHemorragiesCutaneoMuqueuses(
    o1: Pick<IHemorragiesCutaneoMuqueuses, 'id'> | null,
    o2: Pick<IHemorragiesCutaneoMuqueuses, 'id'> | null,
  ): boolean {
    return o1 && o2 ? this.getHemorragiesCutaneoMuqueusesIdentifier(o1) === this.getHemorragiesCutaneoMuqueusesIdentifier(o2) : o1 === o2;
  }

  addHemorragiesCutaneoMuqueusesToCollectionIfMissing<Type extends Pick<IHemorragiesCutaneoMuqueuses, 'id'>>(
    hemorragiesCutaneoMuqueusesCollection: Type[],
    ...hemorragiesCutaneoMuqueusesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const hemorragiesCutaneoMuqueuses: Type[] = hemorragiesCutaneoMuqueusesToCheck.filter(isPresent);
    if (hemorragiesCutaneoMuqueuses.length > 0) {
      const hemorragiesCutaneoMuqueusesCollectionIdentifiers = hemorragiesCutaneoMuqueusesCollection.map(hemorragiesCutaneoMuqueusesItem =>
        this.getHemorragiesCutaneoMuqueusesIdentifier(hemorragiesCutaneoMuqueusesItem),
      );
      const hemorragiesCutaneoMuqueusesToAdd = hemorragiesCutaneoMuqueuses.filter(hemorragiesCutaneoMuqueusesItem => {
        const hemorragiesCutaneoMuqueusesIdentifier = this.getHemorragiesCutaneoMuqueusesIdentifier(hemorragiesCutaneoMuqueusesItem);
        if (hemorragiesCutaneoMuqueusesCollectionIdentifiers.includes(hemorragiesCutaneoMuqueusesIdentifier)) {
          return false;
        }
        hemorragiesCutaneoMuqueusesCollectionIdentifiers.push(hemorragiesCutaneoMuqueusesIdentifier);
        return true;
      });
      return [...hemorragiesCutaneoMuqueusesToAdd, ...hemorragiesCutaneoMuqueusesCollection];
    }
    return hemorragiesCutaneoMuqueusesCollection;
  }
}
