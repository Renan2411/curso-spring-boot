package io.github.Renan2411.domain.repositories;

import io.github.Renan2411.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//Interface que extende o JpaRepository, que utiliza QueryMethods
//Os queryMethods são métodos, que, ao serem nomeados seguindo uma convenção, se tranformam em querys sql
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    //Os argumentos devem ser as propriedades da classe Cliente para que os queryMethods consigam linkar o sql corretamente
    //Os argumentos devem seguir a ordem declarada nos querys methods, por exemplo, findByNomeOrId, o nome deve vir primeiro, seguido do id

    //essa definição => select c from Cliente c where c.nome like nome
    //Em tempo de execuação ele realiza a busca com sql;
    public List<Cliente> findByNomeLike(String nome);

    public List<Cliente> findByNomeOrId(String nome, Integer id);

    public Cliente findOneByNome(String nome);

    public boolean existsByNome(String nome);
}
