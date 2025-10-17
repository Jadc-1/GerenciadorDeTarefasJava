package sistematarefas.dao;

import sistematarefas.model.Endereco;
import java.util.List;

public interface EnderecoDAO {
    void salvar(Endereco endereco);
    void atualizar(Endereco endereco);
    void deletar(int codigoEndereco);
    Endereco buscarPorCodigo(int codigoEndereco);
    List<Endereco> buscarTodos();
}
