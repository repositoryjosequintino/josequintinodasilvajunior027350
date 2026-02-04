import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ArtistaService } from '../../../services/artista-service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-artista-cadastrar-page',
  standalone: false,
  templateUrl: './artista-cadastrar-page.html',
  styleUrl: './artista-cadastrar-page.css',
})
export class ArtistaCadastrarPage {

  public formGroup: FormGroup;

  private formBuilder = inject(FormBuilder);

  private artistaService = inject(ArtistaService);

  private toastrService = inject(ToastrService);

    constructor() {
    this.formGroup = this.formBuilder.group({
      nome: ["", [Validators.required, Validators.minLength(3), Validators.maxLength(100)]]
    });
  }

  public create() {

    const artistaModel = this.formGroup.value;

    const artista = {
      nome: artistaModel.nome
    };

    this.artistaService.create(artista).subscribe({
      next: (response: any) => {},
      error: (error: any) => {
        this.toastrService.error("Erro ao tentar cadastrar o artista!");
      }
    });

    console.log();
  }

}
