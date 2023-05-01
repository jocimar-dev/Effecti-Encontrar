package com.effecti.etapaencontrar.service;

import com.effecti.etapaencontrar.model.ComprasNetData;

import java.util.List;

@SuppressWarnings("unused")
public interface ComprasNetDataService  {

   void mensagensLidas(Long id, Boolean status);
    void buscaSalva();

    List<ComprasNetData> buscaTodasLicitacoes();
}

