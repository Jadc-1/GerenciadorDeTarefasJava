package sistematarefas.model;

import java.time.LocalDate;
import java.util.spi.LocaleServiceProvider;

public class Usuario {
    private int idUsuario;
    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataCadastro;
    private Endereco endereco;
    private boolean ativo;
    private Departamento departamento;

    public Usuario(int idUsuario, String nome, String email, String telefone, Endereco endereco, boolean ativo, Departamento departamento)
    {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataCadastro = LocalDate.now();
        this.endereco = endereco;
        this.ativo = ativo;
        this.departamento = departamento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {this.idUsuario = idUsuario;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}
    public LocalDate getDataCadastro() {return dataCadastro;}
    public Endereco getEndereco() {return endereco;}
    public void setEndereco(Endereco endereco) {this.endereco = endereco;}
    public boolean getAtivo() {return ativo;}
    public void setAtivo(boolean ativo) {this.ativo = ativo;}
    public Departamento getDepartamento() {return departamento;}
    public void setDepartamento(Departamento departamento) {this.departamento = departamento;}
}
