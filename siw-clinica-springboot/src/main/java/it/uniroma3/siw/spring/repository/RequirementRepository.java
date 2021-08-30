package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Requirement;



public interface RequirementRepository extends CrudRepository<Requirement, Long>{

	List<Requirement> findByName(String name);

}
