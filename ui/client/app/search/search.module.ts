import {NgModule} from '@angular/core';
import {SearchComponent} from './search.component';
import { SearchService } from './search.service';

@NgModule({
    declarations: [SearchComponent],
    exports: [SearchComponent],
    providers: [SearchService]
})
export class SearchModule{ }