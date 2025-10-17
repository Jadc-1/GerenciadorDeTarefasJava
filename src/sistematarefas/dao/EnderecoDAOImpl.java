package sistematarefas.dao;

import sistematarefas.model.Endereco;
import sistematarefas.utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class EnderecoDAOImpl implements EnderecoDAO{
    @Override
    public void salvar(Endereco endereco){
        String sql = "INSERT INTO endereco (rua, numero, cidade, estado) VALUES (?,?,?,?)";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, endereco.getRua());
            stmt.setString(2,endereco.getNumero());
            stmt.setString(3,endereco.getCidade());
            stmt.setString(4,endereco.getEstado());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // exibi o erro na tela se ocorrer algum erro
        }
    }
    @Override
    public void atualizar(Endereco endereco){
        String sql = "UPDATE endereco SET rua = ?, numero = ?, cidade = ?, estado = ? WHERE id_endereco = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, endereco.getRua());
            stmt.setString(2,endereco.getNumero());
            stmt.setString(3,endereco.getCidade());
            stmt.setString(4,endereco.getEstado());
            stmt.setInt(5, endereco.getIdEndereco());
            stmt.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(int codigoEndereco){
        String sql = "DELETE FROM endereco WHERE id_endereco = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, codigoEndereco);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Endereco buscarPorCodigo(int codigoEndereco){
        String sql = "SELECT * FROM endereco WHERE id_endereco = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1,codigoEndereco);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                Endereco endereco = new Endereco(rs.getInt("id_endereco"), rs.getString("rua"), rs.getString("numero"), rs.getString("cidade"), rs.getString("estado"));
                return endereco;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Endereco> buscarTodos()
    {
        List<Endereco> enderecos = new ArrayList<>();
        String sql = "SELECT * FROM endereco";
        try(Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                Endereco endereco = new Endereco(rs.getInt("id_endereco"), rs.getString("rua"), rs.getString("numero"), rs.getString("cidade"), rs.getString("estado"));
                enderecos.add(endereco);
            }
            return enderecos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
