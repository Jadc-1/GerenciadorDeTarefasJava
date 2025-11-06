package sistematarefas.service;

import sistematarefas.dao.TarefaDAOImpl;
import sistematarefas.model.Tarefa;

import java.util.List;

public class TarefaService {
    private TarefaDAOImpl tarefaDAO;

    public TarefaService() {
        this.tarefaDAO = new TarefaDAOImpl();
    }

    public void salvarTarefa(Tarefa tarefa) throws Exception {
        if (tarefa.getTitulo() == null || tarefa.getTitulo().isBlank()) {
            throw new Exception("O título da tarefa é obrigatório.");
        }

        if (tarefa.getFuncionario() == null) {
            throw new Exception("A tarefa deve estar vinculada a um funcionário.");
        }

        if (tarefa.getProjeto() == null) {
            throw new Exception("A tarefa deve estar vinculada a um projeto.");
        }

        tarefaDAO.salvar(tarefa);
    }

    public void atualizarTarefa(Tarefa tarefa) throws Exception {
        if (tarefa.getIdTarefa() <= 0) {
            throw new Exception("ID de tarefa inválido para atualização.");
        }

        tarefaDAO.atualizar(tarefa);
    }

    public void deletarTarefa(Tarefa tarefa) throws Exception {
        if (tarefa == null || tarefa.getIdTarefa() <= 0) {
            throw new Exception("Tarefa inválida para exclusão.");
        }

        tarefaDAO.deletar(tarefa);
    }

    public Tarefa buscarPorCodigo(int idTarefa) throws Exception {
        if (idTarefa <= 0) {
            throw new Exception("Código de tarefa inválido.");
        }

        Tarefa tarefa = tarefaDAO.buscarPorCodigo(idTarefa);
        if (tarefa == null) {
            throw new Exception("Tarefa não encontrada.");
        }

        return tarefa;
    }

    public List<Tarefa> buscarTodas() {
        return tarefaDAO.buscarTodos();
    }
}