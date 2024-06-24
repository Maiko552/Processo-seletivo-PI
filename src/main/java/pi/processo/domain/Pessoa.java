package pi.processo.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Pessoa {

    protected String nome;
    protected LocalDate dataNascimento;


    public Pessoa(String nome, String dataNascimento) {
        this.nome = nome;

        //Data formatada
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataNascimento = LocalDate.parse(dataNascimento, formatter);
    }


    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}

