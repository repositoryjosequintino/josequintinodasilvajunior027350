import { Routes } from '@angular/router';
import { ArtistaCadastrarPage } from './pages/artista-page/artista-cadastrar-page/artista-cadastrar-page';

export const routes: Routes = [
    {
        path: "",
        redirectTo: "artista-cadastrar",
        pathMatch: "full"
    },
    {
        path: "artista-cadastrar",
        component: ArtistaCadastrarPage
    }
];
