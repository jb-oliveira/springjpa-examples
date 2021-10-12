package com.jboliveira.springjpa;

import com.jboliveira.springjpa.orm.Cargo;
import com.jboliveira.springjpa.repository.CargoRepository;
import com.jboliveira.springjpa.repository.FuncionarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
public class CargoTest {

    @Autowired
    private CargoRepository cargoRepository;


    @BeforeEach
    void loadData() {
        cargoRepository.save(new Cargo("DESENVOLVEDOR"));
        cargoRepository.save(new Cargo("RH"));
        cargoRepository.save(new Cargo("GERENTE"));
        cargoRepository.save(new Cargo("QA"));
    }

    @Test
    void testCaseInsensitive() {
        List<Cargo> cargos = cargoRepository.findByDescricaoContainingIgnoreCase("EN");
        Assertions.assertEquals(2, cargos.size());
    }




}
