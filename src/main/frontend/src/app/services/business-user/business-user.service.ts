import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {BusinessUser} from '../../models/business-user.model';

const baseUrl = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})
export class BusinessUserService {

  constructor(private http: HttpClient) { }

  generateBusinessUser(): Observable<BusinessUser> {
    return this.http.post(baseUrl + "/generate-business-user", "");
  }

  getBusinessUser(id: string): Observable<BusinessUser> {
    return this.http.get(baseUrl + "/business-user/"+id);
  }

  upsertBusinessUser(businessUser: BusinessUser): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };
    return this.http.put(baseUrl + "/business-user", JSON.stringify(businessUser), httpOptions)
  }
}
