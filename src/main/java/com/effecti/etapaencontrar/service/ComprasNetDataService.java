package com.effecti.etapaencontrar.service;

import com.effecti.etapaencontrar.model.ComprasNetData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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

    public Optional<List<ComprasNetData>> buscaLicitacao(Document file) throws IOException {


        var document = Jsoup.parse(file.toString());
        var tables = document.select("form table.td");
        document.outputSettings().charset("UTF-8");
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

        buscaLicitacao.setLidas(false);
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

    public StringBuilder builder() {

        var service = new ComprasNetDataService();

        Element table = new Element("table");
        table.select("tr.tex3").html();

        var data = service.buscaDadosSite(table);

        var builder = new StringBuilder();
        builder.append("Empresa: ").append(data.getEmpresa()).append("\n");
        builder.append("Concorrência: ").append(data.getConcorrencia()).append("\n");
        builder.append("Objeto: ").append(data.getObjeto()).append("\n");
        builder.append("Edital a partir de: ").append(data.getEditalAPartirDe()).append("\n");
        builder.append("Endereço: ").append(data.getEndereco()).append("\n");
        builder.append("Telefone: ").append(data.getTelefone()).append("\n");
        builder.append("Fax: ").append(data.getFax()).append("\n");
        builder.append("Entrega da proposta: ").append(data.getEntregaProposta()).append("\n");

        System.out.println(builder);
        return builder;
    }
}

