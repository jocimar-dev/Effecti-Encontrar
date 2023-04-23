package com.effecti.etapaencontrar.controller;

import com.effecti.etapaencontrar.service.ComprasNetDataApiService;
import com.effecti.etapaencontrar.service.ComprasNetDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ComprasNetDataController {

    private final ComprasNetDataApiService service;

    @PutMapping("/{id}/lida")
    public ResponseEntity<ComprasNetDataApiService> marcarComoLida(
            @PathVariable("id") Long id, @RequestParam("lida") boolean lida) {
        service.marcarComoLida(id, lida);
        return Optional.of(service).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }
}


