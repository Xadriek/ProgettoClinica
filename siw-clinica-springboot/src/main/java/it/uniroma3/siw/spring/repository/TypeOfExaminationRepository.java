package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.TypeOfExamination;

public interface TypeOfExaminationRepository extends CrudRepository<TypeOfExamination, Long>{

	List<TypeOfExamination> findByName(String name);
	
	

}
