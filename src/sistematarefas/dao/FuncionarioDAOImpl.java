package sistematarefas.dao;

import sistematarefas.model.Funcionario;
import sistematarefas.model.Usuario;
import sistematarefas.utils.Database;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAOImpl implements FuncionarioDAO{
    @Override
    public void salvar(Funcionario funcionario) throws SQLException{
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl(); // Como funcionário Herda de usuário é possível acessar suas funções
        usuarioDAO.salvar(funcionario); // Herança de usuário, para criar precisarei salvar na tabela usuario e depois salvar na tabela funcionario

        String sql = "INSERT INTO funcionario (salario, id_usuario, id_departamento) VALUES (?,?,?)";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setDouble(1, funcionario.getSalario());
            stmt.setInt(2, funcionario.getIdUsuario());
            stmt.setInt(3, funcionario.getDepartamento().getIdDepartamento());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Funcionario funcionario) throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        usuarioDAO.atualizar(funcionario);

        String sql = "UPDATE funcionario SET salario = ?, id_usuario = ?, id_departamento = ? WHERE id_funcionario = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setDouble(1, funcionario.getSalario());
            stmt.setInt(2, funcionario.getIdUsuario());
            stmt.setInt(3, funcionario.getDepartamento().getIdDepartamento());
            stmt.setInt(4, funcionario.getIdFuncionario());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(Funcionario funcionario) throws SQLException
    {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        usuarioDAO.deletar(funcionario);

        String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, funcionario.getIdFuncionario());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Funcionario buscarPorCodigo(int idFuncionario)
    {
        String sql = "SELECT f.id_funcionario, u.nome, u.email, u.telefone, f.salario, u.data_cadastro, u.id_endereco, u.ativo, u.id_departamento, f.id_usuario " +
                "FROM funcionario f INNER JOIN usuario u ON f.id_usuario = u.id_usuario WHERE f.id_funcionario = ?";
        try(Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, idFuncionario);
            ResultSet rs = stmt.executeQuery();

            if(rs.next())
            {
                return new Funcionario(rs.getInt("id_funcionario"),rs.getDouble("salario"), rs.getInt("id_usuario"), rs.getString("nome"), rs.getString("email"), rs.getString("telefone"), rs.getDate("data_cadastro").toLocalDate(), new EnderecoDAOImpl().buscarPorCodigo(rs.getInt("id_endereco")), rs.getBoolean("ativo"), new DepartamentoDAOImpl().buscarPorCodigo(rs.getInt("id_departamento")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Funcionario> buscarTodos()
    {   List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT f.id_funcionario, u.nome, u.email, u.telefone, f.salario, u.data_cadastro, u.id_endereco, u.ativo. u.id_departamento, f.id_usuario" +
                "FROM funcionario f" +
                "INNER JOIN usuario u" +
                "on f.id_usuario = u.id_usuario" +
                "ORDER BY nome";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.executeQuery(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                Funcionario funcionario = new Funcionario(rs.getInt("id_funcionario"), rs.getDouble("salario"), rs.getInt("id_usuario"), rs.getString("nome"), rs.getString("email"), rs.getString("telefone"), rs.getDate("data_cadastro").toLocalDate(), new EnderecoDAOImpl().buscarPorCodigo(rs.getInt("id_endereco")), rs.getBoolean("ativo"), new DepartamentoDAOImpl().buscarPorCodigo(rs.getInt("id_departamento")));
                funcionarios.add(funcionario);
            }
            return funcionarios;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
