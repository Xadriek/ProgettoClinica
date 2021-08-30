package it.uniroma3.siw.spring.service;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.uniroma3.siw.spring.model.TypeOfExamination;
import it.uniroma3.siw.spring.repository.TypeOfExaminationRepository;

@Service
public class TypeOfExaminationService {
	
	@Autowired
	private TypeOfExaminationRepository typeOfExaminationRepository;
	@Autowired
	private CredentialsService credentialsService;
	
	@Transactional
	public TypeOfExamination insert(TypeOfExamination typeOfExamination) {
		return typeOfExaminationRepository.save(typeOfExamination);
	}
	
	@Transactional
	public List<TypeOfExamination> allTypeOfExamination() {
		return (List<TypeOfExamination>) typeOfExaminationRepository.findAll();
	}
	
	@Transactional
	public boolean alreadyExists(TypeOfExamination typeOfExamination) {
		List<TypeOfExamination> typeOfExaminations = this.typeOfExaminationRepository.findByName(typeOfExamination.getName());
		if (typeOfExaminations.size() > 0)
			return true;
		else 
			return false;
	}
	@Transactional
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}

	@Transactional
		public TypeOfExamination typeOfExaminationById(Long id) {
			Optional<TypeOfExamination> optional = typeOfExaminationRepository.findById(id);
			if (optional.isPresent())
				return optional.get();
			else 
				return null;
		}
}
