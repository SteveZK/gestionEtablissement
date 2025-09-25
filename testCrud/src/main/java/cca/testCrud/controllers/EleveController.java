package cca.testCrud.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cca.testCrud.DTOs.EleveDto;
import cca.testCrud.services.EleveService;

@Validated
@RestController
@RequestMapping("/apiEtablissement/Eleves")
public class EleveController {
	
	public EleveService eleveService;
	
	public EleveController(EleveService eleveService) {
        this.eleveService = eleveService;
    }
	
	@PostMapping("/createEleve")
	public ResponseEntity<EleveDto> createEleve(@RequestBody EleveDto eleveDto) {
		
        return new ResponseEntity<>(eleveService.createEleve(eleveDto), HttpStatus.CREATED);
    }
	
	
	 @GetMapping("/ListEleve")
	    public List<EleveDto> findAllEleves() {
		 
		 return eleveService.getAllEleve();
	    }
	 
	 @GetMapping("/{id}")
	    public EleveDto findEleveById(@PathVariable("id") Long id) {
	        
	        return eleveService.getEleveById(id);
	    }
	 
	 @PutMapping("/editEleve/{id}")
	    public EleveDto modifyEleve(@PathVariable("id") Long id, @RequestBody EleveDto eleveDto) {
		 
		 return eleveService.updateEleve(id, eleveDto);
	    }
	 
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> dropEleve(@PathVariable("id") Long id) {
	        eleveService.deleteEleve(id);
	        return ResponseEntity.noContent().build();
	    }
	 
	 @GetMapping("/EleveByParent/{parentId}")
		public List<EleveDto> getElevesByParent(@PathVariable("parentId") Long parentId) {

			return eleveService.eleveParParent(parentId);
	    }
		
		@GetMapping("/EleveByEnseignant/{enseignantId}")
		public List<EleveDto> getElevesByEnseignant(@PathVariable("enseignantId") Long enseignantId) {

			return eleveService.eleveParEnseignant(enseignantId);
	    }
		
		@GetMapping("/EleveByMatricule")
		public List<EleveDto> getElevesByMatricule(@RequestParam(name = "matriculeEleve") String matriculeEleve) {

			return eleveService.eleveParMatricule(matriculeEleve);
	    }

}
