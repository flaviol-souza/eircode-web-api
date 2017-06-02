import { Component } from '@angular/core';
import {Http} from '@angular/http';

@Component({
    selector: 'app',
    templateUrl: './app/app.component.html'
})
export class AppComponent{

    addressFragmet:String = "";
    constructor(http:Http){

    }

}