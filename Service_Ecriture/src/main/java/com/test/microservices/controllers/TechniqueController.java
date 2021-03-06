package com.test.microservices.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.microservices.dto.TechniqueDto;
import com.test.microservices.mappers.TechniqueDtoToTechnique;
import com.test.microservices.pojos.Galerie;
import com.test.microservices.pojos.Technique;
import com.test.microservices.repositories.TechniqueRepository;

@RestController
public class TechniqueController {
	TechniqueRepository tsRepo;
	TechniqueDtoToTechnique mapper;
	public TechniqueController(TechniqueRepository repo,TechniqueDtoToTechnique m) {
		this.tsRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getTechniqueByIdMongo/{id}")
public ResponseEntity<TechniqueDto> getTechnique( @PathVariable String id) {
	if(tsRepo.existsByIdMongo(id)) {
		Technique ab=tsRepo.findByIdMongo( id);
		TechniqueDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<TechniqueDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<TechniqueDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getTechniqueById/{id}")
public ResponseEntity<TechniqueDto> getTechnique( @PathVariable int id) {
	if(tsRepo.existsById(id)) {
		Technique ab=tsRepo.findById( id);
		TechniqueDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<TechniqueDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<TechniqueDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllTechniques")
public ResponseEntity<List<TechniqueDto>> getTechnique( ) {
	List<Technique> lab=tsRepo.findAll();
	List<TechniqueDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<TechniqueDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addTechnique")
public ResponseEntity<TechniqueDto> addTechnique(@RequestBody TechniqueDto dto) {
	Page<Technique> c2 =tsRepo.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "ID")));
	int max=c2.getContent().get(0).getId();
	System.out.println("Id max: "+max);
		Technique ab=mapper.dtoToObject(dto);
		ab.setId(max+1);
		dto.setId(max+1);
		tsRepo.save(ab);
		return new ResponseEntity<TechniqueDto>(dto,HttpStatus.CREATED);
	
}
@PutMapping("/updateTechnique/{id}")
public ResponseEntity<TechniqueDto> updateTechnique(@PathVariable int id,@RequestBody TechniqueDto dto) {
	String idMongo=tsRepo.findById(id).getIdMongo();
	
	tsRepo.deleteById(idMongo);
		Technique ab=mapper.dtoToObject(dto);
		tsRepo.save(ab);
		return new ResponseEntity<TechniqueDto>(dto,HttpStatus.OK);
}
@DeleteMapping("/deleteTechnique/{id}")
public ResponseEntity<TechniqueDto> deleteTechnique(@PathVariable int id) {
	if(tsRepo.existsById(id)) {
		Technique ab=tsRepo.deleteById(id);
		TechniqueDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<TechniqueDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<TechniqueDto>(HttpStatus.NOT_FOUND);
}



}
