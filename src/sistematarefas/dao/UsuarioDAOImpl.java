package sistematarefas.dao;

import sistematarefas.model.Departamento;
import sistematarefas.model.Usuario;
import sistematarefas.utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public int salvar(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, telefone, data_cadastro, id_endereco, ativo, id_departamento) VALUES (?,?,?,?,?,?,?)";
        try(Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) // Adicione Statement.RETURN_GENERATED_KEYS aqui
        {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTelefone());
            stmt.setDate(4, Date.valueOf(usuario.getDataCadastro())); //Transforma em data do SQL
            // verifica se endereço é nulo antes de salvar
            if (usuario.getEndereco() != null) {
                stmt.setInt(5, usuario.getEndereco().getIdEndereco());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }
            stmt.setBoolean(6, usuario.getAtivo());
            stmt.setInt(7, usuario.getDepartamento().getIdDepartamento());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idUsuario = generatedKeys.getInt(1);
                    usuario.setIdUsuario(idUsuario);
                    return idUsuario;
                } else {
                    throw new SQLException("Creating usuario failed, no ID obtained.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar usuário: " + e.getMessage(), e);
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

            // verifica se endereço é nulo antes de salvar
            if (usuario.getEndereco() != null) {
                stmt.setInt(5, usuario.getEndereco().getIdEndereco());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }
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
    public void deletar(Usuario usuario) {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, usuario.getIdUsuario());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario buscarPorCodigo(int idUsuario){
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            { //Na classe usuarioDAO temos o buscarPorCodigo que retorna o objeto, utilizamos o mesmo para pegar o id e retornar o objeto, fazemos o mesmo com departamento
                return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        new EnderecoDAOImpl().buscarPorCodigo(rs.getInt("id_endereco")),
                        rs.getBoolean("ativo"),
                        new DepartamentoDAOImpl().buscarPorCodigo(rs.getInt("id_departamento"))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Usuario> buscarTodos()
    {
        String sql = "SELECT * FROM usuario";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            List<Usuario> usuarios = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                Usuario usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        new EnderecoDAOImpl().buscarPorCodigo(rs.getInt("id_endereco")),
                        rs.getBoolean("ativo"),
                        new DepartamentoDAOImpl().buscarPorCodigo(rs.getInt("id_departamento"))
                );
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
