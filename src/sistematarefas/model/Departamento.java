package sistematarefas.model;

public class Departamento {
    private int idDepartamento;
    private String nomeDepartamento;
    private boolean ativo;

    public Departamento(int idDepartamento, String nomeDepartamento, boolean ativo) {
        this.idDepartamento = idDepartamento;
        this.nomeDepartamento = nomeDepartamento;
        this.ativo = ativo;
    }

    public int getIdDepartamento() { return idDepartamento; }
    public void setIdDepartamento(int idDepartamento) {this.idDepartamento = idDepartamento;}
    public String getNomeDepartamento() { return nomeDepartamento; }
    public void setNomeDepartamento(String nomeDepartamento) {this.nomeDepartamento = nomeDepartamento;}
    public boolean getAtivo() { return ativo; }
    public void setAtivo(boolean ativo) {this.ativo = ativo;}
}
