package com.effecti.etapaencontrar.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "COMPRAS_NET_DATA")
public class ComprasNetData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String numeroPregao;
    @Column(columnDefinition = "TEXT")
    private String concorrencia;
    @Column(columnDefinition = "TEXT")
    private String objeto;
    private String editalAPartirDe;
    private String endereco;
    private String telefone;
    private String fax;
    private String entregaProposta;

    @OneToOne(mappedBy = "comprasNetData", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "compras_net_data_status_id")
    private ComprasNetStatusData status;


}
