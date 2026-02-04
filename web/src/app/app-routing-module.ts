import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ArtistaCadastrarPage } from './pages/artista-page/artista-cadastrar-page/artista-cadastrar-page';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/artista-cadastrar',
    pathMatch: 'full'
  },
  {
    path: "artista-cadastrar",
    component: ArtistaCadastrarPage
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
