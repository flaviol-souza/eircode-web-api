import { Http, Headers } from '@angular/http';
import { SearchComponent } from './search.component';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable()
export class SearchService {
    http:Http;

    constructor(http:Http){
        this.http = http;
    }

    search(apiKey:string, countryCode:string, addressCode:string, isXMLFormart:boolean) {
        let headers = new Headers();
        let uri:string = 'http://localhost:8080/pcw/'+apiKey+'/address/'+countryCode+'/'+addressCode;
        console.log(isXMLFormart);
        if(isXMLFormart == true){
            headers.append('Content-Type', 'text/xml');
            uri = uri+'?format=xml';
        }else{
            headers.append('Content-Type', 'application/json');
            uri = uri+'?format=json';
        }
        
        console.log(uri);        
        return this.http.get(uri, {headers : headers});
    }
}