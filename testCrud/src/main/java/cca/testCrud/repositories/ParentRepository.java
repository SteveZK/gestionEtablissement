package cca.testCrud.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cca.testCrud.entities.Parents;


public interface ParentRepository extends JpaRepository<Parents, Long>{
	
	List<Parents> findByEleves_Id(Long eleveId);
	
	

}
