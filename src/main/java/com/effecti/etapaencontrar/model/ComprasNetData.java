package com.effecti.etapaencontrar.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(indexName = "compras_net_data")
public class ComprasNetData {
    @Id
    private String id;
    private String empresa;
    private String concorrencia;
    private String objeto;
    private String editalAPartirDe;
    private String endereco;
    private String telefone;
    private String fax;
    private String entregaProposta;
}
