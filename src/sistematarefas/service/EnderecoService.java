package sistematarefas.service;

import sistematarefas.dao.EnderecoDAOImpl;
import sistematarefas.model.Endereco;

import java.sql.SQLException;
import java.util.List;

public class EnderecoService {

    private final EnderecoDAOImpl enderecoDAO;

    public EnderecoService() {
        this.enderecoDAO = new EnderecoDAOImpl();
    }

    public void salvar(Endereco endereco) throws SQLException {
        validarEndereco(endereco);
        enderecoDAO.salvar(endereco);
    }

    public void atualizar(Endereco endereco) throws SQLException {
        if (endereco.getIdEndereco() <= 0) {
            throw new IllegalArgumentException("O ID do endereço é inválido para atualização.");
        }
        validarEndereco(endereco);
        enderecoDAO.atualizar(endereco);
    }

    public void deletar(Endereco endereco) throws SQLException {
        if (endereco == null || endereco.getIdEndereco() <= 0) {
            throw new IllegalArgumentException("Endereço inválido para exclusão.");
        }
        enderecoDAO.deletar(endereco);
    }

    public Endereco buscarPorCodigo(int idEndereco) throws SQLException {
        if (idEndereco <= 0) {
            throw new IllegalArgumentException("Código de endereço inválido.");
        }
        return enderecoDAO.buscarPorCodigo(idEndereco);
    }

    public List<Endereco> buscarTodos() throws SQLException {
        return enderecoDAO.buscarTodos();
    }

    private void validarEndereco(Endereco endereco) {
        if (endereco == null) {
            throw new IllegalArgumentException("O endereço não pode ser nulo.");
        }
        if (endereco.getRua() == null || endereco.getRua().isEmpty()) {
            throw new IllegalArgumentException("A rua é obrigatória.");
        }
        if (endereco.getNumero() == null || endereco.getNumero().isEmpty()) {
            throw new IllegalArgumentException("O número é obrigatório.");
        }
        if (endereco.getCidade() == null || endereco.getCidade().isEmpty()) {
            throw new IllegalArgumentException("A cidade é obrigatória.");
        }
        if (endereco.getEstado() == null || endereco.getEstado().isEmpty()) {
            throw new IllegalArgumentException("O estado é obrigatório.");
        }
    }
}