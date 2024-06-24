package pi.processo;

import pi.processo.domain.Funcionario;
import pi.processo.domain.FuncionariosMap;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //Instancias
        FuncionariosMap funcionariosMap = new FuncionariosMap();
        List<Funcionario> listFuncionario = new ArrayList<>();

        //Lista Funcionarios OBS: Joao removido
        listFuncionario.add(new Funcionario("Maria", "18/10/2000", new BigDecimal("2009.44"), "Operador"));
        listFuncionario.add(new Funcionario("Caio", "02/05/1961", new BigDecimal("9836.14"), "Coordenador"));
        listFuncionario.add(new Funcionario("Miguel", "14/10/1988", new BigDecimal("19119.88"), "Diretor"));
        listFuncionario.add(new Funcionario("Alice", "05/01/1995", new BigDecimal("2234.68"), "Recepcionista"));
        listFuncionario.add(new Funcionario("Heitor", "19/11/1999", new BigDecimal("1582.72"), "Operador"));
        listFuncionario.add(new Funcionario("Arthur", "31/03/1993", new BigDecimal("4071.84"), "Contador"));
        listFuncionario.add(new Funcionario("Laura", "08/07/1994", new BigDecimal("3017.45"), "Gerente"));
        listFuncionario.add(new Funcionario("Heloisa", "24/05/2003", new BigDecimal("1606.85"), "Eletricista"));
        listFuncionario.add(new Funcionario("Helena", "02/09/1996", new BigDecimal("2799.93"), "Gerente"));

        //LISTA MOSTRAR FUNCIONARIOS
        Map<String, List<Funcionario>> funcionariosAgrupados = funcionariosMap.mostrarFuncionarios(listFuncionario);
        funcionariosAgrupados.forEach((nome, funcionarios) -> {
            funcionarios.forEach(funcionario -> System.out.println("\t" + funcionario));
        });

        //LISTA POR FUNCAO
        Map<String, List<Funcionario>> funcionariosPorFuncao = FuncionariosMap.filtrarFuncao(listFuncionario);
        for (String funcao : funcionariosPorFuncao.keySet()) {
            System.out.println("Função: " + funcao);
            for (Funcionario funcionario : funcionariosPorFuncao.get(funcao)) {
                System.out.println("\tNome: " + funcionario.getNome() + ", Salário: " + funcionario.getSalario());
            }
        }

//        //LISTA SALARIO COM 10%
        BigDecimal porcentagemAumento = new BigDecimal("10.00");
        List<Funcionario> funcionarios12 = funcionariosMap.reajustarSalarioEmLista(listFuncionario, porcentagemAumento);

        for (Funcionario funcionario : funcionarios12) {
            System.out.println(funcionario);
        }

        //LISTA FUNCIONARIOS MES 10 E 12
        Map<LocalDate, List<Funcionario>> aniversariantesDezEDoze = funcionariosMap.funcionariosQueFazemAniversarioDezEDoze(listFuncionario);
        aniversariantesDezEDoze.forEach((data, funcionariosAniversariantes) -> {
            System.out.println("Aniversariantes em " + data.getMonthValue() + "/" + data.getDayOfMonth() + ":");
            funcionariosAniversariantes.forEach(funcionario -> System.out.println("\t" + funcionario));
        });


        //LISTA DE FUNCIONARIOS MAIS VELHOS
        Map<LocalDate, List<Funcionario>> funcionariosMaioresDe1990 = funcionariosMap.funcionariosComMaiorIdade(listFuncionario);
        System.out.println("Funcionarios mais velhos da empresa: ");
        funcionariosMaioresDe1990.forEach((data, funcionarios) -> {
            funcionarios.forEach(funcionario -> System.out.println("\t" + funcionario.getNome() + ",  Nascido em: " + funcionario.getDataNascimento()));
        });

        //LISTA DE FUNCIONARIOS ORDEM ALFABÉTICA
        Map<String, List<Funcionario>> funcionariosOrdenados = funcionariosMap.mostrarFuncionarios(listFuncionario);

        funcionariosOrdenados.forEach((nome, funcionarios) -> {
            funcionarios.forEach(funcionario -> System.out.println("\t" + funcionario));
        });

        //LISTA TOTAL SALARIO FUNCIONARIOS
        BigDecimal totalSalario = funcionariosMap.totalSalarioFuncionarios(listFuncionario);
        System.out.println("Total salario: ");
        System.out.println(totalSalario);

        //LISTA DE SALARIOS MINIMOS
        Map<Funcionario, BigDecimal> salarioMinimoFuncionario = funcionariosMap.quantoCadaUmGanhaDeSalarioMinimo(listFuncionario);
        salarioMinimoFuncionario.forEach((funcionario, quantidadeSalariosMinimos) ->
                System.err.println(funcionario.getNome() + " = ganha: " + quantidadeSalariosMinimos + " salarios minimos"));

    }
}

