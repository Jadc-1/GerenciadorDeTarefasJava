package sistematarefas.controller;

import sistematarefas.model.Projeto;
import sistematarefas.service.ProjetoService;

import javax.swing.*;
import java.util.List;

public class ProjetoController {

    private ProjetoService projetoService;

    public ProjetoController() {
        this.projetoService = new ProjetoService();
    }

    public void salvarProjeto(Projeto projeto) {
        try {
            projetoService.salvarProjeto(projeto);
            JOptionPane.showMessageDialog(null, "Projeto salvo com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar projeto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarProjeto(Projeto projeto) {
        try {
            projetoService.atualizarProjeto(projeto);
            JOptionPane.showMessageDialog(null, "Projeto atualizado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar projeto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deletarProjeto(Projeto projeto) {
        try {
            projetoService.deletarProjeto(projeto);
            JOptionPane.showMessageDialog(null, "Projeto excluído com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir projeto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Projeto buscarPorCodigo(int idProjeto) {
        try {
            return projetoService.buscarPorCodigo(idProjeto);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar projeto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public List<Projeto> listarProjetos() {
        try {
            return projetoService.buscarTodos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar projetos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}