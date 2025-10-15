package sistematarefas.model;

public class Endereco {
    private int idEndereco;
    private String rua;
    private String numero;
    private String cidade;
    private String estado;

    public int getIdEndereco() {return idEndereco;}
    public void setIdEndereco(int idEndereco) {this.idEndereco = idEndereco; }
    public String getRua() {return rua;}
    public void setRua(String rua) {this.rua = rua;}
    public String getCidade() {return cidade;}
    public void setCidade(String cidade) {this.cidade = cidade;}
    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}
}
