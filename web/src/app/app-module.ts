import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { ArtistaComponent } from './components/artista-component/artista-component';
import { ArtistaCadastrarComponent } from './components/artista/artista-cadastrar-component/artista-cadastrar-component';
import { ArtistaEditarComponent } from './components/artista/artista-editar-component/artista-editar-component';
import { ArtistaDetalharComponent } from './components/artista/artista-detalhar-component/artista-detalhar-component';
import { Album } from './components/album/album';
import { AlbumCadastrarComponent } from './components/album/album-cadastrar-component/album-cadastrar-component';
import { AlbumEditarComponent } from './components/album/album-editar-component/album-editar-component';
import { AlbumDetalharComponent } from './components/album/album-detalhar-component/album-detalhar-component';
import { AlbumDetalharPage } from './pages/album/album-detalhar-page/album-detalhar-page';
import { AlbumCadastrarPage } from './pages/album/album-cadastrar-page/album-cadastrar-page';
import { AlbumEditarPage } from './pages/album/album-editar-page/album-editar-page';
import { Artista } from './pages/artista/artista';
import { ArtistaCadastrarPage } from './pages/artista/artista-cadastrar-page/artista-cadastrar-page';
import { ArtistaDetalharPage } from './pages/artista/artista-detalhar-page/artista-detalhar-page';
import { ArtistaEditarPage } from './pages/artista/artista-editar-page/artista-editar-page';
import { HeaderComponent } from './components/header-component/header-component';
import { FooterComponent } from './components/footer-component/footer-component';

@NgModule({
  declarations: [
    App,
    ArtistaComponent,
    ArtistaCadastrarComponent,
    ArtistaEditarComponent,
    ArtistaDetalharComponent,
    Album,
    AlbumCadastrarComponent,
    AlbumEditarComponent,
    AlbumDetalharComponent,
    AlbumDetalharPage,
    AlbumCadastrarPage,
    AlbumEditarPage,
    Artista,
    ArtistaCadastrarPage,
    ArtistaDetalharPage,
    ArtistaEditarPage,
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    provideBrowserGlobalErrorListeners(),
  ],
  bootstrap: [App]
})
export class AppModule { }
