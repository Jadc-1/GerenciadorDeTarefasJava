package sistematarefas.controller;

import sistematarefas.model.Departamento;
import sistematarefas.service.DepartamentoService;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController() {
        this.departamentoService = new DepartamentoService();
    }

    public void salvarDepartamento(Departamento departamento) {
        try {
            departamentoService.salvar(departamento);
            JOptionPane.showMessageDialog(null,
                    "Departamento salvo com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao salvar departamento: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarDepartamento(Departamento departamento) {
        try {
            departamentoService.atualizar(departamento);
            JOptionPane.showMessageDialog(null,
                    "Departamento atualizado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao atualizar departamento: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deletarDepartamento(Departamento departamento) {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Deseja realmente excluir o departamento " + departamento.getNomeDepartamento() + "?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                departamentoService.deletar(departamento);
                JOptionPane.showMessageDialog(null,
                        "Departamento excluído com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null,
                        e.getMessage(),
                        "Atenção",
                        JOptionPane.WARNING_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Erro ao excluir departamento: " + e.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Departamento buscarPorCodigo(int idDepartamento) {
        try {
            Departamento departamento = departamentoService.buscarPorCodigo(idDepartamento);
            if (departamento == null) {
                JOptionPane.showMessageDialog(null,
                        "Nenhum departamento encontrado com o código informado.",
                        "Informação",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            return departamento;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao buscar departamento: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public List<Departamento> listarDepartamentos() {
        try {
            return departamentoService.buscarTodos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar departamentos: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}