package com.jboliveira.springjpa.repository;

import com.jboliveira.springjpa.orm.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepository extends CrudRepository<Cargo,Integer> {

    List<Cargo> findByDescricaoContainingIgnoreCase(String desc);
}
