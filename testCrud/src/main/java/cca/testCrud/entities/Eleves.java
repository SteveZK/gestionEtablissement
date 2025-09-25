package cca.testCrud.entities;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Eleve")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Eleves extends Membre{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "matricule_Eleve", nullable = false, length = 50, unique = true)
	private String matriculeEleve;
	
	@Column(name = "salle", nullable = false, length = 30)
	private String nomSalle;
	
	@Column(length = 15)
	private String niveau;
	
	@ManyToMany(fetch = FetchType.LAZY) @JoinTable(name = "eleve_enseignnant",
			joinColumns = @JoinColumn(name = "eleve_id"),
			inverseJoinColumns = @JoinColumn(name = "enseignant_id"))
	private List<Enseignants> enseignants;
	
	@ManyToOne
	@JoinColumn(name = "parent_id", nullable = true)
	private Parents parent;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatriculeEleve() {
		return matriculeEleve;
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

	public List<Enseignants> getEnseignants() {
		return enseignants;
	}

	public void setEnseignants(List<Enseignants> enseignants) {
		this.enseignants = enseignants;
	}

	public Parents getParent() {
		return parent;
	}

	public void setParent(Parents parent) {
		this.parent = parent;
	}

	
}
