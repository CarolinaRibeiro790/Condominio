package model;

public class MoradorFactory implements PessoaFactory {

    @Override
    public Pessoa getPessoa(int codigo, String nome, String dataNascimento, String rg, String cpf, int codigoEndereco, ResidenciaEndereco endereco) {
        return new Morador(codigo, nome, dataNascimento, rg, cpf, codigoEndereco, endereco);
    }
}
