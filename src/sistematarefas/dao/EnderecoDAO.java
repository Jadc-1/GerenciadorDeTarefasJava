package sistematarefas.dao;

import sistematarefas.model.Endereco;

public interface EnderecoDAO {
    void salvar(Endereco endereco);
    void atualizar(Endereco endereco);
    void deletar(int codigoEndereco);
    Endereco buscarPorCodigo(int codigoEndereco);
}
