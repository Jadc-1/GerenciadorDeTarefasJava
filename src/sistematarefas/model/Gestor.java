package sistematarefas.model;

import java.time.LocalDate;

public class Gestor extends Funcionario{
    private int idGestor;
    private String setor;
    private int idFuncionario;

    public Gestor(int idGestor, String setor, int idFuncionario, double salario, int idUsuario, String nome, String email, String telefone, LocalDate dataCadastro, Endereco endereco, boolean ativo, Departamento departamento) {
        this.idGestor = idGestor;
        this.setor = setor;
        super(idFuncionario, salario, idUsuario, nome, email, telefone, dataCadastro, endereco, ativo, departamento);
    }
    public int getIdGestor() { return idGestor; }
    public void setIdGestor(int idGestor) { this.idGestor = idGestor; }
    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }
    public int getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; }
}
