package com.effecti.etapaencontrar.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ComprasNetData {
    private String empresa;
    private String concorrencia;
    private String objeto;
    private String editalAPartirDe;
    private String endereco;
    private String telefone;
    private String fax;
    private String entregaDaProposta;

}
