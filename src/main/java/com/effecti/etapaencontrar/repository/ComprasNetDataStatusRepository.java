package com.effecti.etapaencontrar.repository;

import com.effecti.etapaencontrar.model.ComprasNetStatusData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unused")
    public interface ComprasNetDataStatusRepository extends JpaRepository<ComprasNetStatusData, Long> {
}