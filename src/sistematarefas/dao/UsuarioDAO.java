package sistematarefas.dao;

import sistematarefas.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO {
    int salvar(Usuario usuario) throws SQLException;
    void atualizar(Usuario usuario) throws SQLException;
    void deletar(Usuario usuario) throws SQLException;
    Usuario buscarPorCodigo(int idUsuario) throws SQLException;
    List<Usuario> buscarTodos() throws SQLException;
}
