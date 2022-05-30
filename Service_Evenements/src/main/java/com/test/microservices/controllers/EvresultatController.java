package com.test.microservices.controllers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.test.microservices.dto.EvresultatDto;
import com.test.microservices.mappers.EvresultatDtoToEvresultat;
import com.test.microservices.pojos.Evresultat;
import com.test.microservices.pojos.Palmares;
import com.test.microservices.repositories.EvresultatRepository;

@RestController
public class EvresultatController {
	EvresultatDtoToEvresultat mapper;
	EvresultatRepository objetRepo;
	public EvresultatController(EvresultatRepository repo,EvresultatDtoToEvresultat m) {
		this.objetRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getEvresultatByIdMongo/{id}")
public ResponseEntity<EvresultatDto> getEvresultat( @PathVariable String id) {
	if(objetRepo.existsByIdMongo(id)) {
		Evresultat ab=objetRepo.findByIdMongo( id);
		EvresultatDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<EvresultatDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvresultatDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getEvresultatById/{id}")
public ResponseEntity<EvresultatDto> getEvresultat( @PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Evresultat ab=objetRepo.findById( id);
		EvresultatDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<EvresultatDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvresultatDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getPalmaresById/{id}")
public ResponseEntity<List<Palmares>> getPalmares( @PathVariable int id) {
	if(objetRepo.existsById(id)) {
		List<Palmares> liste=objetRepo.aggregateBySample();
		return new ResponseEntity<List<Palmares>>(liste,HttpStatus.OK);
	}
	return new ResponseEntity<List<Palmares>>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllEvresultats")
public ResponseEntity<List<EvresultatDto>> getEvresultat( ) {
	List<Evresultat> lab=objetRepo.findAll();
	List<EvresultatDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<EvresultatDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getAllEvresultatsByEventId/{id}")
public ResponseEntity<List<EvresultatDto>> getEvresultatByEventId(@PathVariable int id ) {
	if(objetRepo.existsByEvenementID(id)) {
		List<Evresultat> lab=objetRepo.findAllByEvenementID(id);
		List<EvresultatDto> ldto=mapper.objectsToDtos(lab);
		return new ResponseEntity<List<EvresultatDto>>(ldto,HttpStatus.OK);
	}
	return new ResponseEntity<List<EvresultatDto>>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllEvresultatsByChampionId/{id}")
public ResponseEntity<List<EvresultatDto>> getEvresultatByChampionId(@PathVariable int id ) {
	if(objetRepo.existsByEvenementID(id)) {
		List<Evresultat> lab=objetRepo.findAllByChampionID(id);
		List<EvresultatDto> ldto=mapper.objectsToDtos(lab);
		return new ResponseEntity<List<EvresultatDto>>(ldto,HttpStatus.OK);
	}
	return new ResponseEntity<List<EvresultatDto>>(HttpStatus.NOT_FOUND);
}
@PostMapping("/addEvresultat")
public ResponseEntity<EvresultatDto> addEvresultat(@RequestBody EvresultatDto dto) {
	if(!objetRepo.existsById(dto.getId())) {
		Evresultat ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<EvresultatDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<EvresultatDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateEvresultat/{id}")
public ResponseEntity<EvresultatDto> updateEvresultat(@PathVariable int id,@RequestBody EvresultatDto dto) {
	if(objetRepo.existsById(id)) {
		Evresultat ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<EvresultatDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvresultatDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteEvresultat/{id}")
public ResponseEntity<EvresultatDto> deleteEvresultat(@PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Evresultat ab=objetRepo.deleteById(id);
		EvresultatDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<EvresultatDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvresultatDto>(HttpStatus.NOT_FOUND);
}
@PostMapping("/uploadResult")
public void handleFileUpload(@RequestPart(value="files[]", required = true) final MultipartFile[] files) {
	System.out.println("Nom original: " + files[0].getOriginalFilename() + "!");
	System.out.println("Type de contenu: " + files[0].getContentType());
	System.out.println("Nom: " + files[0].getName());
	System.out.println("Taille: " + files[0].getSize());
	String content;
	try {
		content = new String(files[0].getBytes(), StandardCharsets.UTF_8);
		System.out.println("Contenu" + content);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

}


}
