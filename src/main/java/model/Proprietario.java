package model;

public class Proprietario extends Pessoa {

    public Proprietario(int codigo, String nome, String dataNascimento, String rg, String cpf, int codigoEndereco, ResidenciaEndereco endereco) {
        super(codigo, nome, dataNascimento, rg, cpf, codigoEndereco, endereco);
    }

    public Proprietario() {
    }
}
