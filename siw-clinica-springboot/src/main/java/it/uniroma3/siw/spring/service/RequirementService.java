package it.uniroma3.siw.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Requirement;
import it.uniroma3.siw.spring.repository.RequirementRepository;

@Service
public class RequirementService {
	
	@Autowired
	private RequirementRepository requirementRepository;
	
	@Transactional
	public Requirement insert(Requirement requirement) {
		return requirementRepository.save(requirement);
	}
	
	@Transactional
	public List<Requirement> allRequirement() {
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
}
