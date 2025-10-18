package sistematarefas.dao;

import sistematarefas.model.Gestor;

import java.sql.SQLException;
import java.util.List;

public interface GestorDAO {
    void salvar(Gestor gestor) throws SQLException;
    void atualizar(Gestor gestor) throws SQLException;
    void deletar(Gestor gestor) throws SQLException;
    Gestor buscarPorCodigo(int idGestor) throws SQLException;
    List<Gestor> buscarTodos() throws SQLException;
}
