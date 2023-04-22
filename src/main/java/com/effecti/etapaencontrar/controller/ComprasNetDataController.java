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
public class ComprasNetDataController extends ComprasNetDataService {

    private final ComprasNetDataService service;

    @GetMapping("/comprasnet")
    public String pesquisaEffecti(Model model) throws IOException {
        var htmlFile = new File("sites/comprasnet_principal.html");
        var dataList = service.buscaLicitacao(htmlFile);
        model.addAttribute("dataList", dataList);
        return "comprasnet";
    }
}

