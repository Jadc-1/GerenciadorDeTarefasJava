package sistematarefas.controller;

import sistematarefas.model.Funcionario;
import sistematarefas.service.FuncionarioService;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController() {
        this.funcionarioService = new FuncionarioService();
    }

    public void salvarFuncionario(Funcionario funcionario) {
        try {
            funcionarioService.salvar(funcionario);
            JOptionPane.showMessageDialog(null,
                    "Funcionário salvo com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao salvar funcionário no banco de dados: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarFuncionario(Funcionario funcionario) {
        try {
            funcionarioService.atualizar(funcionario);
            JOptionPane.showMessageDialog(null,
                    "Funcionário atualizado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao atualizar funcionário: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deletarFuncionario(Funcionario funcionario) {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Deseja realmente excluir o funcionário " + funcionario.getNome() + "?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                funcionarioService.deletar(funcionario);
                JOptionPane.showMessageDialog(null,
                        "Funcionário excluído com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null,
                        e.getMessage(),
                        "Atenção",
                        JOptionPane.WARNING_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Erro ao excluir funcionário: " + e.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Funcionario buscarPorCodigo(int idFuncionario) {
        try {
            Funcionario funcionario = funcionarioService.buscarPorCodigo(idFuncionario);
            if (funcionario == null) {
                JOptionPane.showMessageDialog(null,
                        "Nenhum funcionário encontrado com o código informado.",
                        "Informação",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            return funcionario;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao buscar funcionário: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public List<Funcionario> listarFuncionarios() {
        try {
            return funcionarioService.buscarTodos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar funcionários: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
