package sistematarefas.controller;

import sistematarefas.model.Tarefa;
import sistematarefas.service.TarefaService;

import javax.swing.*;
import java.util.List;

public class TarefaController {

    private TarefaService tarefaService;

    public TarefaController() {
        this.tarefaService = new TarefaService();
    }

    public void salvarTarefa(Tarefa tarefa) {
        try {
            tarefaService.salvarTarefa(tarefa);
            JOptionPane.showMessageDialog(null, "Tarefa salva com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar tarefa: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarTarefa(Tarefa tarefa) {
        try {
            tarefaService.atualizarTarefa(tarefa);
            JOptionPane.showMessageDialog(null, "Tarefa atualizada com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar tarefa: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deletarTarefa(Tarefa tarefa) {
        try {
            tarefaService.deletarTarefa(tarefa);
            JOptionPane.showMessageDialog(null, "Tarefa excluída com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir tarefa: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Tarefa buscarPorCodigo(int idTarefa) {
        try {
            return tarefaService.buscarPorCodigo(idTarefa);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar tarefa: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public List<Tarefa> listarTarefas() {
        try {
            return tarefaService.buscarTodas();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar tarefas: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}