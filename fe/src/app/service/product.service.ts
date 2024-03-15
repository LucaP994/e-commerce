import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Product } from '../models/product.model';
import { Observable } from 'rxjs';

const productApi: string = environment.apiUrl+"/products/";
const categoriesApi: string = environment.apiUrl+"/categories/";
@Injectable({
  providedIn: 'root'
})
export class ProductService {
  constructor(
    private http: HttpClient
  ) { }
  public getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(productApi+"all");
  }
}
