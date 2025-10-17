import sistematarefas.dao.EnderecoDAO;
import sistematarefas.dao.EnderecoDAOImpl;
import sistematarefas.dao.ProjetoDAO;
import sistematarefas.dao.ProjetoDAOImpl;
import sistematarefas.model.Endereco;
import sistematarefas.model.Projeto;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    EnderecoDAO enderecoDAO = new EnderecoDAOImpl();
    Endereco endereco = new Endereco(2, "Rua teste", "123", "São", "PR");


    try {
        var enderecoString = enderecoDAO.buscarPorCodigo(1);
        IO.println(enderecoString);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
