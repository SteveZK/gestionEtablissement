package cca.testCrud.DTOs;

import java.util.List;

import cca.testCrud.entities.Membre;
import cca.testCrud.entities.TypeParents;
import jakarta.annotation.Nonnull;

public class ParentDto extends Membre{
	
	private Long id;
	
	@Nonnull
	private TypeParents genre;
	
	@Nonnull
	private String profession;
	
	private List<Long> eleveId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TypeParents getGenre() {
		return genre;
	}

	public void setGenre(TypeParents genre) {
		this.genre = genre;
	}

	public List<Long> getEleveId() {
		return eleveId;
	}

	public void setEleveId(List<Long> eleveId) {
		this.eleveId = eleveId;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	

}
