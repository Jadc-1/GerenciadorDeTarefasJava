package sistematarefas.dao;

import sistematarefas.model.Tarefa;

import java.sql.SQLException;
import java.util.List;

public interface TarefaDAO {
    void salvar (Tarefa tarefa) throws SQLException;
    void atualizar (Tarefa tarefa) throws SQLException;
    void deletar (Tarefa tarefa) throws SQLException;
    Tarefa buscarPorCodigo (int idTarefa) throws SQLException;
    List<Tarefa> buscarTodos() throws SQLException;
}
