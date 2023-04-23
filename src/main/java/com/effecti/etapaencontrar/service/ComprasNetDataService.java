package com.effecti.etapaencontrar.service;

import com.effecti.etapaencontrar.model.ComprasNetData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
public class ComprasNetDataService {

    private static final String NON_BREAKING_SPACE = "&nbsp;";

    public Optional<List<ComprasNetData>>buscaLicitacao(File htmlFile) throws IOException {

        var document = Jsoup.parse(htmlFile, "UTF-8");
        var doc = Jsoup.parse(document.html());
        var tables = doc.select("form table.td");

        List<ComprasNetData> comprasNetDataList = new ArrayList<>();

        for (var table : tables) {
            comprasNetDataList.add(buscaDadosSite(table));
        }
        return Optional.of(comprasNetDataList);
    }

    public ComprasNetData buscaDadosSite(Element table) {
        var row = table.select("tr.tex3").get(0);
        var buscaLicitacao = new ComprasNetData();
        var empresa = row.select("b").get(0).text();
        var concorrencia = row.select("b").get(1).text().trim();
        var objeto = requireNonNull(row.select("b").get(2).nextSibling()).toString().replaceFirst(NON_BREAKING_SPACE, "").replace("Objeto: ", "").trim();
        var editalAPartirDe = requireNonNull(row.select("b").get(3).nextSibling()).toString().replaceFirst(NON_BREAKING_SPACE, "").trim();
        var endereco = requireNonNull(row.select("b").get(4).nextSibling()).toString().replaceFirst(NON_BREAKING_SPACE, "").trim();
        var telefone = requireNonNull(row.select("b").get(5).nextSibling()).toString().replaceFirst(NON_BREAKING_SPACE, "").trim();
        var fax = requireNonNull(row.select("b").get(6).nextSibling()).toString().replaceFirst(NON_BREAKING_SPACE, "").trim();
        var entregaProposta = "";
        var elementos = row.select("b");
        if (elementos.size() > 7) {
            entregaProposta = requireNonNull(elementos.get(7).nextSibling()).toString().replaceFirst(NON_BREAKING_SPACE, "").trim();
        }

        buscaLicitacao.setEmpresa(empresa);
        buscaLicitacao.setConcorrencia(concorrencia);
        buscaLicitacao.setObjeto(objeto);
        buscaLicitacao.setEditalAPartirDe(editalAPartirDe);
        buscaLicitacao.setEndereco(endereco);
        buscaLicitacao.setTelefone(telefone);
        buscaLicitacao.setFax(fax);
        buscaLicitacao.setEntregaProposta(entregaProposta);

        return buscaLicitacao;
    }
}

