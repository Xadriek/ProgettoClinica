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
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "exams")
public class Exam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate dateOfPrenotation;
	
	private String dateExamination;
	
	@OneToMany(mappedBy="exam",cascade=CascadeType.ALL)
	private List<Result> result;
	@ManyToOne
	private User patient;
	@ManyToOne
	private Doctor doctor;
	@ManyToOne
	private TypeOfExamination typeOfExamination;
	
	
	
	
}
