package cca.testCrud.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cca.testCrud.entities.Eleves;



@Repository
public interface EleveRepository extends JpaRepository<Eleves, Long>{
	
	List<Eleves> findByEnseignants_Id(Long enseignantId);
	
	List<Eleves> findByParent_Id(Long parentId);
	
	List<Eleves> findByMatriculeEleve(String matriculeEleve);
	
	
	
	
}
