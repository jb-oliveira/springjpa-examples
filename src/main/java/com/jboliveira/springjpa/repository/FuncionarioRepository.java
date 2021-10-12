package com.jboliveira.springjpa.repository;

import com.jboliveira.springjpa.orm.Funcionario;
import com.jboliveira.springjpa.orm.FuncionarioProjecao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>,
        JpaSpecificationExecutor<Funcionario> {

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

    @Query(value = "select f.id,f.nome,f.salario from funcionarios f", nativeQuery = true)
    List<FuncionarioProjecao> findFuncionarioSalario();

    @Query(value = "select f.id,f.nome,f.salario from funcionarios f where f.salario > :salario", nativeQuery = true)
    List<FuncionarioProjecao> findFuncionarioSalarioComparametros(Double salario, Pageable pageable);
}
