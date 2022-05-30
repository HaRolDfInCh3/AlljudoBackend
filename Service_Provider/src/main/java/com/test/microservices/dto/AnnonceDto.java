package com.test.microservices.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AnnonceDto {
	public String idMongo;
	public int id;
	public int sous_categorie_ID;
	public int user_ID;
	public String titre;
	private String login;
	public String descriptif;
	public java.util.Date date_publication;
	public java.util.Date date_modification;
	public String nom;
	public String mail;
	public String code_postal;
	public String ville;
	public String pays;
	public String telephone;
	public boolean Premium;
	private Sous_categorieDto sous_categorie2;
	private UserDto user2;
	public boolean Valide;
}
