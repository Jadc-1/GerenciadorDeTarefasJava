package sistematarefas.dao;

import sistematarefas.model.Funcionario;

public interface FuncionarioDAO {
    void salvar(Funcionario funcionario);
    void atualizar(Funcionario funcionario);
    void deletar(int codigoFuncionario);
    Funcionario buscarPorCodigo(int codigoFuncionario);
}
