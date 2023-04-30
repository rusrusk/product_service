import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { take } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpHandler, HttpHeaders } from '@angular/common/http';
import { HttpService } from '../services/http.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  //auth code on session storage , will be generted each time for each req 


  constructor(
    private authService: AuthService,
    private activatedRoute: ActivatedRoute,
    private router: Router
    ) {
      this.getAuthorizationCode();
    }


  ngOnInit(): void {
      this.authService.getToken().pipe(take(1)).subscribe((tokens) => {
        console.log("tokens ============= " , tokens);
        
        if((tokens as any)?.id_token) {
          sessionStorage.setItem('id_token', (tokens as any).id_token);
          this.router.navigate(['/home']);
        }
      })
  }

  getAuthorizationCode() {
    this.activatedRoute.queryParams.subscribe((params) => {
      console.log('PARAMS ============ ' , params);
      
      if (params?.['code']) {
        this.authService.code = params['code'];
        // console.log("code ========== " + this.authService.code);        
      }
    })
  }
}
