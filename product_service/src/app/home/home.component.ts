import { Component, OnInit } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { HttpService } from '../services/http.service';
import { take } from 'rxjs';
import product from '../constants/product';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  public productContent: string = '';
  constructor(private httpService: HttpService) {

  }

  ngOnInit(): void {
      this.getDemoInformation();
  }

  private getDemoInformation() {
    const token = sessionStorage.getItem('id_token');
    const bearerToken = 'Bearer ' + token;
    const options = {
      headers: new HttpHeaders({
        'Authorization': bearerToken
      }),
      responseType: 'text/plain'
    }

    this.httpService.doGet(product(), options).pipe(take(1)).subscribe((content) => {
        (this.productContent as any) = content;
    });
  }

}
