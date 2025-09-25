package cca.testCrud.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cca.testCrud.DTOs.EnseignantDto;
import cca.testCrud.entities.Eleves;
import cca.testCrud.entities.Enseignants;
import cca.testCrud.repositories.EleveRepository;
import cca.testCrud.repositories.EnseignantRepository;

@Service
public class EnseignantService {
	
	
	private EnseignantRepository enseignantRepository;
	
	private EleveRepository eleveRepository;
	
	private ModelMapper modelMapper;
	
	public  EnseignantService(EnseignantRepository enseignantRepository, ModelMapper modelMapper, 
			EleveRepository eleveRepository) {
		this.enseignantRepository = enseignantRepository;
		this.modelMapper = modelMapper;
		this.eleveRepository = eleveRepository;
	}
	
	//persistance
	@Transactional
		public EnseignantDto  saveEnseignant (EnseignantDto  enseignantDto) {
			Enseignants enseignants = modelMapper.map(enseignantDto, Enseignants.class);
			
			if (enseignantDto.getEleveId() != null) {
	            List<Eleves> eleves = eleveRepository.findAllById(enseignantDto.getEleveId());
	            enseignants.setEleves(eleves);
	        }
			
	        return mapToDto(enseignantRepository.save(enseignants));
		}
		
		//chargement
		public List<EnseignantDto> getAllEnseignant (){
			return enseignantRepository.findAll()
                    .stream().map(this::mapToDto)
                    .collect(Collectors.toList());
		}
		
		//chargement par id
		public EnseignantDto  getEnseignantById(Long id) {
			Enseignants enseignant = enseignantRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Enseignant non trouvé"));
			return mapToDto(enseignant);
		}
		
		//mise a jour
		@Transactional
		public EnseignantDto  updateEnseignant (Long id, EnseignantDto  enseignantDto) {
			Enseignants existing = enseignantRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Enseignant non trouvé"));
	     existing.setAdresse(enseignantDto.getAdresse());
	     existing.setDateNaissance(enseignantDto.getDateNaissance());
	     existing.setLieuNaissance(enseignantDto.getLieuNaissance());
	     existing.setMatriculeEnseignant(enseignantDto.getMatriculeEnseignant());
	     existing.setNbreHeureParSemaine(enseignantDto.getNbreHeureParSemaine());
	     existing.setNbreSalleAGerer(enseignantDto.getNbreSalleAGerer());
	     existing.setNom(enseignantDto.getNom());
	     existing.setPrenom(enseignantDto.getPrenom());
	     existing.setTelephone(enseignantDto.getTelephone());
	     
	     if (enseignantDto.getEleveId() != null) {
	            List<Eleves> eleves = eleveRepository.findAllById(enseignantDto.getEleveId());
	            existing.setEleves(eleves);
	        }
	     Enseignants updated = enseignantRepository.save(existing);
	     return mapToDto(updated);
			
		}
		
		//suppression
		public void deleteEnseignant(Long id) {
			this.enseignantRepository.deleteById(id);;
		}
		
		public List<EnseignantDto> enseignantParEleve(Long eleveId) {
			return enseignantRepository.findByEleves_Id(eleveId)
                    .stream().map(this::mapToDto)
                    .collect(Collectors.toList());
	    }
		
		public List<EnseignantDto> enseignantParMatricule(String matriculeEnseignant) {
			return enseignantRepository.findByMatriculeEnseignant(matriculeEnseignant)
                    .stream().map(this::mapToDto)
                    .collect(Collectors.toList());
	    }
		
		 private EnseignantDto mapToDto(Enseignants e) {
		        return modelMapper.map(e, EnseignantDto.class);
		    }

}
