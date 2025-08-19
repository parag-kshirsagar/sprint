import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AddMenuComponent } from './components/add-menu/add-menu.component';
import { ViewMenuComponent } from './components/view-menu/view-menu.component';
import { UpdateMenuComponent } from './components/update-menu/update-menu.component';

export const routes: Routes = [
 { path: '', component: HomeComponent },
 { path: 'add-menu', component: AddMenuComponent },
 { path: 'view-menu', component: ViewMenuComponent },
 { path: 'update-menu/:id', component: UpdateMenuComponent },
 { path: '**', redirectTo: '' },
 ];