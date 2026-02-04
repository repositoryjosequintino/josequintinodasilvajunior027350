import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ArtistaService {

  private ENDPOINT = "http://localhost:8080/api/v1/artista";

  private httpClient = inject(HttpClient);

  public create(artistaModel: any): Observable<any> {
    return this.httpClient.post<any>(this.ENDPOINT, artistaModel);
  }
  
}
