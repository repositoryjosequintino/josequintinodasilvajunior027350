import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { App } from './app';
import { AppRoutingModule } from './app-routing-module';
import { FooterComponent } from './components/footer-component/footer-component';
import { HeaderComponent } from './components/header-component/header-component';
import { MaterialModule } from './material-module';
import { ArtistaCadastrarPage } from './pages/artista-page/artista-cadastrar-page/artista-cadastrar-page';
import { provideToastr } from 'ngx-toastr';

@NgModule({
  declarations: [
    App,
    HeaderComponent,
    FooterComponent,
    ArtistaCadastrarPage
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    ReactiveFormsModule
  ],
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideClientHydration(withEventReplay()),
    provideToastr(),
  ],
  bootstrap: [App]
})
export class AppModule { }
