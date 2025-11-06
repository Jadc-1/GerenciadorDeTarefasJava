package sistematarefas.service;

import sistematarefas.dao.ProjetoDAOImpl;
import sistematarefas.model.Projeto;

import java.util.List;

public class ProjetoService {

    private ProjetoDAOImpl projetoDAO;

    public ProjetoService() {
        this.projetoDAO = new ProjetoDAOImpl();
    }

    public void salvarProjeto(Projeto projeto) throws Exception {
        if (projeto.getNome() == null || projeto.getNome().isBlank()) {
            throw new Exception("O nome do projeto é obrigatório.");
        }

        if (projeto.getDescricao() == null || projeto.getDescricao().isBlank()) {
            throw new Exception("A descrição do projeto é obrigatória.");
        }

        if (projeto.getGestor() == null) {
            throw new Exception("O projeto deve estar vinculado a um gestor.");
        }

        projetoDAO.salvar(projeto);
    }

    public void atualizarProjeto(Projeto projeto) throws Exception {
        if (projeto.getCodigoProjeto() <= 0) {
            throw new Exception("Código de projeto inválido para atualização.");
        }

        projetoDAO.atualizar(projeto);
    }

    public void deletarProjeto(Projeto projeto) throws Exception {
        if (projeto == null || projeto.getCodigoProjeto() <= 0) {
            throw new Exception("Projeto inválido para exclusão.");
        }

        projetoDAO.deletar(projeto);
    }

    public Projeto buscarPorCodigo(int idProjeto) throws Exception {
        if (idProjeto <= 0) {
            throw new Exception("Código de projeto inválido.");
        }

        Projeto projeto = projetoDAO.buscarPorCodigo(idProjeto);
        if (projeto == null) {
            throw new Exception("Projeto não encontrado.");
        }

        return projeto;
    }

    public List<Projeto> buscarTodos() {
        return projetoDAO.buscarTodos();
    }
}
