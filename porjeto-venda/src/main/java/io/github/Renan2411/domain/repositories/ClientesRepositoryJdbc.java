package io.github.Renan2411.domain.repositories;

import io.github.Renan2411.domain.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
//O spring entende que voce faz operações na base de dados, e as exceptions que ocorrem na base de dados serão traduzidas
public class ClientesRepositoryJdbc {

    //Criando sql de inserção padrão para a tabela CLIENTE
    private final static String INSERT = "INSERT INTO CLIENTE (NOME) VALUES (?)";
    private final static String SELECT_ALL = "SELECT * FROM CLIENTE";
    private final static String UPDATE = "UPDATE CLIENTE SET nome = ? where id = ?";
    private final static String DELETE = "DELETE FROM CLIENTE WHERE ID = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate; //Já traz a conexão pronta para usarmos

    public List<Cliente> buscarTodos() {
        return jdbcTemplate.query(SELECT_ALL, getClienteRowMapper());
    }

    public List<Cliente> buscarPorNome(String nome) {
        return jdbcTemplate.query(
                SELECT_ALL.concat(" where nome like ?"),
                new Object[]{'%' + nome + '%'},
                getClienteRowMapper());
    }

    public Cliente editar(Cliente cliente) {
        jdbcTemplate.update(UPDATE, new Object[]{cliente.getNome(), cliente.getId()});
        return cliente;
    }

    public void excluir(Cliente cliente) {
        jdbcTemplate.update(DELETE, new Object[]{cliente.getId()});
    }

    public Cliente salvar(Cliente cliente) {
        //Aqui estão realizando uma inserção no bando, usando o sql criado em cima, e como segundo parâmetro, um array de objetos constendo os valores que serão iseridos
        //O valor substitui o (?) do sql criado
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    private static RowMapper<Cliente> getClienteRowMapper() {
        return new RowMapper<Cliente>() {
            @Override
            //vai maperar cada linha do retorno da query e fazer uma conversão
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                String name = resultSet.getString("nome");
                Integer id = resultSet.getInt("id");
                return new Cliente(id, name);
            }
        };
    }
}
