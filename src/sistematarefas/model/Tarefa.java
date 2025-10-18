package sistematarefas.model;

import java.time.LocalDate;

public class Tarefa {
    private int idTarefa;
    private String titulo;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private String statusTarefa;
    private Funcionario funcionario;
    private Projeto projeto;

    public Tarefa(int idTarefa, String titulo, String descricao, LocalDate dataInicio, LocalDate dataTermino, String statusTarefa, Funcionario funcionario, Projeto projeto) {
        this.idTarefa = idTarefa;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.statusTarefa = statusTarefa;
        this.funcionario = funcionario;
        this.projeto = projeto;
    }

    public Tarefa(int idTarefa, String titulo, String descricao, LocalDate dataInicio, String statusTarefa, Funcionario funcionario, Projeto projeto) {
        this.idTarefa = idTarefa;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTermino = null;
        this.statusTarefa = statusTarefa;
        this.funcionario = funcionario;
        this.projeto = projeto;
    }

    public int getIdTarefa() { return idTarefa; }
    public void setIdTarefa(int idTarefa) { this.idTarefa = idTarefa; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public LocalDate getDataTermino() { return dataTermino; }
    public void setDataTermino(LocalDate dataTermino) { this.dataTermino = dataTermino; }
    public String getStatusTarefa() { return statusTarefa; }
    public void setStatusTarefa(String statusTarefa) { this.statusTarefa = statusTarefa; }
    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }
    public Projeto getProjeto() { return projeto; }
    public void setProjeto(Projeto projeto) { this.projeto = projeto; }
}
