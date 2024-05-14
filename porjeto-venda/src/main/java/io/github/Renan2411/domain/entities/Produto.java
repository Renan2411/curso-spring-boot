package io.github.Renan2411.domain.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column()
    @NotEmpty(message = "{campo.codigo-cliente.obrigatorio}")
    private String descricao;

    @Column(name = "{campo.preco.obrigatorio}")
    @NotNull(message = "Preço é obrigatório")
    private BigDecimal preco;
}
