package sistematarefas.dao;

import sistematarefas.model.Departamento;
import sistematarefas.model.Usuario;
import sistematarefas.utils.Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, telefone, data_cadastro, id_endereco, ativo, id_departamento) VALUES (?,?,?,?,?,?,?)";
        try(Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) // Faço conexão com o banco de dados e envio o INSERT
        {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTelefone());
            stmt.setDate(4, Date.valueOf(usuario.getDataCadastro())); //Transforma em data do SQL
            stmt.setInt(5, usuario.getEndereco().getIdEndereco());
            stmt.setBoolean(6, usuario.getAtivo());
            stmt.setInt(7, usuario.getDepartamento().getIdDepartamento());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Usuario usuario)
    {
        String sql = "UPDATE usuario SET nome = ?, email = ?, telefone = ?, data_cadastro = ?, id_endereco = ?, ativo = ?, id_departamento = ? WHERE id_usuario = ?";
        try(Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTelefone());
            stmt.setDate(4, Date.valueOf(usuario.getDataCadastro()));
            stmt.setInt(5, usuario.getEndereco().getIdEndereco());
            stmt.setBoolean(6, usuario.getAtivo());
            stmt.setInt(7, usuario.getDepartamento().getIdDepartamento());
            stmt.setInt(8, usuario.getIdUsuario());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(int idDepartamento) {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, idDepartamento);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario buscarPorCodigo(int codigoUsuario){
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, codigoUsuario);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            { //Na classe usuarioDAO temos o buscarPorCodigo que retorna o objeto, utilizamos o mesmo para pegar o id e retornar o objeto, fazemos o mesmo com departamento
                Usuario usuario = new Usuario(rs.getInt("id_usuario"), rs.getString("nome"), rs.getString("email"), rs.getString("telefone"), rs.getDate("data_cadastro").toLocalDate(), new EnderecoDAOImpl().buscarPorCodigo(rs.getInt("id_endereco")), rs.getBoolean("ativo"), new DepartamentoDAOImpl().buscarPorCodigo(rs.getInt("id_departamento")));
                return usuario;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
