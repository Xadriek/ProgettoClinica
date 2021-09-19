package it.uniroma3.siw.spring.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.OneToMany;


import lombok.Data;

@Entity
@Data
@Table(name = "users") // cambiamo nome perchè in postgres user e' una parola riservata
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cognome;
	
	@OneToMany(mappedBy="patient",cascade= {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Exam> exam;

	

}
