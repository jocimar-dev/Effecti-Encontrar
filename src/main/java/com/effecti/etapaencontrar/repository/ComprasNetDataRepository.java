package com.effecti.etapaencontrar.repository;

import com.effecti.etapaencontrar.model.ComprasNetData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unused")
    public interface ComprasNetDataRepository extends JpaRepository<ComprasNetData, Long> {
}