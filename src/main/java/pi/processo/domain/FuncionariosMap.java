package pi.processo.domain;


import java.math.BigDecimal;

import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class FuncionariosMap {

    //Map
    Map<LocalDate, List<Funcionario>> filtro = new HashMap<>();


    //Métodos
    public Map<String, List<Funcionario>> mostrarFuncionarios(List<Funcionario> list) {
        return list.stream().collect(Collectors.groupingBy(Funcionario::getNome, TreeMap::new, Collectors.toList()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (PrimeiroArgumentoNome, segundoNomeArgumento) -> PrimeiroArgumentoNome, LinkedHashMap::new));

    }

    public static Map<String, List<Funcionario>> filtrarFuncao(List<Funcionario> list) {
        return list.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

    }

    public List<Funcionario> reajustarSalarioEmLista(List<Funcionario> funcionarios, BigDecimal porcentagemAumento) {
        BigDecimal divisor = BigDecimal.valueOf(100);
        BigDecimal multiplicadorAumento = porcentagemAumento.divide(divisor).add(BigDecimal.ONE);

        for (Funcionario funcionario : funcionarios) {
            BigDecimal salarioAtual = funcionario.getSalario();
            BigDecimal novoSalario = salarioAtual.multiply(multiplicadorAumento);
            funcionario.setSalario(novoSalario.setScale(2, RoundingMode.HALF_EVEN));
        }
        return funcionarios;
    }

    public Map<LocalDate, List<Funcionario>> funcionariosQueFazemAniversarioDezEDoze(List<Funcionario> list) {
        filtro = list.stream()
                .collect(Collectors.groupingBy(Funcionario::getDataNascimento));

        Map<LocalDate, List<Funcionario>> aniversariantesDezEDoze = filtro.entrySet().stream()
                .filter(entry -> entry.getKey().getMonthValue() == 10 || entry.getKey().getMonthValue() == 12)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return aniversariantesDezEDoze;
    }

    public Map<LocalDate, List<Funcionario>> funcionariosComMaiorIdade(List<Funcionario> list) {
        filtro = list.stream()
                .collect(Collectors.groupingBy(Funcionario::getDataNascimento));

        Map<LocalDate, List<Funcionario>> funcionariosMaisVelhos = filtro.entrySet().stream()
                .filter(entry -> entry.getKey().getYear() < 1990)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return funcionariosMaisVelhos;
    }

    public BigDecimal totalSalarioFuncionarios(List<Funcionario> list) {
        return list.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<Funcionario, BigDecimal> quantoCadaUmGanhaDeSalarioMinimo(List<Funcionario> list) {
        BigDecimal salarioMinimo = BigDecimal.valueOf(1212.00);
        return list.stream()
                .collect(Collectors.toMap(
                        funcionario -> funcionario,
                        funcionario -> funcionario.getSalario().divide(salarioMinimo, 1, RoundingMode.DOWN)
                ));
    }

}













