package sistematarefas.dao;

import sistematarefas.model.Departamento;
import sistematarefas.model.Endereco;
import sistematarefas.model.Funcionario;
import sistematarefas.utils.Database;

import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAOImpl implements DepartamentoDAO{
    @Override
    public void salvar(Departamento departamento) {
        String sql = "INSERT INTO departamento (nomeDepartamento, ativo) VALUES (?,?)";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, departamento.getNomeDepartamento());
            stmt.setBoolean(2, departamento.getAtivo());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Departamento departamento)
    {
        String sql = "UPDATE departamento SET nomeDepartamento = ?, ativo = ? WHERE id_departamento = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, departamento.getNomeDepartamento());
            stmt.setBoolean(2, departamento.getAtivo());
            stmt.setInt(3, departamento.getIdDepartamento());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(Departamento departamento){
        String sql = "DELETE FROM departamento WHERE id_departamento = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, departamento.getIdDepartamento());
            stmt.executeUpdate();
        } catch (Exception e) {}
    }

    @Override
    public Departamento buscarPorCodigo(int idDepartamento){
        String sql = "SELECT * FROM departamento WHERE id_departamento = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, idDepartamento);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                Departamento departamento = new Departamento(rs.getInt("id_departamento"), rs.getString("nome_departamento"), rs.getBoolean("ativo"));
                return departamento;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Departamento> buscarTodos() {
        List<Departamento> departamentos = new ArrayList<>();
        String sql = "SELECT * FROM departamento";
        try(Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                Departamento departamento = new Departamento(rs.getInt("id_departamento"), rs.getString("nome_departamento"), rs.getBoolean("ativo"));
                departamentos.add(departamento);
            }
            return departamentos;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
