package com.jboliveira.springjpa.repository;

import com.jboliveira.springjpa.orm.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {

    List<Funcionario> findByNome(String nome);

    List<Funcionario> findByNomeContainingIgnoreCase(String nome);

    @Query("select f " +
            "from Funcionario f " +
            "where f.nome like :nome " +
            "and f.salario >= :salario " +
            "and f.dataContratacao =:dataContratacao")
    List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate dataContratacao);

    @Query(value = "select * from funcionarios f where f.data_contratacao >= :data1",
            nativeQuery = true)
    List<Funcionario> findDataContratacaoMaior(LocalDate data);
}
