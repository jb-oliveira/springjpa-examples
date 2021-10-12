package com.jboliveira.springjpa.service;

import com.jboliveira.springjpa.orm.Funcionario;
import com.jboliveira.springjpa.repository.FuncionarioRepository;
import com.jboliveira.springjpa.specification.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamico {

    private DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private FuncionarioRepository funcionarioRepository;

    public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        System.out.println("Digite o nome");
        String nome = scanner.next();

        if (nome.equals("NULL")) {
            nome = null;
        }

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        if (cpf.equals("NULL")) {
            cpf = null;
        }

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();

        if (salario == 0.0) {
            salario = null;
        }

        System.out.println("Digite a data contratacao");
        String dataContratacaoStr = scanner.next();
        LocalDate dataContratacao = null;

        if (!dataContratacaoStr.equals("NULL")) {
            dataContratacao = LocalDate.parse(dataContratacaoStr, formater);
        }


        List<Funcionario> list = funcionarioRepository.findAll(
                Specification.where(
                        SpecificationFuncionario.nome(nome)
                                .or(SpecificationFuncionario.cpf(cpf))
                                .or(SpecificationFuncionario.salario(salario))
                                .or(SpecificationFuncionario.dataContratacao(dataContratacao))
                )
        );
        list.forEach(System.out::println);
    }
}
