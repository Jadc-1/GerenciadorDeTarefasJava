package sistematarefas.dao;

import sistematarefas.model.Projeto;

import java.sql.SQLException;
import java.util.List;

public interface ProjetoDAO {
    void salvar(Projeto projeto) throws SQLException;
    void atualizar(Projeto projeto) throws SQLException;
    void deletar(Projeto projeto) throws SQLException;
    Projeto buscarPorCodigo(int idProjeto) throws SQLException;
    List<Projeto> buscarTodos() throws SQLException;
}
