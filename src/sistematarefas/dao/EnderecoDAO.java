package sistematarefas.dao;

import sistematarefas.model.Endereco;

public interface EnderecoDAO {
    void salvar(Endereco endereco);
    void atualizar(Endereco endereco);
    void deletar(Endereco endereco);
    Endereco buscarPorCodigo(int codigoEndereco);
    Endereco buscarPorCep(String cep);
}
