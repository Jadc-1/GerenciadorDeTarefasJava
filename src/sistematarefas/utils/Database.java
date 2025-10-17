package sistematarefas.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Arquivo para conexão com o banco de dados
public class Database {
    private static final String url = "jdbc:mysql://localhost:3306/sistema_tarefas"; //Aqui irei passar a url de conexão (está na pagina principal do mysql workbench) e o banco de dados
    private static final String user = "root";
    private static final String password = "LuC@s271656";

    public static Connection getConnection() throws SQLException { //Metodo que será usado para realizar conexão com o banco de dados e posteriormente realizar a manipulação dos dados
        return DriverManager.getConnection(url, user, password);
    }
}
