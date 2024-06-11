package br.com.gerenciaautoeletrica.domain.entity.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "servicos_pecas")
public class ServicoPecaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer quantidade;

    @Column
    private Double precoPeca;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private ServicoEntity servico;

    @ManyToOne
    @JoinColumn(name = "peca_id")
    private PecaEntity peca;

}
