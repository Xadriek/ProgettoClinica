package it.uniroma3.siw.spring.service;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Exam;
import it.uniroma3.siw.spring.model.Result;
import it.uniroma3.siw.spring.repository.ResultRepository;

@Service
public class ResultService {
	
	@Autowired
	private ResultRepository resultRepository;
	@Autowired
	private CredentialsService credentialsService;
	
	@Transactional
	public Result insert(Result result) {
		return resultRepository.save(result);
	}
	
	@Transactional
	public List<Result> allResult() {
		return (List<Result>) resultRepository.findAll();
	}
	
	@Transactional
	public boolean alreadyExists(Result result) {
		List<Result> results = this.resultRepository.findByName(result.getName());
		if (results.size() > 0)
			return true;
		else 
			return false;
	}
	@Transactional
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}

	@Transactional
	public Optional<Result> resultById(Long id) {
		return resultRepository.findById(id);
	}

	@Transactional
	public Optional<Result> resultByExam(Exam exam) {
		return resultRepository.findByExam(exam);
	}
}
