package cca.testCrud.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cca.testCrud.DTOs.EleveDto;
import cca.testCrud.entities.Eleves;
import cca.testCrud.entities.Enseignants;
import cca.testCrud.entities.Parents;
import cca.testCrud.repositories.EleveRepository;
import cca.testCrud.repositories.EnseignantRepository;
import cca.testCrud.repositories.ParentRepository;



@Service
public class EleveService {
	
	private EleveRepository eleveRepository;
	
	private EnseignantRepository enseignantRepository;
	
	private ParentRepository parentRepository;
	
	private ModelMapper modelMapper;
	
	public EleveService(EleveRepository eleveRepository, EnseignantRepository enseignantRepository,
            ParentRepository parentRepository, ModelMapper modelMapper) {
		this.eleveRepository = eleveRepository;
		this.enseignantRepository = enseignantRepository;
		this.parentRepository = parentRepository;
		this.modelMapper = modelMapper;
	}
	
	//persistance
	 	@Transactional
	 	public EleveDto createEleve(EleveDto eleveDto) {
	 		Eleves eleve = mapToEntity(eleveDto);
	        
	        if (eleveDto.getEnseignantId() != null) {
	            List<Enseignants> enseignants = enseignantRepository.findAllById(eleveDto.getEnseignantId());
	            eleve.setEnseignants(enseignants);
	        }
	        if (eleveDto.getParentId() != null) {
	            Parents parent = parentRepository.findById(eleveDto.getParentId())
	                    .orElseThrow(() -> new RuntimeException("Parent introuvable"));
	            eleve.setParent(parent);
	        }
	        
	        Eleves saved = eleveRepository.save(eleve);
	        
	        return mapToDto(saved);
		}
		
		//chargement
		public List<EleveDto> getAllEleve (){
			return eleveRepository.findAll()
	                .stream().map(this::mapToDto)
	                .collect(Collectors.toList());
		}
		
		//chargement par id
		public EleveDto getEleveById(Long id) {
			return mapToDto(findEntityById(id));
		}
		
		//mise a jour
		@Transactional
		public EleveDto  updateEleve (Long id, EleveDto  eleveDto) {
			Eleves existing = findEntityById(id);
	        
			existing.setAdresse(eleveDto.getAdresse());
			existing.setDateNaissance(eleveDto.getDateNaissance());
			existing.setLieuNaissance(eleveDto.getLieuNaissance());
			existing.setMatriculeEleve(eleveDto.getMatriculeEleve());
			existing.setNiveau(eleveDto.getNiveau());
			existing.setNom(eleveDto.getNom());
			existing.setNomSalle(eleveDto.getNomSalle());
			existing.setPrenom(eleveDto.getPrenom());
			existing.setTelephone(eleveDto.getTelephone());
			
			if (eleveDto.getEnseignantId() != null) {
	            List<Enseignants> enseignants = enseignantRepository.findAllById(eleveDto.getEnseignantId());
	            existing.setEnseignants(enseignants);
	        }
			Eleves updated = eleveRepository.save(existing);
	        return mapToDto(updated);
			
		}
		
		//suppression
		public void deleteEleve(Long id) {
			this.eleveRepository.deleteById(id);
		}
		
		
		public List<EleveDto> eleveParEnseignant(Long enseignantId) {
	        return eleveRepository.findByEnseignants_Id(enseignantId)
	                .stream().map(this::mapToDto).collect(Collectors.toList());
	    }
		
		public List<EleveDto> eleveParParent(Long parentId) {
	        return eleveRepository.findByParent_Id(parentId)
	                .stream().map(this::mapToDto).collect(Collectors.toList());
	    }
		
		public List<EleveDto> eleveParMatricule(String matriculeEleve) {
	        return eleveRepository.findByMatriculeEleve(matriculeEleve)
	                .stream().map(this::mapToDto).collect(Collectors.toList());
	    }
		
		private Eleves findEntityById(Long id) {
	        return eleveRepository.findById(id).orElseThrow(() -> new RuntimeException("Aucun élève avec l'id " + id));
	    }
		
		//Mapping de l'entité en Dto
		private EleveDto mapToDto(Eleves eleve) {
		    EleveDto elevedto = modelMapper.map(eleve, EleveDto.class);
		    if (eleve.getParent() != null) {
		        elevedto.setParentId(eleve.getParent().getId());
		    }
		    if (eleve.getEnseignants() != null) {
		        elevedto.setEnseignantId(
		            eleve.getEnseignants()
		             .stream()
		             .map(Enseignants::getId)
		             .collect(Collectors.toList())
		        );
		    }
		    return elevedto;
		}
		
		//Mapping du Dto en entité
		private Eleves mapToEntity(EleveDto dto) {
	        return modelMapper.map(dto, Eleves.class);
	    }
		
}
