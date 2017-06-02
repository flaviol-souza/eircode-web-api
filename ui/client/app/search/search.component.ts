import {Component} from '@angular/core';
import {Http, Headers} from '@angular/http';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';
import { SearchService } from './search.service';

@Component({
    moduleId: module.id,
    selector: 'search',
    templateUrl:'./search.component.html',
    providers: [SearchService]
})
export class SearchComponent {
    
    searchService:SearchService;
    searchForm:FormGroup;
    
    apiKey:string = "";
    countryCode:string = "";
    addressCode:string = "";
    format:boolean = false;
    fragment:string = "";

    constructor(searchService:SearchService, fb:FormBuilder){
        this.searchService = searchService;
        this.searchForm = fb.group({
            apiKey: ['', Validators.required],
            countryCode: ['', Validators.required],
            addressCode: ['', Validators.required],
            format: ['']
        });
    }

    clearForm(event){
        event.preventDefault();
        console.log('reset form');

        this.apiKey = "";
        this.countryCode = "";
        this.addressCode = "";
        this.format = false;
        this.fragment = "";
    }

    search(event){
        event.preventDefault();

        console.log(this.apiKey);
        console.log(this.countryCode);
        console.log(this.addressCode);
        console.log(this.format);
        this.fragment = '';
        this.searchService.search(this.apiKey, this.countryCode, this.addressCode, this.format)
            .subscribe((res: any) => {
                console.log(res);
                this.fragment = res._body;
            }, (error: any) => {
                this.fragment = 'ERROR: ' + error._body;
                console.log(error);
            });
    }
}