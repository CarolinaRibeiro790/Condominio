package model;

public class ResidenciaEndereco {

    private int codigoResidencia;
    private String rua;
    private int numero;
    private String bairro;
    private String cep;

    public ResidenciaEndereco(int codigoResidencia, String rua, int numero, String bairro, String cep) {
        this.codigoResidencia = codigoResidencia;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
    }

    public ResidenciaEndereco() {
    }

    public int getCodigoResidencia() {
        return codigoResidencia;
    }

    public void setCodigoResidencia(int codigoResidencia) {
        this.codigoResidencia = codigoResidencia;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "----- Endereco Cadastrado ----- " + "\n Codigo da Residencia: " + codigoResidencia + "\n Rua: " + rua + "\nNumero: " + numero + "\nBairro: " + bairro + "\nCep: " + cep ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.codigoResidencia;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResidenciaEndereco other = (ResidenciaEndereco) obj;
        return this.codigoResidencia == other.codigoResidencia;
    }

}
