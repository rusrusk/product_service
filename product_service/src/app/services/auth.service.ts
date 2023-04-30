import { HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { of, take } from "rxjs";
import { HttpService } from "./http.service";
import tokenUrl from "../constants/token";
import redirectUrl from "../constants/redirect";
import { Buffer } from "buffer";


@Injectable()
export class AuthService {
	public code: string = '';
	constructor(
		private httpService: HttpService
	) {}

	getToken() {
		const demoClient = 'client';
        const demoSecret = 'secret';
        
        const basicAuth = `Basic ` + Buffer.from(`${demoClient}:${demoSecret}`).toString('base64');
        const headers = new HttpHeaders(
          {'content-type': `application/json`,
          'Authorization': basicAuth
        });
        const options = {
          headers: headers
        }
        
        
		const post =  this.httpService.doPost(tokenUrl(this.code), null, options);
    console.log("tokenUrl === " + this.code);
    
    
    return post;
	}
}