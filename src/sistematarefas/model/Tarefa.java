package sistematarefas.model;

import java.time.LocalDate;

public class Tarefa {
    private int idTarefa;
    private String titulo;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private String statusTarefa;
    private int idFuncionario;
    private int idProjeto;

    public Tarefa(int idTarefa, String titulo, String descricao, LocalDate dataInicio, LocalDate dataTermino, String statusTarefa, int idFuncionario, int idProjeto) {
        this.idTarefa = idTarefa;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.statusTarefa = statusTarefa;
        this.idFuncionario = idFuncionario;
        this.idProjeto = idProjeto;
    }

    public Tarefa(int idTarefa, String titulo, String descricao, LocalDate dataInicio, String statusTarefa, int idFuncionario, int idProjeto) {
        this.idTarefa = idTarefa;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTermino = null;
        this.statusTarefa = statusTarefa;
        this.idFuncionario = idFuncionario;
        this.idProjeto = idProjeto;
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
}
