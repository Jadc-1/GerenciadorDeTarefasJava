package sistematarefas.model;

import java.time.LocalDate;

public class Funcionario extends Usuario {
    private int idFuncionario;
    private double salario;

    public Funcionario(int idFuncionario, double salario, int idUsuario, String nome, String email, String telefone, LocalDate dataCadastro, Endereco endereco, boolean ativo, Departamento departamento)
    {
        this.idFuncionario = idFuncionario;
        this.salario = salario;
        super(idUsuario, nome, email, telefone, dataCadastro, endereco, ativo, departamento);
    }
    public int getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
}
