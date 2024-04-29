package io.github.Renan2411.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //Indica para escanear como uma entidade do BANCO
@Table  //@Table(name = "tb_nome_table", schema = "schema") -> Indica que é uma tabela no banco. Não precisa quando os nomes são iguai; vincula como uma tabela no BD
public class Cliente {

    @Id //Indica que é a chave primária
    @GeneratedValue(strategy = GenerationType.AUTO) //Indica que é um valor gerado, a estratégia indica como será gerado esse valor;
    @Column(name = "id") //Faz o vinculo com a coluna na tabela. Não tem necessidade do (name = "") quando os nomes são iguais
    private Integer id;

    @Column(name = "nome", length = 100) //Não há necessidade dessa annotation, pois o @Entity entender que todas as props são colunas da tabela
    private String nome;

    public Cliente() {
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(Integer id,String nome) {
        this.nome = nome;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", name='" + nome + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }
}
