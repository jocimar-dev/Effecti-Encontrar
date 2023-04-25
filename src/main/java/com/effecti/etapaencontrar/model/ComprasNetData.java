package com.effecti.etapaencontrar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class ComprasNetData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String empresa;
    private String concorrencia;
    private String objeto;
    private String editalAPartirDe;
    private String endereco;
    private String telefone;
    private String fax;
    private String entregaProposta;
    private boolean lidas;
    private LocalDate dataLeitura;
}
