package io.github.Renan2411.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity //Indica para escanear como uma entidade do BANCO
@Table
@Getter
@Setter
//@Table(name = "tb_nome_table", schema = "schema") -> Indica que é uma tabela no banco. Não precisa quando os nomes são iguai; vincula como uma tabela no BD
public class Cliente {

    @Id //Indica que é a chave primária
    @GeneratedValue(strategy = GenerationType.AUTO)
    //Indica que é um valor gerado, a estratégia indica como será gerado esse valor;
    @Column(name = "id")
    //Faz o vinculo com a coluna na tabela. Não tem necessidade do (name = "") quando os nomes são iguais
    private Integer id;

    @Column(name = "nome", length = 100)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    //Não há necessidade dessa annotation, pois o @Entity entender que todas as props são colunas da tabela
    private String nome;

    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    @Column(length = 11)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY) //Indica um relacionamento Um (Classe atual) para muitos (classe destino)
    private Set<Pedido> pedidos;

    public Cliente() {
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(Integer id, String nome) {
        this.nome = nome;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", pedidos=" + pedidos +
                '}';
    }
}
