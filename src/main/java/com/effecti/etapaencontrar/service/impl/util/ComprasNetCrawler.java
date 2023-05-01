package com.effecti.etapaencontrar.service.impl.util;

import com.effecti.etapaencontrar.model.ComprasNetData;
import com.effecti.etapaencontrar.service.exception.PaginaNaoEncontradaException;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.requireNonNull;

@Component
@SuppressWarnings("unused")
public class ComprasNetCrawler {
    public static List<ComprasNetData> buscarDadosSite(Element conteudo) throws PaginaNaoEncontradaException {
        ComprasNetData pregao = new ComprasNetData();
        Element elementoPregao = selecionarElementoPregao(conteudo);
        for (Element elementosSite : elementoPregao.select("tr.tex3")) {
            pregao.setNumeroPregao(obterNumeroPregao(elementosSite));
            pregao.setObjeto(obterObjeto(elementosSite));
            pregao.setEditalAPartirDe(obterEditalAPartirDe(elementosSite));
            pregao.setEndereco(obterEndereco(elementosSite));
            pregao.setTelefone(obterTelefone(elementosSite));
            pregao.setFax(obterFax(elementosSite));
            pregao.setEntregaProposta(obterEntregaProposta(elementosSite));
        }
        return List.of(pregao);
    }


    protected static Element selecionarElementoPregao(Element conteudo) throws PaginaNaoEncontradaException {
        var conteudoRaspado = conteudo.selectFirst("tr.tex3");
        if (conteudoRaspado == null) {
            throw new PaginaNaoEncontradaException("Não foi possivel encontrar o conteudo da página");
        }
        return requireNonNull(conteudoRaspado.selectFirst("td"));
    }

    protected static String     obterNumeroPregao(Element elementoPregao) {
        var elementoNumeroPregao = elementoPregao.selectFirst("b");
        if (elementoNumeroPregao != null) {
            return elementoNumeroPregao.ownText();
        }
        return "Número do pregão não encontrado";
    }

    protected static String obterObjeto(Element elementoPregao) {
        var elementoObjeto = elementoPregao.selectFirst("b:contains(Objeto:)");
        if (elementoObjeto != null) {
            return requireNonNull(elementoObjeto.nextSibling()).toString().trim();
        }
        return "Objeto não encontrado";
    }

    protected static String obterEditalAPartirDe(Element elementoPregao) {
        var elementoEditalAPartirDe = elementoPregao.selectFirst("b:contains(Edital a partir de:)");
        if (elementoEditalAPartirDe != null) {
            return elementoEditalAPartirDe.nextSibling().toString().trim();
        }
        return "Não informado";
    }

    protected static String obterEndereco(Element elementoPregao) {
        var elementoEndereco = elementoPregao.selectFirst("b:contains(Endereço):");
        if (elementoEndereco != null) {
            return elementoEndereco.nextSibling().toString().trim();
        }
        return "Endereço não informado";
    }

    protected static String obterTelefone(Element elementoPregao) {
        var elementoTelefone = elementoPregao.selectFirst("b:contains(Telefone):");
        if (elementoTelefone != null) {
            return elementoTelefone.nextSibling().toString().trim();
        }
        return "Telefone não informado";
    }

    protected static String obterFax(Element elementoPregao) {
        var elementoFax = elementoPregao.selectFirst("b:contains(Fax):");
        if (elementoFax != null) {
            return elementoFax.nextSibling().toString().trim();
        }
        return "Endereço não informado";
    }

    protected static String obterEntregaProposta(Element elementoPregao) {
        var elementoEntregaProposta = elementoPregao.selectFirst("b:contains(Entrega da Proposta):");
        if (elementoEntregaProposta != null) {
            return elementoEntregaProposta.nextSibling().toString().trim();
        }
        return "Entrega da Proposta não informada";
    }

    public ComprasNetCrawler() {
    }

}
