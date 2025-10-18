package sistematarefas.dao;

import sistematarefas.model.Funcionario;

import java.sql.SQLException;
import java.util.List;

public interface FuncionarioDAO {
    void salvar(Funcionario funcionario) throws SQLException;
    void atualizar(Funcionario funcionario) throws SQLException;
    void deletar(Funcionario funcionario) throws SQLException;
    Funcionario buscarPorCodigo(int idFuncionario) throws SQLException;
    List<Funcionario> buscarTodos() throws SQLException;
}
