import { Route } from "@angular/router";
import { HomeComponent } from "./home/home.component";
import { AuthComponent } from "./auth/auth.component";
import { LoginComponent } from "./login/login.component";
import { ProdListingComponent } from "./prod-listing/prod-listing.component";

const redirectToUrl = '';

const routes: Route[] = [
	{path: 'home', component: HomeComponent, pathMatch: 'full'},
	{path: 'auth', component: AuthComponent, pathMatch: 'full'},
	{path: '', redirectTo: 'auth', pathMatch: 'full'},
	{path: 'authorized', redirectTo: 'auth', pathMatch: 'full'},
	{path: 'login', component : LoginComponent, pathMatch: 'full'},
	{path: 'products', component: ProdListingComponent, pathMatch: 'full'},

]

export default routes;