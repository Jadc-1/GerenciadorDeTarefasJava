package sistematarefas.service;

import sistematarefas.dao.DepartamentoDAOImpl;
import sistematarefas.model.Departamento;

import java.sql.SQLException;
import java.util.List;

public class DepartamentoService {

    private final DepartamentoDAOImpl departamentoDAO;

    public DepartamentoService() {
        this.departamentoDAO = new DepartamentoDAOImpl();
    }

    public void salvar(Departamento departamento) throws SQLException {
        if (departamento == null) {
            throw new IllegalArgumentException("O departamento não pode ser nulo.");
        }
        if (departamento.getNomeDepartamento() == null || departamento.getNomeDepartamento().isEmpty()) {
            throw new IllegalArgumentException("O nome do departamento é obrigatório.");
        }

        departamentoDAO.salvar(departamento);
    }

    public void atualizar(Departamento departamento) throws SQLException {
        if (departamento == null || departamento.getIdDepartamento() <= 0) {
            throw new IllegalArgumentException("Departamento inválido para atualização.");
        }
        departamentoDAO.atualizar(departamento);
    }

    public void deletar(Departamento departamento) throws SQLException {
        if (departamento == null || departamento.getIdDepartamento() <= 0) {
            throw new IllegalArgumentException("Departamento inválido para exclusão.");
        }
        departamentoDAO.deletar(departamento);
    }

    public Departamento buscarPorCodigo(int idDepartamento) throws SQLException {
        if (idDepartamento <= 0) {
            throw new IllegalArgumentException("Código do departamento inválido.");
        }
        return departamentoDAO.buscarPorCodigo(idDepartamento);
    }

    public List<Departamento> buscarTodos() throws SQLException {
        return departamentoDAO.buscarTodos();
    }
}
