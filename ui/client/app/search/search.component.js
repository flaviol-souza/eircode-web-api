"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var forms_1 = require("@angular/forms");
var search_service_1 = require("./search.service");
var SearchComponent = (function () {
    function SearchComponent(searchService, fb) {
        this.apiKey = "";
        this.countryCode = "";
        this.addressCode = "";
        this.format = false;
        this.fragment = "";
        this.searchService = searchService;
        this.searchForm = fb.group({
            apiKey: ['', forms_1.Validators.required],
            countryCode: ['', forms_1.Validators.required],
            addressCode: ['', forms_1.Validators.required],
            format: ['']
        });
    }
    SearchComponent.prototype.clearForm = function (event) {
        event.preventDefault();
        console.log('reset form');
        this.apiKey = "";
        this.countryCode = "";
        this.addressCode = "";
        this.format = false;
        this.fragment = "";
    };
    SearchComponent.prototype.search = function (event) {
        var _this = this;
        event.preventDefault();
        console.log(this.apiKey);
        console.log(this.countryCode);
        console.log(this.addressCode);
        console.log(this.format);
        this.fragment = '';
        this.searchService.search(this.apiKey, this.countryCode, this.addressCode, this.format)
            .subscribe(function (res) {
            console.log(res);
            _this.fragment = res._body;
        }, function (error) {
            _this.fragment = 'ERROR: ' + error._body;
            console.log(error);
        });
    };
    return SearchComponent;
}());
SearchComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'search',
        templateUrl: './search.component.html',
        providers: [search_service_1.SearchService]
    }),
    __metadata("design:paramtypes", [search_service_1.SearchService, forms_1.FormBuilder])
], SearchComponent);
exports.SearchComponent = SearchComponent;
//# sourceMappingURL=search.component.js.map