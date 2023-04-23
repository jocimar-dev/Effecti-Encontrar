package com.effecti.etapaencontrar.repository;

import com.effecti.etapaencontrar.model.ComprasNetData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ComprasNetDataRepository extends ElasticsearchRepository<ComprasNetData, String> {
}
