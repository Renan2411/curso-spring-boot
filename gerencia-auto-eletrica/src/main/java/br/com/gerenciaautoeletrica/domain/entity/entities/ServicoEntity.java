package br.com.gerenciaautoeletrica.domain.entity.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "servicos")
public class ServicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Boolean pago;

    @Column
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private ClienteEntity cliente;

    @Column
    private Double valorServico;

    @Column
    private Double valorTotal;

}
