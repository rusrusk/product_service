import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) {}


  public findAllProducts() {
    return this.httpClient.get('http://localhost:9090/products')
  }

}
