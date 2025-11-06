package sistematarefas.controller;

import sistematarefas.model.Usuario;
import sistematarefas.service.UsuarioService;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController() {
        this.usuarioService = new UsuarioService();
    }

    /**
     * Salva um novo usuário
     */
    public void salvarUsuario(Usuario usuario) {
        try {
            usuarioService.salvarUsuario(usuario);
            JOptionPane.showMessageDialog(null, "Usuário salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar usuário no banco de dados:\n" + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Atualiza um usuário existente
     */
    public void atualizarUsuario(Usuario usuario) {
        try {
            usuarioService.atualizarUsuario(usuario);
            JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar usuário:\n" + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Deleta um usuário
     */
    public void deletarUsuario(Usuario usuario) {
        try {
            int confirmacao = JOptionPane.showConfirmDialog(null,
                    "Deseja realmente excluir o usuário " + usuario.getNome() + "?",
                    "Confirmação", JOptionPane.YES_NO_OPTION);

            if (confirmacao == JOptionPane.YES_OPTION) {
                usuarioService.deletarUsuario(usuario);
                JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir usuário:\n" + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Busca um usuário pelo ID
     */
    public Usuario buscarPorId(int idUsuario) {
        try {
            Usuario usuario = usuarioService.buscarPorId(idUsuario);
            if (usuario == null) {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
            return usuario;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar usuário:\n" + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    /**
     * Lista todos os usuários
     */
    public List<Usuario> listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.listarUsuarios();
            if (usuarios == null || usuarios.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum usuário encontrado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
            return usuarios;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar usuários:\n" + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * Altera o status de ativo/inativo de um usuário
     */
    public void alterarStatusUsuario(int idUsuario, boolean ativo) {
        try {
            usuarioService.alterarStatusUsuario(idUsuario, ativo);
            JOptionPane.showMessageDialog(null, "Status do usuário atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar status do usuário:\n" + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE);
        }
    }
}
