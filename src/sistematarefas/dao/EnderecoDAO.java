package sistematarefas.dao;

import sistematarefas.model.Endereco;

import java.sql.SQLException;
import java.util.List;

public interface EnderecoDAO {
    void salvar(Endereco endereco) throws SQLException;
    void atualizar(Endereco endereco) throws SQLException;
    void deletar(Endereco endereco) throws SQLException;
    Endereco buscarPorCodigo(int idEndereco) throws SQLException;
    List<Endereco> buscarTodos() throws SQLException;
}
