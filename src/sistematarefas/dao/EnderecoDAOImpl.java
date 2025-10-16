package sistematarefas.dao;

import sistematarefas.model.Endereco;
import sistematarefas.utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;

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

    }

    @Override
    public void deletar(Endereco endereco){

    }

    @Override
    public Endereco buscarPorCodigo(int codigoEndereco){
        return null;
    }

    @Override
    public Endereco buscarPorCep(String cep){
        return null;
    }
}
