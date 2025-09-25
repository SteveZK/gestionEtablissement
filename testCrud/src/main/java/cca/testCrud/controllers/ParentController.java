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
import org.springframework.web.bind.annotation.RestController;

import cca.testCrud.DTOs.ParentDto;
import cca.testCrud.services.ParentService;

@Validated
@RestController
@RequestMapping("/apiEtablissement/Parents")
public class ParentController {
	
	public ParentService parentService;
	
	public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }
	
	@PostMapping("/createParent")
	public ResponseEntity<ParentDto> createParent(@RequestBody ParentDto parentDto) {
		
		ParentDto response = parentService.saveParent(parentDto);
		
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
	
	 @GetMapping("/ListParent")
	    public ResponseEntity<List<ParentDto>> findAllParent() {
		 
		 return ResponseEntity.ok(parentService.getAllParent());
	    }
	 
	 @GetMapping("/{id}")
	    public ResponseEntity<ParentDto> findParentById(@PathVariable("id") Long id) {
		 
		 return ResponseEntity.ok(parentService.getParentById(id));
	    }
	 
	 @PutMapping("/editParent/{id}")
	    public ResponseEntity<ParentDto> modifyParent(@PathVariable("id") Long id, @RequestBody ParentDto parentDto) {
		 
		 return ResponseEntity.ok(parentService.updateParent(id, parentDto));
	    }
	 
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> dropParent(@PathVariable("id") Long id) {
	        parentService.deleteParent(id);
	        return ResponseEntity.noContent().build();
	    }
	 
	 @GetMapping("/ParentByEleve/{eleveId}")
		public ResponseEntity<List<ParentDto>> getparentByEleve(@PathVariable("eleveId") Long eleveId) {

			return ResponseEntity.ok(parentService.parentParEleve(eleveId));
	    }

}
