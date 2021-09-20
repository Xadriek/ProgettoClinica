package it.uniroma3.siw.spring.model;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Data;

@Entity
@Data
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String surname;
	@Column(nullable=false)
	private String specialization;
	
	@OneToMany(mappedBy="doctor")
	@LazyCollection( LazyCollectionOption.TRUE )
	private List<Exam> exam;
	
	 @Column(nullable = true, length = 64)
	    private String photos;
	 
	 @Transient
	    public String getPhotosImagePath() {
	        if (photos == null || id == null) return null;
	         
	        return "/doctor-photos/" + id + "/" + photos;
	    }

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", surname=" + surname + ", specialization=" + specialization
				+ "]";
	}
	
	
	public String toStringPDF() {
		return "Doctor:  " + name + "  " + surname + "       specialization:  " + specialization;
	}

	
	 
	 
}
