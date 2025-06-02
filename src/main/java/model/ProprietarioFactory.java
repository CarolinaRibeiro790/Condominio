package model;

public class ProprietarioFactory implements PessoaFactory {

    @Override
    public Pessoa getPessoa(int codigo, String nome, String dataNascimento, String rg, String cpf, int codigoEndereco, ResidenciaEndereco endereco) {
        return new Proprietario(codigo, nome, dataNascimento, rg, cpf, codigoEndereco, endereco);
    }

}
