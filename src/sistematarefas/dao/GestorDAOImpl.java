package sistematarefas.dao;

import sistematarefas.model.*;
import sistematarefas.utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class GestorDAOImpl implements GestorDAO {
    @Override
    public void salvar (Gestor gestor) throws SQLException{
        FuncionarioDAO funcionarioDAO = new FuncionarioDAOImpl();
        funcionarioDAO.salvar(gestor);

        String sql = "INSERT INTO gestor (id_gestor, setor) VALUES (?, ?)";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, gestor.getIdGestor());
            stmt.setString(2, gestor.getSetor());
            stmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar (Gestor gestor) throws SQLException{
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        usuarioDAO.atualizar(gestor);

        String sql = "UPDATE gestor SET setor = ? WHERE id_gestor = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString( 1, gestor.getSetor());
            stmt.setInt( 2, gestor.getIdGestor());
            stmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar (Gestor gestor) throws SQLException{
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        usuarioDAO.deletar(gestor);

        String sql = "DELETE FROM gestor WHERE id_gestor = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, gestor.getIdGestor());
            stmt.executeUpdate();
        } catch (Exception e) {
        e.printStackTrace();
        }
    }

    @Override
    public Gestor buscarPorCodigo (int idGestor) {
        String sql = "SELECT g.id_gestor, g.setor, g.id_funcionario, u.nome, u.email, u.telefone, u.data_cadastro, u.id_endereco, u.ativo, u.id_departamento, u.id_usuario, f.salario" +
                     "FROM gestor g " +
                    "INNER JOIN funcionario f ON g.id_funcionario = f.id_funcionario" +
                    "INNER JOIN usuario u ON f.id_usuario = u.id_usuario " +
                    "WHERE g.id_gestor = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, idGestor);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Endereco endereco = new EnderecoDAOImpl().buscarPorCodigo(rs.getInt("id_endereco"));
                Departamento departamento = new DepartamentoDAOImpl().buscarPorCodigo(rs.getInt("id_departamento"));

                return new Gestor(
                        rs.getInt("id_gestor"),
                        rs.getString("setor"),
                        rs.getInt("id_funcionario"),
                        rs.getDouble("salario"),
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getDate("data_cadastro").toLocalDate(),
                        endereco,
                        rs.getBoolean("ativo"),
                        departamento
                );
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Gestor> buscarTodos()
    {
        String sql = "SELECT g.id_gestor, g.setor, g.id_funcionario, u.nome, u.email, u.telefone, u.data_cadastro, u.id_endereco, u.ativo, u.id_departamento, u.id_usuario, f.salario" +
                " FROM gestor g " +
                "INNER JOIN funcionario f ON g.id_funcionario = f.id_funcionario" +
                " INNER JOIN usuario u ON f.id_usuario = u.id_usuario ";
        try(Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            ResultSet rs = stmt.executeQuery();
            List<Gestor> gestores = new java.util.ArrayList<>();
            while(rs.next())
            {
                Endereco endereco = new EnderecoDAOImpl().buscarPorCodigo(rs.getInt("id_endereco"));
                Departamento departamento = new DepartamentoDAOImpl().buscarPorCodigo(rs.getInt("id_departamento"));

                Gestor gestor = new Gestor(
                        rs.getInt("id_gestor"),
                        rs.getString("setor"),
                        rs.getInt("id_funcionario"),
                        rs.getDouble("salario"),
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getDate("data_cadastro").toLocalDate(),
                        endereco,
                        rs.getBoolean("ativo"),
                        departamento
                );
                gestores.add(gestor);
            }
            return gestores;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
