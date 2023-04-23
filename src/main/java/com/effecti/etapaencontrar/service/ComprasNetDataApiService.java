package com.effecti.etapaencontrar.service;

import com.effecti.etapaencontrar.repository.ComprasNetDataRepository;
import com.effecti.etapaencontrar.model.ComprasNetData;
import com.effecti.etapaencontrar.model.ViewData;
import com.effecti.etapaencontrar.service.exception.MensagemLidaException;
import com.effecti.etapaencontrar.service.exception.PaginaNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class ComprasNetDataApiService {

    private final ComprasNetDataRepository repository;
    private final Map<Long, ViewData> dataStorage = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public ComprasNetData salvaLicitacao(String id) {
        var data = repository.findById(id).orElse(null);
        if (data != null) {
            var viewData = new ViewData();
            viewData.setId(idCounter.incrementAndGet());
            viewData.setLida(false);
            dataStorage.put(viewData.getId(), viewData);
            return data;
        } else {
            throw new PaginaNaoEncontradaException("ID não encontrado");
        }
    }

    public void marcarComoLida(Long id, boolean lida) {
        var data = dataStorage.get(id);
        if (data != null) {
            data.setLida(lida);
        } else {
            throw new MensagemLidaException("ID não encontrado");
        }
    }
}
