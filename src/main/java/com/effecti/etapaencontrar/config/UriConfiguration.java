package com.effecti.etapaencontrar.config;

import org.jetbrains.annotations.Contract;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("unused")
public class UriConfiguration {
    public static final String COMPRASNET_URI =
            "http://comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacaoDia.asp";
    public static final String COMPRASNET_URI2 = "https://jocimar-dev.github.io/comprasnet-tesx/";



    @Contract(pure = true)
    public UriConfiguration() {
    }

}
