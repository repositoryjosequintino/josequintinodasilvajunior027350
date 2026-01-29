package br.gov.mt.seplag.api.controller;

import java.util.UUID;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.mt.seplag.api.service.ArquivoService;
import br.gov.mt.seplag.api.transfer.ArquivoResponseTransfer;

@RestController
@RequestMapping("/api/v1/arquivo")
public class ArquivoController {

    private final ArquivoService arquivoService;

    public ArquivoController(ArquivoService arquivoService) {
        this.arquivoService = arquivoService;
    }

    @GetMapping("/{codePublic}")
    public ResponseEntity<InputStreamResource> download(@PathVariable UUID codePublic) {

        ArquivoResponseTransfer arquivoResponseTransfer = this.arquivoService.download(codePublic);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(arquivoResponseTransfer.getExtensao()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + arquivoResponseTransfer.getNome() + "\"")
                .body(new InputStreamResource(arquivoResponseTransfer.getInputStream()));
                
    }

}
