package model;

public class CondominioMensalidade {

    private int codigoMensalidade;
    private String mes;
    private String dataVencimento;
    private String dataPagamento;
    private String statusCondominio;
    private int codigoProprietario;
    private int codigoResidencia;

    public CondominioMensalidade(int codigoMensalidade, String mes, String dataVencimento, String dataPagamento, String statusCondominio, int codigoProprietario, int codigoResidencia) {
        this.codigoMensalidade = codigoMensalidade;
        this.mes = mes;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.statusCondominio = statusCondominio;
        this.codigoProprietario = codigoProprietario;
        this.codigoResidencia = codigoResidencia;
    }

    public CondominioMensalidade() {
    }

    public int getCodigoMensalidade() {
        return codigoMensalidade;
    }

    public void setCodigoMensalidade(int codigoMensalidade) {
        this.codigoMensalidade = codigoMensalidade;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getStatusCondominio() {
        return statusCondominio;
    }

    public void setStatusCondominio(String statusCondominio) {
        this.statusCondominio = statusCondominio;
    }

    public int getCodigoProprietario() {
        return codigoProprietario;
    }

    public void setCodigoProprietario(int codigoProprietario) {
        this.codigoProprietario = codigoProprietario;
    }

    public int getCodigoResidencia() {
        return codigoResidencia;
    }

    public void setCodigoResidencia(int codigoResidencia) {
        this.codigoResidencia = codigoResidencia;
    }

    @Override
    public String toString() {
        return "Mensalidade" + "\nCodigo da Mensalidade: " + codigoMensalidade + "\nMes: " + mes + "\nData de Vencimento: " + dataVencimento + "\nData de Pagamento: " + dataPagamento + "\nStatus: " + statusCondominio + "\nCodigo do Proprietario: " + codigoProprietario + "\nCodigo da Residencia: " + codigoResidencia;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.codigoMensalidade;
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
        final CondominioMensalidade other = (CondominioMensalidade) obj;
        return this.codigoMensalidade == other.codigoMensalidade;
    }
}
