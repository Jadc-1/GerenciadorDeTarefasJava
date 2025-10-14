package sistematarefas.model;

import java.time.LocalDate;

public class Projeto {
    private int codigoProjeto;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private String statusProjeto;
    private char prioridade;
    private int idGestor;

    public Projeto(int codigoProjeto, String nome, String descricao, String statusProjeto, char prioridade, int idGestor){
        this.codigoProjeto = codigoProjeto;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = LocalDate.now();
        this.dataTermino = null;
        this.statusProjeto = statusProjeto;
        this.prioridade = prioridade;
        this.idGestor = idGestor;
    }

    public Projeto(int codigoProjeto, String nome, String descricao, LocalDate dataTermino, String statusProjeto, char prioridade, int idGestor){
        this.codigoProjeto = codigoProjeto;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = LocalDate.now();
        this.dataTermino = dataTermino;
        this.statusProjeto = statusProjeto;
        this.prioridade = prioridade;
        this.idGestor = idGestor;
    }

    public int getCodigoProjeto() { return codigoProjeto; }

    public void setCodigoProjeto(int codigoProjeto) { this.codigoProjeto = codigoProjeto; }

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getDescricao() {return descricao;}

    public LocalDate getDataInicio() {return dataInicio;}

    public LocalDate getDatatermino() {return dataTermino;}

    public void setDataTermino(LocalDate dataTermino) {this.dataTermino = dataTermino;}

    public void finalizarProjeto() {this.dataTermino = LocalDate.now();}

    public String getStatusProjeto() {return statusProjeto;}

    public void setStatusProjeto(String statusProjeto) {this.statusProjeto = statusProjeto;}

    public char getPrioridade() { return prioridade;}

    public void setPrioridade(char prioridade) {this.prioridade = prioridade;}

    public int getIdGestor() {return idGestor;}

    public void setIdGestor(int idGestor) {this.idGestor = idGestor;}
}
