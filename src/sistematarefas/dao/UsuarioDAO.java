package sistematarefas.dao;

import sistematarefas.model.Usuario;

public interface UsuarioDAO {
    void salvar(Usuario usuario);
    void atualizar(Usuario usuario);
    void deletar(int codigoUsuario);
    Usuario buscarPorCodigo(int codigoUsuario);
}
