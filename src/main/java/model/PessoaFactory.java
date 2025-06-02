package model;

public interface PessoaFactory {

    Pessoa getPessoa(int codigo, String nome, String dataNascimento, String rg, String cpf, int codigoEndereco, ResidenciaEndereco endereco);
}
