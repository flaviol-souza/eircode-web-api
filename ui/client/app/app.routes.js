"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var search_component_1 = require("./search/search.component");
exports.appRoutes = [
    { path: '', component: search_component_1.SearchComponent },
    { path: 'search', component: search_component_1.SearchComponent },
    { path: '**', component: search_component_1.SearchComponent }
];
//export const routing = RouterModule.forRoot{appRoutes}; 
//# sourceMappingURL=app.routes.js.map