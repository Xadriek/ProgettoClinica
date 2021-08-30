package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class TypeOfExamination {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String description;
	private Long cost;
	@OneToMany(mappedBy="typeOfExamination",cascade = CascadeType.ALL)
	private List<Requirement> requirements;
	
	@OneToMany(mappedBy="typeOfExamination",cascade = CascadeType.ALL)
	private List<Exam> exams;
	
	
}
