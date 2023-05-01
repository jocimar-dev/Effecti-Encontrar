package com.effecti.etapaencontrar.controller;

import com.effecti.etapaencontrar.model.ComprasNetData;
import com.effecti.etapaencontrar.model.ComprasNetStatusData;
import com.effecti.etapaencontrar.service.ComprasNetDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/effecti")
public class ComprasNetDataController {

    private final ComprasNetDataService service;

    @GetMapping("/listar")
    public ResponseEntity<List<ComprasNetData>> buscarTodasLicitacoes() {
        var status = service.buscaTodasLicitacoes();

        return Optional.ofNullable(status)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/busca-salva")
    public ResponseEntity<List<ComprasNetData>> buscaSalva() {
        service.buscaSalva();

        return Optional.ofNullable(service.buscaTodasLicitacoes())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/lida")
    public ResponseEntity<Void> atualizarStatusLida(@PathVariable Long id, @RequestParam("lida") ComprasNetStatusData status) {
        if (Boolean.FALSE.equals(status.getLidas())) {
            return ResponseEntity.badRequest().build();
        }
        service.mensagensLidas(id, true);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/index.html")
    public String paginaInicial() {
        return "index";
    }
}

