package cca.testCrud.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
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

import cca.testCrud.DTOs.EnseignantDto;
import cca.testCrud.services.EnseignantService;

@Validated
@RestController
@RequestMapping("/apiEtablissement/Enseignants")
public class EnseignantController {
	
	
	public EnseignantService enseignantService;
	
    public EnseignantController(EnseignantService enseignantService, ModelMapper modelMapper) {
        this.enseignantService = enseignantService;
    }
	
	@PostMapping("/createEnseignant")
	public ResponseEntity<EnseignantDto> createEnseignant(@RequestBody EnseignantDto enseignantDto) {
		return ResponseEntity
                .status(201)
                .body(enseignantService.saveEnseignant(enseignantDto));
    }
	
	 @GetMapping("/ListEnseignant")
	    public ResponseEntity<List<EnseignantDto>> findAllEnseignant() {
		 
		 return ResponseEntity.ok(enseignantService.getAllEnseignant());
	    }
	 
	 @GetMapping("/{id}")
	    public ResponseEntity<EnseignantDto> findEnseignantById(@PathVariable("id") Long id) {
		 
		 return ResponseEntity.ok(enseignantService.getEnseignantById(id));
	    }
	 
	 @PutMapping("/editEnseignant/{id}")
	    public ResponseEntity<EnseignantDto> modifyEnseignant(@PathVariable("id") Long id,
	                                              @RequestBody EnseignantDto enseignantDto) {
		 
		 return ResponseEntity.ok(enseignantService.updateEnseignant(id, enseignantDto));
	    }
	 
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> dropEnseignant(@PathVariable("id") Long id) {
	        enseignantService.deleteEnseignant(id);
	        return ResponseEntity.noContent().build();
	    }
	 
	 @GetMapping("/EnseignantByEleve/{eleveId}")
		public ResponseEntity<List<EnseignantDto>> getEnseignantByEleve(@PathVariable("eleveId") Long eleveId) {

			return ResponseEntity.ok(enseignantService.enseignantParEleve(eleveId));
	    }
		
		@GetMapping("/EnseignantByMatricule")
		public ResponseEntity<List<EnseignantDto>> getEnseignantByEleve(@RequestParam(name = "matriculeEnseignant") String matriculeEnseignant) {

			  return ResponseEntity.ok(enseignantService.enseignantParMatricule(matriculeEnseignant));
	    }

}
