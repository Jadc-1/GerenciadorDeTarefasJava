package sistematarefas.dao;

import sistematarefas.model.Departamento;
import sistematarefas.model.Endereco;
import java.util.List;

public interface DepartamentoDAO {
    void salvar(Departamento departamento);
    void atualizar(Departamento departamento);
    void deletar(int codigoDepartamento);
    Departamento buscarPorCodigo(int codigoDepartamento);
    List<Departamento> buscarTodos();
}
