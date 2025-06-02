package model;

public class Morador extends Pessoa {

    public Morador(int codigo, String nome, String dataNascimento, String rg, String cpf, int codigoEndereco, ResidenciaEndereco endereco) {
        super(codigo, nome, dataNascimento, rg, cpf, codigoEndereco, endereco);
    }

    public Morador() {
    }   
}
