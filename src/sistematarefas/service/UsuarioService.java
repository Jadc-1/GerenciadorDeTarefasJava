package sistematarefas.service;

import sistematarefas.dao.UsuarioDAO;
import sistematarefas.dao.UsuarioDAOImpl;
import sistematarefas.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAOImpl();
    }

    /**
     * Salva um novo usuário no banco de dados
     */
    public void salvarUsuario(Usuario usuario) throws SQLException {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }

        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do usuário é obrigatório");
        }

        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new IllegalArgumentException("E-mail do usuário é obrigatório");
        }

        usuarioDAO.salvar(usuario);
    }

    /**
     * Atualiza um usuário existente
     */
    public void atualizarUsuario(Usuario usuario) throws SQLException{
        if (usuario == null || usuario.getIdUsuario() <= 0) {
            throw new IllegalArgumentException("Usuário inválido para atualização");
        }

        usuarioDAO.atualizar(usuario);
    }

    /**
     * Remove um usuário do banco de dados
     */
    public void deletarUsuario(Usuario usuario) throws SQLException{
        if (usuario == null || usuario.getIdUsuario() <= 0) {
            throw new IllegalArgumentException("Usuário inválido para exclusão");
        }

        usuarioDAO.deletar(usuario);
    }

    /**
     * Busca um usuário pelo ID
     */
    public Usuario buscarPorId(int idUsuario) throws SQLException{
        if (idUsuario <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }

        return usuarioDAO.buscarPorCodigo(idUsuario);
    }

    /**
     * Lista todos os usuários
     */
    public List<Usuario> listarUsuarios() throws SQLException{
        return usuarioDAO.buscarTodos();
    }

    /**
     * Ativa ou desativa um usuário
     */
    public void alterarStatusUsuario(int idUsuario, boolean ativo) throws SQLException  {
        Usuario usuario = usuarioDAO.buscarPorCodigo(idUsuario);
        if (usuario != null) {
            usuario.setAtivo(ativo);
            usuarioDAO.atualizar(usuario);
        } else {
            throw new IllegalArgumentException("Usuário não encontrado para alterar status");
        }
    }
}