package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Doctor;
import it.uniroma3.siw.spring.model.Exam;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.repository.ExamRepository;

@Service
public class ExamService {
	
	
		
		@Autowired
		private ExamRepository examRepository; 
		@Autowired
		private UserService userService; 
		@Autowired
		private DoctorService doctorService; 
		@Autowired
		private TypeOfExaminationService typeOfExaminationService; 
		@Autowired
		private CredentialsService credentialsService;
		
		@Transactional
		public Exam insert(Exam exam) {
			return examRepository.save(exam);
		}

		@Transactional
		public List<Exam> allExams() {
			return (List<Exam>) examRepository.findAll();
		}

		@Transactional
		public Exam examById(Long id) {
			Optional<Exam> optional = examRepository.findById(id);
			if (optional.isPresent())
				return optional.get();
			else 
				return null;
		}
		@Transactional
		public List<Exam> examByPatient(User patient) {
			Optional<List<Exam>> optional = examRepository.findByPatient(patient);
			if (optional.isPresent())
				return optional.get();
			else 
				return null;
		}
		@Transactional
		public List<Exam> examByDoctor(Doctor doctor) {
			Optional<List<Exam>> optional = examRepository.findByDoctor(doctor);
			if (optional.isPresent())
				return optional.get();
			else 
				return null;
		}

		@Transactional
		public boolean alreadyExists(Exam exam) {
			Optional<Exam> exams = this.examRepository.findById(exam.getId());
			if (exams.isPresent())
				return true;
			else 
				return false;
		}

		public UserService getUserService() {
			return this.userService;
		}
		public DoctorService getDoctorService() {
			return this.doctorService;
		}
		public TypeOfExaminationService getTypeOfExaminationService() {
			return this.typeOfExaminationService;
		}
		@Transactional
		public CredentialsService getCredentialsService() {
			return credentialsService;
		}
}
