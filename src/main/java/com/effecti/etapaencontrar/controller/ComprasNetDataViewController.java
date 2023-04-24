package com.effecti.etapaencontrar.controller;

import com.effecti.etapaencontrar.model.ComprasNetData;
import com.effecti.etapaencontrar.service.ComprasNetDataService;
import com.effecti.etapaencontrar.service.exception.PaginaNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequiredArgsConstructor
public class ComprasNetDataViewController {

    private final ComprasNetDataService service;

    private ComprasNetData comprasnet;
    @GetMapping("/comprasnet")
    public String pesquisaEffecti(Model model) {
        try {
            var uri = new URI("http://comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacaoDia.asp");
            var document = Jsoup.connect(String.valueOf(uri)).get();

            var dataList = service.buscaLicitacao(document);

            if (dataList.isPresent()) {
                model.addAttribute("dataList", dataList.get());
                return "comprasnet";
            } else {
                model.addAttribute("erroRequisicao",
                        "Não foram encontrados dados de licitação.");
                return "error";
            }
        } catch (IOException e) {
            model.addAttribute("erroRequisicao",
                    "Erro ao conectar no site: " + e.getMessage());
            return "error";
        } catch (URISyntaxException e) {
            throw new PaginaNaoEncontradaException(e.getMessage());
        }
    }

    @PostMapping("/processar")
    public String processarFormulario(@ModelAttribute("formulario") ComprasNetData licitacao, Model model) {
        var selecaoLeitura = comprasnet.editalLido(licitacao);
        model.addAttribute("lidas", selecaoLeitura ?
                "Edital apreciado." : "Edital ainda não apreciado.");
        return "comprasnet";
    }

}

