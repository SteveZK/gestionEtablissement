package cca.testCrud.DTOs;

import java.util.List;

import cca.testCrud.entities.Membre;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EleveDto extends Membre{
	
	private Long id;
	
	@Nonnull
	private String matriculeEleve;
	
	@Nonnull
	private String nomSalle;
	
	@Nonnull
	private String niveau;
	
	private List<Long> enseignantId;
	
	@Nonnull
	private Long parentId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatriculeEleve() {
		return matriculeEleve;
	}
	
	

	public List<Long> getEnseignantId() {
		return enseignantId;
	}

	public void setEnseignantId(List<Long> enseignantId) {
		this.enseignantId = enseignantId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public void setMatriculeEleve(String matriculeEleve) {
		this.matriculeEleve = matriculeEleve;
	}

	public String getNomSalle() {
		return nomSalle;
	}

	public void setNomSalle(String nomSalle) {
		this.nomSalle = nomSalle;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	
	
	
}
