package sistematarefas.dao;

import sistematarefas.model.Departamento;
import sistematarefas.model.Endereco;

import java.sql.SQLException;
import java.util.List;

public interface DepartamentoDAO {
    void salvar(Departamento departamento) throws SQLException;
    void atualizar(Departamento departamento) throws SQLException;
    void deletar(Departamento Departamento) throws SQLException;
    Departamento buscarPorCodigo(int idDepartamento) throws SQLException;
    List<Departamento> buscarTodos() throws SQLException;
}
