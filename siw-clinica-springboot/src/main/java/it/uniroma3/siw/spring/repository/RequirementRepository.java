package it.uniroma3.siw.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Requirement;
import it.uniroma3.siw.spring.model.TypeOfExamination;



public interface RequirementRepository extends CrudRepository<Requirement, Long>{

	List<Requirement> findByName(String name);

	Optional<Requirement> findByTypeOfExamination(TypeOfExamination typeOfExamination);

}
