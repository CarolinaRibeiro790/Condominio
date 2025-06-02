package model;

public class ProprietarioEndereco {

    private int codigo;
    private String nome;
    private String dataNascimento;
    private String cpf;
    private String rg;
    private String rua;
    private int numero;
    private String bairro;
    private String cep;
    private String pagamento;
    private String vencimento;
    private String status;
    private String mes;

    public ProprietarioEndereco(int codigo, String nome, String dataNascimento, String cpf, String rg, String rua, int numero, String bairro, String cep, String pagamento, String vencimento, String status, String mes) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.pagamento = pagamento;
        this.vencimento = vencimento;
        this.status = status;
        this.mes = mes;
    }

    public ProprietarioEndereco() {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
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

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
       
    @Override
    public String toString() {
        return "----- Exibindo dados do Proprietario ----- " + "\nCodigo: " + codigo + "\nNome: " + nome + "\nData de Nascimento: " + dataNascimento + "\nCPF: " + cpf + "\nRG: " + rg + "\nRua: " + rua + "\nNumero: " + numero + "\nBairro: " + bairro + "\nCep: " + cep + "\nData de Pagamento: " + pagamento + "\nData de Vencimento: " + vencimento + "\nStatus=" + status + "\nMes: " + mes;
    }

}
