import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Sector} from "../../models/sector.model";

const baseUrl = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})
export class SectorService {

  constructor(private http: HttpClient) { }

  getSectors(): Observable<Array<Sector>> {
    // @ts-ignore
    return this.http.get(baseUrl + "/sectors");
  }
}
