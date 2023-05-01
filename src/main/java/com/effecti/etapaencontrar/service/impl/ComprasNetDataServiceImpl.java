package com.effecti.etapaencontrar.service.impl;

import com.effecti.etapaencontrar.model.ComprasNetData;
import com.effecti.etapaencontrar.repository.ComprasNetDataRepository;
import com.effecti.etapaencontrar.repository.ComprasNetDataStatusRepository;
import com.effecti.etapaencontrar.service.ComprasNetDataService;
import com.effecti.etapaencontrar.service.exception.MensagemLidaException;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static com.effecti.etapaencontrar.config.UriConfiguration.COMPRASNET_URI2;
import static com.effecti.etapaencontrar.service.impl.util.ComprasNetCrawler.buscarDadosSite;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ComprasNetDataServiceImpl implements ComprasNetDataService {

    private final ComprasNetDataRepository repository;
    private final ComprasNetDataStatusRepository statusRepository;

    @Override
    public void buscaSalva() {

        try {
            Document document = Jsoup.connect(COMPRASNET_URI2).get();
            Element table = document.selectFirst("table");
            if (table == null) { return;}
            List<ComprasNetData> comprasNetDataList = buscarDadosSite(table);
            salvarLicitacoes(comprasNetDataList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ComprasNetData> buscaTodasLicitacoes() {
        return repository.findAll();
    }

    private void salvarLicitacoes(List<ComprasNetData> comprasNetDataList) {
        repository.saveAll(comprasNetDataList);
    }

    @Override
    public void mensagensLidas(Long id, Boolean status) {
        var model = statusRepository.findById(id)
                .orElseThrow(() -> new MensagemLidaException("Mensagem não encontrada"));
        model.setId(id);
        model.setLidas(status);
        model.setDataLeitura(LocalDateTime.now());
        statusRepository.save(model);
    }
}