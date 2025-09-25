package cca.testCrud.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Enseignant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enseignants extends Membre{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "matricule_Enseignant", nullable = false, length = 50, unique = true)
	private String matriculeEnseignant;
	
	@Column(name = "nbre_Heure_Par_semaine", nullable = false)
	private int nbreHeureParSemaine;
	
	@Column(name = "nbre_Salle_Gerer", nullable = false)
	private int nbreSalleAGerer;
	
	@ManyToMany(mappedBy = "enseignants")
	private List<Eleves> eleves;
	
	

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

	public List<Eleves> getEleves() {
		return eleves;
	}

	public void setEleves(List<Eleves> eleves) {
		this.eleves = eleves;
	}

	
}
