package sistematarefas.model;

public class Endereco {
    private int idEndereco;
    private String rua;
    private String numero;
    private String cidade;
    private String estado;

    public Endereco(int idEndereco, String rua, String numero, String cidade, String estado)
    {
        this.idEndereco = idEndereco;
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
    }

    public int getIdEndereco() {return idEndereco;}
    public void setIdEndereco(int idEndereco) {this.idEndereco = idEndereco; }
    public String getRua() {return rua;}
    public void setRua(String rua) {this.rua = rua;}
    public String getCidade() {return cidade;}
    public void setCidade(String cidade) {this.cidade = cidade;}
    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}
}
