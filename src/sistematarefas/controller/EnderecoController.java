package sistematarefas.controller;

import sistematarefas.model.Endereco;
import sistematarefas.service.EnderecoService;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController() {
        this.enderecoService = new EnderecoService();
    }

    public void salvarEndereco(Endereco endereco) {
        try {
            enderecoService.salvar(endereco);
            JOptionPane.showMessageDialog(null,
                    "Endereço salvo com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao salvar endereço: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarEndereco(Endereco endereco) {
        try {
            enderecoService.atualizar(endereco);
            JOptionPane.showMessageDialog(null,
                    "Endereço atualizado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao atualizar endereço: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deletarEndereco(Endereco endereco) {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Deseja realmente excluir este endereço?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                enderecoService.deletar(endereco);
                JOptionPane.showMessageDialog(null,
                        "Endereço excluído com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null,
                        e.getMessage(),
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Erro ao excluir endereço: " + e.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Endereco buscarPorCodigo(int idEndereco) {
        try {
            Endereco endereco = enderecoService.buscarPorCodigo(idEndereco);
            if (endereco == null) {
                JOptionPane.showMessageDialog(null,
                        "Nenhum endereço encontrado com o código informado.",
                        "Informação",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            return endereco;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao buscar endereço: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public List<Endereco> listarEnderecos() {
        try {
            return enderecoService.buscarTodos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar endereços: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}