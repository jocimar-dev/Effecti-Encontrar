package com.effecti.etapaencontrar.service;

import com.effecti.etapaencontrar.model.ComprasNetData;

import java.util.List;
import java.util.Optional;

public interface ComprasNetDataService {
    Optional<List<ComprasNetData>> busqueTodasLicitacoes();

    void mensagensLidas(Long id, boolean msgLida);

    ComprasNetData busqueLicitacaoPorId(Long id);
}
