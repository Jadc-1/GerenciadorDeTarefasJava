package sistematarefas.model;

import java.time.LocalDate;

public class Projeto {
    private int codigoProjeto;
    private string nome;
    private string descricao;
    private LocalDate datainicio;
    private LocalDate datatermino;
    private string statusprojeto;
    private char prioridade;
    private int idGestor;

    public Projeto(int codigoProjeto, string nome, string descricao, LocalDate datainicio, LocalDate datatermino, string statusprojeto, char prioridade, int idGestor){
        this.codigoProjeto = codigoProjeto;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = LocalDate.now();
        this.dataTermino = null;
        this.statusProjeto = statusprojeto;
        this.prioridade = prioridade;
        this.idGestor = idGestor;
    }

    public int getCodigoProjeto() { return codigoProjeto; }

    public void setCodigoProjeto(int codigoProjeto) { this.codigoProjeto = codigoProjeto; }

    public string getNome() {return nome;}

    public void setNome(string nome) {this.nome = nome;}

    public string getDescricao() {return descricao;}

    public LocalDate getDataInicio() {return dataInicio;}

    public LocalDate getDatatermino() {return dataTermino;}

    public void setDataTermino(LocalDate dataTermino) {this.dataTermino = dataTermino;}

    public void finalizarProjeto() {this.dataTermino = LocalDate.now();}

    public char getPrioridade() { return prioridade;}

    public void setPrioridade(char prioridade) {this.prioridade = prioridade;}

    public int getIdGestor() {return idGestor;}

    public void setIdGestor(int idGestor) {this.idGestor = idGestor;}
}
