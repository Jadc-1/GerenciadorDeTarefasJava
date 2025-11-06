package sistematarefas.service;

import sistematarefas.dao.GestorDAOImpl;
import sistematarefas.model.Gestor;

import java.sql.SQLException;
import java.util.List;

public class GestorService {

    private GestorDAOImpl gestorDAO;

    public GestorService() {
        this.gestorDAO = new GestorDAOImpl();
    }

    public void salvarGestor(Gestor gestor) throws SQLException {
        if (gestor.getSetor() == null || gestor.getSetor().isEmpty()) {
            throw new IllegalArgumentException("O setor não pode estar vazio.");
        }
        gestorDAO.salvar(gestor);
    }

    public void atualizarGestor(Gestor gestor) throws SQLException {
        if (gestor.getIdGestor() <= 0) {
            throw new IllegalArgumentException("ID do gestor inválido.");
        }
        gestorDAO.atualizar(gestor);
    }

    public void deletarGestor(Gestor gestor) throws SQLException {
        if (gestor.getIdGestor() <= 0) {
            throw new IllegalArgumentException("ID do gestor inválido para exclusão.");
        }
        gestorDAO.deletar(gestor);
    }

    public Gestor buscarPorCodigo(int idGestor) {
        return gestorDAO.buscarPorCodigo(idGestor);
    }

    public List<Gestor> buscarTodos() {
        return gestorDAO.buscarTodos();
    }
}
