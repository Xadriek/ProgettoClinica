package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Result;



public interface ResultRepository extends CrudRepository<Result, Long>{

	List<Result> findByName(String name);

}