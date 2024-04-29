package io.github.Renan2411.domain.repositories;

import io.github.Renan2411.domain.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
//O spring entende que voce faz operações na base de dados, e as exceptions que ocorrem na base de dados serão traduzidas
public class ClientesRepositoryEntityManager {

    @Autowired
    private EntityManager entityManager; //Já traz a conexão pronta para usarmos

    @Transactional
    public List<Cliente> buscarTodos() {
        return entityManager
                .createQuery("from Cliente", Cliente.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Cliente buscarPorId(Integer id) {
        return entityManager.find(Cliente.class, id);
    }

    @Transactional(readOnly = true)
    //Aqui indica que é apenas uma busca para leitura, sem necessidade de cache, o que torna a pesquisa um pouco mais rapida
    public List<Cliente> buscarPorNome(String nome) {
        String jpql = "select c from Cliente c where c.nome like :nome";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class).setParameter("nome", nome);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

    @Transactional
    public Cliente editar(Cliente cliente) {
        //Aqui ele primeiro atualiza a entidade do cache, e depois manda para o BD, fazendo sua atualização
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    public void excluir(Cliente cliente) {
        //Se não estiver sincronizado com o entityManager
        if(!entityManager.contains(cliente)){
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }

    @Transactional //Ao fazer uma transação, se der algum erro, ele faz o rollback
    //Com JPA é necessário usar essa annotation
    //As entidades JPA possuem um estado. Antes de serem salvas, elas são transientes, passando para o estado manager (gerenciada) após persistidas, ficando no cache;
    public Cliente salvar(Cliente cliente) {
        entityManager.persist(cliente);
        return cliente;
    }
}

