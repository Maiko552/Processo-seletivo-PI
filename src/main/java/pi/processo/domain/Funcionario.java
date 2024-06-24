package pi.processo.domain;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class Funcionario extends Pessoa {

    private BigDecimal salario;
    private String funcao;


    public Funcionario(String nome, String dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }


    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols(new Locale("pt", "BR"));
        simbolo.setDecimalSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", simbolo);
        return "\tFuncionario (a): " +
                nome + ", " + "\tdata de nascimento: " +
                dataNascimento.format(formatter) + ", " + "\tsalário: " + decimalFormat.format(salario.doubleValue()) + " ," +
                "\tfunção: " + funcao + "\n";

    }
}
