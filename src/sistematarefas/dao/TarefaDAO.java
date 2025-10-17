package sistematarefas.dao;

import sistematarefas.model.Tarefa;

public interface TarefaDAO {
    void salvar (Tarefa tarefa);
    void atualizar (Tarefa tarefa);
    void deletar (Tarefa tarefa);
    Tarefa buscarPorCodigo (int codigoTarefa);
}
