package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Doctor;



public interface DoctorRepository extends CrudRepository<Doctor, Long> {

	List<Doctor> findByName(String name);

	List<Doctor> findByNameAndSurname(String name, String surname);

}
