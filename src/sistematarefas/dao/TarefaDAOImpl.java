package sistematarefas.dao;

import sistematarefas.model.Funcionario;
import sistematarefas.model.Projeto;
import sistematarefas.model.Tarefa;
import sistematarefas.model.Usuario;
import sistematarefas.utils.Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAOImpl implements TarefaDAO {
    @Override
    public void salvar (Tarefa tarefa) {
        String sql = "INSERT INTO tarefa (id_tarefa, titulo, descricao, data_criacao, prazo, status, id_funcionario, id_projeto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, tarefa.getIdTarefa());
            stmt.setString(2, tarefa.getTitulo());
            stmt.setString(3, tarefa.getDescricao());
            stmt.setDate( 4, Date.valueOf(tarefa.getDataInicio()));
            stmt.setDate( 5, Date.valueOf(tarefa.getDataTermino())); //falta acrescentar status, id_funcionario e id_projeto, pois nao sei se sera por herança

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Tarefa tarefa)
    {
        String sql = "UPDATE tarefa SET titulo = ?, descricao = ?, data_criacao = ?, prazo = ?, status = ?, id_funcionario = ?, id_projeto WHERE id_tarefa = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setDate( 3, Date.valueOf(tarefa.getDataInicio()));
            stmt.setDate( 4, Date.valueOf(tarefa.getDataTermino())); //falta acrescentar status, id_funcionario e id_projeto, pois nao sei se sera por herança
            stmt.executeUpdate();
        } catch (Exception e) {
        e.printStackTrace();
        }
    }

    @Override
    public void deletar(Tarefa tarefa)
    {
        String sql = "DELETE FROM tarefa WHERE id_tarefa = ?";

        try(Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, tarefa.getIdTarefa());
            stmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Tarefa buscarPorCodigo(int idTarefa)
    {
        String sql = "SELECT * FROM tarefa WHERE id_tarefa = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, idTarefa);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                Projeto projeto = new ProjetoDAOImpl().buscarPorCodigo(rs.getInt("id_projeto"));
                Funcionario funcionario = new FuncionarioDAOImpl().buscarPorCodigo(rs.getInt("id_funcionario"));
                Tarefa tarefa = new Tarefa(
                        rs.getInt("id_tarefa"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getDate("data_criacao").toLocalDate(),
                        rs.getDate("prazo").toLocalDate(),
                        rs.getString("status"),
                        funcionario,
                        projeto
                );
            }
        }catch (Exception e) {
        e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tarefa> buscarTodos()
    {
        String sql = "SELECT * FROM tarefa";

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            List<Tarefa> tarefas = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                Projeto projeto = new ProjetoDAOImpl().buscarPorCodigo(rs.getInt("id_projeto"));
                Funcionario funcionario = new FuncionarioDAOImpl().buscarPorCodigo(rs.getInt("id_funcionario"));
                Tarefa tarefa = new Tarefa(
                        rs.getInt("id_tarefa"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getDate("data_criacao").toLocalDate(),
                        rs.getDate("prazo").toLocalDate(),
                        rs.getString("status"),
                        funcionario,
                        projeto
                );
                tarefas.add(tarefa);
            }
            return tarefas;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}