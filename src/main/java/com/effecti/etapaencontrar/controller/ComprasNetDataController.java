package com.effecti.etapaencontrar.controller;

import com.effecti.etapaencontrar.service.ComprasNetDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/effecti")
public class ComprasNetDataController {

    private final ComprasNetDataService service;
    @GetMapping("/busca")
    public String pesquisaEffecti(Model model) {
        var dataList = service.busqueTodasLicitacoes();

        if (dataList.isPresent()) {
            model.addAttribute("dataList", dataList.get());
            return "comprasnet";
        } else {
            model.addAttribute("erroRequisicao",
                    "Não foram encontrados dados de licitação.");
            return "error";
        }
    }

    @PutMapping("/{id}/lida")
    public ResponseEntity<Void> atualizarStatusLida(@PathVariable Long id, @RequestBody Map<String, Boolean> body) {
        var lida = body.get("lida");
        if (lida == null) {
            return ResponseEntity.badRequest().build();
        }
        service.mensagensLidas(id, lida);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/detalhe/{id}")
    public String exibirDetalhes(@PathVariable("id") Long id, Model model) {
         var detalhado = service.busqueLicitacaoPorId(id);
            if (id != null) {
                model.addAttribute("detalhe", detalhado);
            } else {
                model.addAttribute("erroRequisicao",
                        "Não foram encontrados dados de licitação.");
                return "error";
            }

        return "detalhes"; // retorna o nome da página de detalhes
    }
}

