package model;

public class Pessoa {
    private int codigo;
    private String nome;
    private String dataNascimento;
    private String rg;
    private String cpf;
    private int codigoEndereco;
    private ResidenciaEndereco endereco;

    public Pessoa(int codigo, String nome, String dataNascimento, String rg, String cpf, int codigoEndereco, ResidenciaEndereco endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.rg = rg;
        this.cpf = cpf;
        this.codigoEndereco = codigoEndereco;
        this.endereco = endereco;
    }

    public Pessoa() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getCodigoEndereco() {
        return codigoEndereco;
    }

    public void setCodigoEndereco(int codigoEndereco) {
        this.codigoEndereco = codigoEndereco;
    }

    public ResidenciaEndereco getEndereco() {
        return endereco;
    }

    public void setEndereco(ResidenciaEndereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "------ Dados do Morador ----- " + "\nCodigo: " + codigo + "\nNome: " + nome + "\nData de Nascimento: " + dataNascimento + "\nRG: " + rg + "\nCPF: " + cpf + "\nCódigo da residencia: " + codigoEndereco;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.codigo;
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
        final Pessoa other = (Pessoa) obj;
        return this.codigo == other.codigo;
    }
    
    
}
