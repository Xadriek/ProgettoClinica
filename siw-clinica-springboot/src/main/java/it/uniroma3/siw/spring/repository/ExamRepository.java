package it.uniroma3.siw.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Doctor;
import it.uniroma3.siw.spring.model.Exam;
import it.uniroma3.siw.spring.model.User;

public interface ExamRepository extends CrudRepository<Exam, Long>{
	
	public Optional<List<Exam>> findByPatient(User Patient);
	
	public Optional<List<Exam>> findByDoctor(Doctor doctor);
}
