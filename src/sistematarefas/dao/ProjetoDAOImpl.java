package sistematarefas.dao;

import sistematarefas.model.Departamento;
import sistematarefas.model.Endereco;
import sistematarefas.model.Gestor;
import sistematarefas.model.Projeto;
import sistematarefas.utils.Database;
import sistematarefas.dao.GestorDAOImpl;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAOImpl implements ProjetoDAO {
    @Override
    public void salvar(Projeto projeto)
    {
        String sql = "INSERT INTO projeto (nome, descricao, data_inicio, data_termino, status_projeto, prioridade, id_gestor) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = (conn.prepareStatement(sql)))
        {
            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.setDate(3, Date.valueOf(projeto.getDataInicio()));
            stmt.setDate(4, Date.valueOf(projeto.getDataTermino()));
            stmt.setString(5, projeto.getStatusProjeto());
            stmt.setString(6, String.valueOf(projeto.getPrioridade()));
            stmt.setInt(7, projeto.getGestor().getIdGestor());
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Projeto projeto)
    {
        String sql = "UPDATE SET nome = ?, descricao = ?, data_inicio = ?, data_termino = ?, status_projeto = ?, prioridade = ? WHERE id_projeto = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = (conn.prepareStatement(sql)))
        {
            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.setDate(3, Date.valueOf(projeto.getDataInicio()));
            stmt.setDate(4, Date.valueOf(projeto.getDataTermino()));
            stmt.setString(5, projeto.getStatusProjeto());
            stmt.setString(6, String.valueOf(projeto.getPrioridade()));
            stmt.setInt(7, projeto.getGestor().getIdGestor());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(Projeto projeto)
    {
        String sql = "DELETE FROM projeto WHERE id_projeto = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = (conn.prepareStatement(sql)))
        {
            stmt.setInt(1, projeto.getCodigoProjeto());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Projeto buscarPorCodigo(int idProjeto)
    {
        String sql = "SELECT p.codigo_projeto, p.nome, p.descricao, p.data_inicio, p.data_termino, p.status_projeto, p.prioridade, id_gestor, u.nome AS nome_gestor" +
                "FROM projeto p" +
                "JOIN gestor g on p.id_gestor = g.id_gestor" +
                "JOIN funcionario f on g.id_funcionario = f.id_funcionario" +
                "JOIN usuario u on f.id_usuario = u.id_usuario" +
                "WHERE p.codigo_projeto = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = (conn.prepareStatement(sql)))
        {
            stmt.setInt(1, idProjeto);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                Gestor gestor = new GestorDAOImpl().buscarPorCodigo(rs.getInt("id_gestor"));
                Projeto projeto = new Projeto(
                        rs.getInt("codigo_projeto"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDate("data_termino").toLocalDate(),
                        rs.getString("status_projeto"),
                        rs.getString("prioridade").charAt(0),
                        gestor

                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Projeto> buscarTodos() {
        String sql = "SELECT p.codigo_projeto, p.nome, p.descricao, p.data_inicio, p.data_termino, p.status_projeto, p.prioridade, id_gestor, u.nome AS nome_gestor" +
                "FROM projeto p" +
                "JOIN gestor g on p.id_gestor = g.id_gestor" +
                "JOIN funcionario f on g.id_funcionario = f.id_funcionario" +
                "JOIN usuario u on f.id_usuario = u.id_usuario";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            List<Projeto> projetos = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                Gestor gestor = new GestorDAOImpl().buscarPorCodigo(rs.getInt("id_gestor"));
                Projeto projeto = new Projeto(
                        rs.getInt("codigo_projeto"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDate("data_termino").toLocalDate(),
                        rs.getString("status_projeto"),
                        rs.getString("prioridade").charAt(0),
                        gestor

                );
                projetos.add(projeto);
            }
            return projetos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
