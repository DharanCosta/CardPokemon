package com.cardpokemon;



import com.cardpokemon.models.PkmCardModel;
import com.cardpokemon.service.CardService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class CardGamePokemonApplication {
	public static void main(String[] args) {
		SpringApplication.run(CardGamePokemonApplication.class, args);
	}
	// GENERATE COMPLETE POKÉDEX

	@Bean
	CommandLineRunner runner(CardService cardService) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<PkmCardModel>> typeReference = new TypeReference<List<PkmCardModel>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/db.json");
			try {
				List<PkmCardModel> cards = mapper.readValue(inputStream,typeReference);
				cardService.save(cards);
				System.out.println("Cards Saved!");
			} catch (IOException e){
				System.out.println("Unable to save Cards: " + e.getMessage());
			}
		};}

}
