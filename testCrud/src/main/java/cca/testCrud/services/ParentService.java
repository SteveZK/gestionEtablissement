package cca.testCrud.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cca.testCrud.DTOs.ParentDto;
import cca.testCrud.entities.Eleves;
import cca.testCrud.entities.Parents;
import cca.testCrud.repositories.EleveRepository;
import cca.testCrud.repositories.ParentRepository;

@Service
public class ParentService {
	
	private ParentRepository parentRepository;
	
	private EleveRepository eleveRepository;
	
	private ModelMapper modelMapper;
	
	public  ParentService(ParentRepository parentRepository, EleveRepository eleveRepository, ModelMapper modelMapper) {
		this.parentRepository = parentRepository;
		this.eleveRepository = eleveRepository;
		this.modelMapper = modelMapper;
	}
	
	//persistance
		@Transactional
		public ParentDto  saveParent (ParentDto  parentDto) {
			Parents parent = modelMapper.map(parentDto, Parents.class);
	        if (parentDto.getEleveId() != null) {
	            List<Eleves> eleves = eleveRepository.findAllById(parentDto.getEleveId());
	            parent.setEleves(eleves);
	        }
	        Parents saved = parentRepository.save(parent);
	        return mapToDto(saved);
		}
		
		//chargement
		public List<ParentDto> getAllParent (){
			return parentRepository.findAll()
	                .stream()
	                .map(this::mapToDto)
	                .collect(Collectors.toList());
		}
		
		//chargement par id
		public ParentDto  getParentById(Long id) {
			Parents parent = parentRepository.findById(id).orElseThrow(() -> new RuntimeException("Parent introuvable pour l'id : " + id));
	        return mapToDto(parent);
		}
		
		//mise a jour
		@Transactional
		public ParentDto  updateParent (Long id, ParentDto  parentDto) {
			Parents existing = parentRepository.findById(id).orElseThrow(() -> new RuntimeException("Parent introuvable pour l'id : " + id));
				existing.setAdresse(parentDto.getAdresse());
				existing.setDateNaissance(parentDto.getDateNaissance());
				existing.setLieuNaissance(parentDto.getLieuNaissance());
				existing.setNom(parentDto.getNom());
				existing.setPrenom(parentDto.getPrenom());
				existing.setTelephone(parentDto.getTelephone());
				existing.setProfession(parentDto.getProfession());
				existing.setGenre(parentDto.getGenre());
				
				
				if (parentDto.getEleveId() != null) {
		            List<Eleves> eleves = eleveRepository.findAllById(parentDto.getEleveId());
		            existing.setEleves(eleves);
		        }
				
				
				Parents updated = parentRepository.save(existing);
		        return mapToDto(updated);
			
		}
		
		//suppression
		public void deleteParent(Long id) {
			this.parentRepository.deleteById(id);
		}
		
		public List<ParentDto> parentParEleve(Long eleveId) {
	        return parentRepository.findByEleves_Id(eleveId)
	                .stream().map(this::mapToDto).collect(Collectors.toList());
	    }
		
		private ParentDto mapToDto(Parents parent) {
	        ParentDto parentDto = modelMapper.map(parent, ParentDto.class);
	        if (parent.getEleves() != null) {
	            parentDto.setEleveId(
	                parent.getEleves()
	                      .stream()
	                      .map(Eleves::getId)
	                      .collect(Collectors.toList())
	            );
	        }
	        return parentDto;
	    }
		

}
