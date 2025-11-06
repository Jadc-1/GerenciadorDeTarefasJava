package sistematarefas.controller;

import sistematarefas.model.Gestor;
import sistematarefas.service.GestorService;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class GestorController {

    private GestorService gestorService;

    public GestorController() {
        this.gestorService = new GestorService();
    }

    public void salvarGestor(Gestor gestor) {
        try {
            gestorService.salvarGestor(gestor);
            JOptionPane.showMessageDialog(null, "Gestor salvo com sucesso!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar gestor: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarGestor(Gestor gestor) {
        try {
            gestorService.atualizarGestor(gestor);
            JOptionPane.showMessageDialog(null, "Gestor atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar gestor: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deletarGestor(Gestor gestor) {
        try {
            gestorService.deletarGestor(gestor);
            JOptionPane.showMessageDialog(null, "Gestor deletado com sucesso!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar gestor: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Gestor buscarPorCodigo(int idGestor) {
        try {
            return gestorService.buscarPorCodigo(idGestor);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar gestor: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public List<Gestor> listarGestores() {
        try {
            return gestorService.buscarTodos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar gestores: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}