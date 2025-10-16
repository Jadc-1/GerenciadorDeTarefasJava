package sistematarefas.dao;

import sistematarefas.model.Projeto;

import java.util.List;

public class ProjetoDAOImpl implements ProjetoDAO {
    @Override
    public void salvar(Projeto projeto)
    {
        String sql = "INSERT INTO projeto (nome, descricao, dataInicio, dataTermino, statusProjeto, prioridade, idGestor) VALUES (?,?,?,?,?,?,?)";
    }

    @Override
    public void atualizar(Projeto projeto)
    {

    }

    @Override
    public void deletar(Projeto projeto)
    {

    }

    @Override
    public Projeto buscarPorCodigo(int codigoProjeto)
    {
        return null;
    }

    @Override
    public List<Projeto> buscarTodos() { return null; }
}
