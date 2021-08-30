package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Doctor;
import it.uniroma3.siw.spring.repository.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository; 
	@Autowired
	private CredentialsService credentialsService;
	
	@Transactional
	public Doctor insert(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	@Transactional
	public List<Doctor> allDoctors() {
		return (List<Doctor>) doctorRepository.findAll();
	}

	@Transactional
	public Doctor doctorById(Long id) {
		Optional<Doctor> optional = doctorRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Doctor doctor) {
		List<Doctor> doctors = this.doctorRepository.findByNameAndSurname(doctor.getName(),doctor.getSurname());
		if (doctors.size() > 0)
			return true;
		else 
			return false;
	}
	@Transactional
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}
}
