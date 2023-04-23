package com.effecti.etapaencontrar.controller;

import com.effecti.etapaencontrar.service.ComprasNetDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ComprasNetDataViewController {

    private final ComprasNetDataService service;

    @GetMapping("/comprasnet")
    public String pesquisaEffecti(Model model) {
        try {
            var htmlFile = new File("sites/comprasnet_principal.html");
            var dataList = service.buscaLicitacao(htmlFile);
            if (dataList.isPresent()) {
                model.addAttribute("dataList", dataList.get());
                return "comprasnet";
            } else {
                model.addAttribute("erroRequisicao",
                        "Erro ao buscar dados de licitação.");
                return "error";
            }
        } catch (IOException e) {
            model.addAttribute("erroRequisicao",
                    "Erro ao conectar no site: " + e.getMessage());
            return "error";
        }
    }
}

