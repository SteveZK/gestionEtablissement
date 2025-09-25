package cca.testCrud.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cca.testCrud.entities.Enseignants;


@Repository
public interface EnseignantRepository extends JpaRepository<Enseignants, Long>{
	
	List<Enseignants> findByEleves_Id(Long eleveId);
	
	List<Enseignants> findByMatriculeEnseignant(String matriculeEnseignant);
	

}
