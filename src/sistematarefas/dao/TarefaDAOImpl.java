package sistematarefas.dao;

import sistematarefas.model.Projeto;
import sistematarefas.model.Tarefa;
import sistematarefas.utils.Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    public void deletar(int idTarefa)
    {
        String sql = "DELETE FROM tarefa WHERE id_tarefa = ?";

        try(Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, idTarefa);
            stmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Tarefa buscarPorCodigo(int codigoTarefa)
    {
        String sql = "SELECT * FROM tarefa WHERE id_tarefa = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, codigoTarefa);
            ResultSet rs = stmt.executeQuery();
            Tarefa tarefa = new Tarefa(rs.getInt ( "id_tarefa"), rs.getString("titulo"), rs.getString("descricao"), rs.getDate("data_criacao"));//falta acrescentar status, id_funcionario e id_projeto, pois nao sei se sera por herança
            return tarefa;
        }catch (Exception e) {
        e.printStackTrace();
        }
        return null;
    }
}
