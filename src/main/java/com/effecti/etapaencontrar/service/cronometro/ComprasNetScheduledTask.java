package com.effecti.etapaencontrar.service.cronometro;

import com.effecti.etapaencontrar.service.ComprasNetDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ComprasNetScheduledTask {

    private final ComprasNetDataService service;

    @Scheduled(fixedRate = 3600000) // Executa a cada 1 hora
    public void fetchAndSave() {
        service.buscaSalva();
    }
}

