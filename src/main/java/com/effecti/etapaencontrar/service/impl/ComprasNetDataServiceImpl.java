package com.effecti.etapaencontrar.service.impl;

import com.effecti.etapaencontrar.model.ComprasNetData;
import com.effecti.etapaencontrar.repository.ComprasNetDataRepository;
import com.effecti.etapaencontrar.service.ComprasNetDataService;
import com.effecti.etapaencontrar.service.exception.MensagemLidaException;
import com.effecti.etapaencontrar.service.exception.PaginaNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.effecti.etapaencontrar.config.UriConfiguration.COMPRASNET_URI;
import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class ComprasNetDataServiceImpl implements ComprasNetDataService {

    private final ComprasNetDataRepository repository;

    private static final String REMOVE_ESPACO_VAZIO = "&nbsp;";

    public Optional<List<ComprasNetData>> busqueTodasLicitacoes() {
        try {
            var uri = new URI(COMPRASNET_URI);
            var document = Jsoup.connect(String.valueOf(uri)).get();
            var tables = document.select("form table.td");
            document.outputSettings().charset("UTF-8");
            List<ComprasNetData> comprasNetDataList = new ArrayList<>();

            for (var table : tables) {
                Optional<List<ComprasNetData>> optionalData = buscaDadosSite(table);
                optionalData.ifPresent(comprasNetDataList::addAll);
            }
            return Optional.of(comprasNetDataList);
        } catch (PaginaNaoEncontradaException | IOException e) {
            return Optional.empty();
        } catch (URISyntaxException e) {
            throw new PaginaNaoEncontradaException(e.getMessage());
        }
    }



    public Optional<List<ComprasNetData>> buscaDadosSite(Element table)
    {
        var row = table.select("tr.tex3").get(0);
        var buscaLicitacao = new ComprasNetData();
        var empresa = row.select("b").get(0).text();
        var concorrencia = row.select("b").get(1).text().trim();
        var objeto = requireNonNull(row.select("b").get(2).nextSibling()).toString().replaceFirst(REMOVE_ESPACO_VAZIO, "").replace("Objeto: ", "").trim();
        var editalAPartirDe = requireNonNull(row.select("b").get(3).nextSibling()).toString().replaceFirst(REMOVE_ESPACO_VAZIO, "").trim();
        var endereco = requireNonNull(row.select("b").get(4).nextSibling()).toString().replaceFirst(REMOVE_ESPACO_VAZIO, "").trim();
        var telefone = requireNonNull(row.select("b").get(5).nextSibling()).toString().replaceFirst(REMOVE_ESPACO_VAZIO, "").trim();
        var fax = requireNonNull(row.select("b").get(6).nextSibling()).toString().replaceFirst(REMOVE_ESPACO_VAZIO, "").trim();
        var entregaProposta = "";
        var elementos = row.select("b");
        if (elementos.size() > 7) {
            entregaProposta = requireNonNull(elementos.get(7).nextSibling()).toString().replaceFirst(REMOVE_ESPACO_VAZIO, "").trim();
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

        return Optional.of(List.of(buscaLicitacao));
    }

    @Override
    public void mensagensLidas(Long id, boolean lida) {
        var model = repository.findById(id)
                .orElseThrow(() -> new MensagemLidaException("Mensagem não encontrada"));
        model.setLidas(lida);
        if (!lida) {
            model.setDataLeitura(null);
        }
        repository.save(model);
    }

    @Override
    public ComprasNetData busqueLicitacaoPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PaginaNaoEncontradaException("Pagina não encontrada"));
    }



}

