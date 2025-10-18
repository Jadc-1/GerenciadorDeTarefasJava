import sistematarefas.dao.*;
import sistematarefas.model.Endereco;
import sistematarefas.model.Funcionario;
import sistematarefas.model.Gestor;
import sistematarefas.model.Projeto;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

import java.sql.SQLException;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    EnderecoDAO enderecoDAO = new EnderecoDAOImpl();
    GestorDAO gestorDAO = new GestorDAOImpl() {
    };
    try {
        var lista = gestorDAO.buscarTodos();
        if(gestorDAO.buscarTodos().isEmpty())
        {
            IO.println("Nada cadastrado!");
        }
        for(Gestor gestor : lista)
        {
            IO.println(gestor.toString());
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
