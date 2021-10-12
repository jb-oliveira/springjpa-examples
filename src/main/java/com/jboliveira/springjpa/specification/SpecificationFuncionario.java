package com.jboliveira.springjpa.specification;

import com.jboliveira.springjpa.orm.Funcionario;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class SpecificationFuncionario {

    public static Specification<Funcionario> nome(String nome) {
        return (root, criterioQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    }

    public static Specification<Funcionario> cpf(String cpf) {
        return (root, criterioQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("cpf"), cpf);
    }

    public static Specification<Funcionario> salario(Double salario) {
        return (root, criterioQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("salario"), salario);
    }

    public static Specification<Funcionario> dataContratacao(LocalDate data) {
        return (root, criterioQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("dataContratacao"), data);
    }

}
