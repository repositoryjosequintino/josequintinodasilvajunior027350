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
    AlbumDetalharComponent
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
