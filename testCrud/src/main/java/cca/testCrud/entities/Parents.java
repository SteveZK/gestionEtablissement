package cca.testCrud.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parents extends Membre{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TypeParents genre;
	
	@Column(length = 30)
	private String profession;
	
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Eleves> eleves;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Eleves> getEleves() {
		return eleves;
	}

	public void setEleves(List<Eleves> eleves) {
		this.eleves = eleves;
	}

	public TypeParents getGenre() {
		return genre;
	}

	public void setGenre(TypeParents genre) {
		this.genre = genre;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	
}
