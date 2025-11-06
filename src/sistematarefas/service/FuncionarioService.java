package sistematarefas.service;

import sistematarefas.dao.FuncionarioDAOImpl;
import sistematarefas.model.Funcionario;

import java.sql.SQLException;
import java.util.List;

public class FuncionarioService {

    private final FuncionarioDAOImpl funcionarioDAO;

    public FuncionarioService() {
        this.funcionarioDAO = new FuncionarioDAOImpl();
    }

    public void salvar(Funcionario funcionario) throws SQLException {
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não pode ser nulo.");
        }
        if (funcionario.getSalario() <= 0) {
            throw new IllegalArgumentException("O salário deve ser maior que zero.");
        }
        funcionarioDAO.salvar(funcionario);
    }

    public void atualizar(Funcionario funcionario) throws SQLException {
        if (funcionario == null || funcionario.getIdFuncionario() <= 0) {
            throw new IllegalArgumentException("Funcionário inválido para atualização.");
        }
        funcionarioDAO.atualizar(funcionario);
    }

    public void deletar(Funcionario funcionario) throws SQLException {
        if (funcionario == null || funcionario.getIdFuncionario() <= 0) {
            throw new IllegalArgumentException("Funcionário inválido para exclusão.");
        }
        funcionarioDAO.deletar(funcionario);
    }

    public Funcionario buscarPorCodigo(int idFuncionario) throws SQLException {
        if (idFuncionario <= 0) {
            throw new IllegalArgumentException("Código do funcionário inválido.");
        }
        return funcionarioDAO.buscarPorCodigo(idFuncionario);
    }

    public List<Funcionario> buscarTodos() throws SQLException {
        return funcionarioDAO.buscarTodos();
    }
}
