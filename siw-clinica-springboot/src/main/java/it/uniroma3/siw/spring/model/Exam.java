package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Exam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate dateOfPrenotation;
	
	private String dateExamination;
	
	@OneToMany(mappedBy="exam",cascade=CascadeType.ALL)
	private List<Result> result;
	@ManyToOne(cascade=CascadeType.ALL)
	private User patient;
	@ManyToOne(cascade=CascadeType.ALL)
	private Doctor doctor;
	@ManyToOne(cascade=CascadeType.ALL)
	private TypeOfExamination typeOfExamination;
	
	public Exam(Long id, LocalDate dateOfPrenotation, String dateExamination, List<Result> result, User patient,
			Doctor doctor, TypeOfExamination typeOfExamination) {
		super();
		this.id = id;
		this.dateOfPrenotation = dateOfPrenotation;
		this.dateExamination = dateExamination;
		this.result = result;
		this.patient = patient;
		this.doctor = doctor;
		this.typeOfExamination = typeOfExamination;
	}

	public Exam() {
		super();
	}
	
	
}
