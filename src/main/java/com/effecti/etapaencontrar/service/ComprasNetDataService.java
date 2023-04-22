package com.effecti.etapaencontrar.service;

import com.effecti.etapaencontrar.model.ComprasNetData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Service
public class ComprasNetDataService {

    private static final String NON_BREAKING_SPACE = "&nbsp;";

    public List<ComprasNetData> buscaLicitacao(File htmlFile) throws IOException {
        var document = Jsoup.parse(htmlFile, "UTF-8");
        var tables = document.select("form table.td");
        List<ComprasNetData> comprasNetDataList = new ArrayList<>();

        for (var table : tables) {
            comprasNetDataList.add(buscaDadosSite(table));
            comprasNetDataList.add(buscaDadosSite(tables.get(0)));
        }
        return comprasNetDataList;
    }

    private ComprasNetData buscaDadosSite(Element table) {
        var conteudo = table.select("tr.tex3").get(0);

        var buscaLicitacao = new ComprasNetData();
        var empresa = conteudo.select("b").get(0).text();

        var concorrencia = conteudo.select("b").get(1).text().trim();
        var objeto = requireNonNull(conteudo.select("b").get(2).nextSibling()).toString().replaceFirst(NON_BREAKING_SPACE, "").replace("Objeto: ", "").trim();
        var editalAPartirDe = requireNonNull(conteudo.select("b").get(3).nextSibling()).toString().replaceFirst(NON_BREAKING_SPACE, "").trim();
        var endereco = requireNonNull(conteudo.select("b").get(4).nextSibling()).toString().replaceFirst(NON_BREAKING_SPACE, "").trim();
        var telefone = requireNonNull(conteudo.select("b").get(5).nextSibling()).toString().replaceFirst(NON_BREAKING_SPACE, "").trim();
        var fax = requireNonNull(conteudo.select("b").get(6).nextSibling()).toString().replaceFirst(NON_BREAKING_SPACE, "").trim();
        var entregaDaProposta = requireNonNull(conteudo.select("b").get(7).nextSibling()).toString().replaceFirst(NON_BREAKING_SPACE, "").trim();

        buscaLicitacao.setEmpresa(empresa);
        buscaLicitacao.setConcorrencia(concorrencia);
        buscaLicitacao.setObjeto(objeto);
        buscaLicitacao.setEditalAPartirDe(editalAPartirDe);
        buscaLicitacao.setEndereco(endereco);
        buscaLicitacao.setTelefone(telefone);
        buscaLicitacao.setFax(fax);
        buscaLicitacao.setEntregaDaProposta(entregaDaProposta);

        return buscaLicitacao;
    }
}

