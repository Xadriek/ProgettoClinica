package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Requirement;
import it.uniroma3.siw.spring.model.TypeOfExamination;
import it.uniroma3.siw.spring.repository.RequirementRepository;

@Service
public class RequirementService {

	@Autowired
	private RequirementRepository requirementRepository;
	@Autowired
	private CredentialsService credentialsService;
	@Autowired
	private TypeOfExaminationService typeOfExaminationService;
	@Transactional
	public Requirement insert(Requirement requirement) {
		return requirementRepository.save(requirement);
	}

	@Transactional
	public List<Requirement> allRequirements() {
		return (List<Requirement>) requirementRepository.findAll();
	}

	@Transactional
	public boolean alreadyExists(Requirement requirement) {
		List<Requirement> requirements = this.requirementRepository.findByName(requirement.getName());
		if (requirements.size() > 0)
			return true;
		else 
			return false;
	}
	@Transactional
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}
	@Transactional
	public TypeOfExaminationService getTypeOfExaminationService() {
		return typeOfExaminationService;
	}
	@Transactional
	public Optional<Requirement> requirementById(Long id) {
		return requirementRepository.findById(id);
	}

	@Transactional
	public Optional<Requirement> requirementByTypeOfExamination(TypeOfExamination typeOfExamination) {
		return requirementRepository.findByTypeOfExamination(typeOfExamination);
	}
}
