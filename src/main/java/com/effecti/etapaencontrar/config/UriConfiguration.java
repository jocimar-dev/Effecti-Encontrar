package com.effecti.etapaencontrar.config;

import org.springframework.context.annotation.Configuration;

@Configuration

public class UriConfiguration {
    public static final String COMPRASNET_URI =
            "http://comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacaoDia.asp";

    private UriConfiguration() {
    }

}
