package cca.testCrud.DTOs;

import java.util.List;


import cca.testCrud.entities.Membre;
import jakarta.annotation.Nonnull;

public class EnseignantDto extends Membre{
	
	private Long id;
	
	@Nonnull
	private String matriculeEnseignant;
	
	@Nonnull
	private int nbreHeureParSemaine;
	
	@Nonnull
	private int nbreSalleAGerer;
	
	private List<Long> eleveId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatriculeEnseignant() {
		return matriculeEnseignant;
	}

	public void setMatriculeEnseignant(String matriculeEnseignant) {
		this.matriculeEnseignant = matriculeEnseignant;
	}

	public int getNbreHeureParSemaine() {
		return nbreHeureParSemaine;
	}

	public void setNbreHeureParSemaine(int nbreHeureParSemaine) {
		this.nbreHeureParSemaine = nbreHeureParSemaine;
	}

	public int getNbreSalleAGerer() {
		return nbreSalleAGerer;
	}

	public void setNbreSalleAGerer(int nbreSalleAGerer) {
		this.nbreSalleAGerer = nbreSalleAGerer;
	}

	public List<Long> getEleveId() {
		return eleveId;
	}

	public void setEleveId(List<Long> eleveId) {
		this.eleveId = eleveId;
	}
	
	

}
