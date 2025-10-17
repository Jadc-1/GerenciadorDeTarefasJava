package sistematarefas.dao;

import sistematarefas.model.Funcionario;
import sistematarefas.model.Gestor;
import sistematarefas.model.Usuario;
import sistematarefas.utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorDAOImpl implements GestorDAO {
    @Override
    public void salvar (Gestor gestor){
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        usuarioDAO.salvar(gestor);

        String sql = "INSERT INTO gestor (id_gestor, setor) VALUES (?, ?)";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, gestor.getIdGestor());
            stmt.setString(2, gestor.getSetor());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar (Gestor gestor){
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        usuarioDAO.atualizar(gestor);

        String sql = "UPDATE gestor SET setor = ? WHERE id_gestor = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString( 1, gestor.getSetor());
            stmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar (int idGestor){
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        usuarioDAO.deletar(idGestor);

        String sql = "DELETE FROM gestor WHERE id_gestor = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, idGestor);
            stmt.executeUpdate();
        } catch (Exception e) {
        e.printStackTrace();
        }
    }

    @Override
    public Gestor buscarPorCodigo (int codigoGestor) {
        String sql = "SELECT g.id_gestor, g.setor, g.id_funcionario, u.nome, u.email, u.telefone, u.data_cadastro, u.id_endereco, u.ativo, u.id_departamento, u.id_usuario" +
                     "FROM gestor g INNER JOIN usuario u ON g.id_usuario = u.id_usuario WHERE g.id_gestor = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, codigoGestor);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return new Funcionario(rs.getInt( "id_gestor"), rs.getString( "setor"), rs.getInt( "id_funcionario"), rs.getInt("id_usuario"), rs.getString("nome"), rs.getString("email"), rs.getString("telefone"), rs.getDate("data_cadastro").toLocalDate(), new EnderecoDAOImpl().buscarPorCodigo(rs.getInt("id_endereco")), rs.getBoolean("ativo"), new DepartamentoDAOImpl().buscarPorCodigo(rs.getInt("id_departamento")));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
