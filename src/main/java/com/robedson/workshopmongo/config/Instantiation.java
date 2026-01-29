package com.robedson.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.robedson.workshopmongo.domain.Post;
import com.robedson.workshopmongo.domain.User;
import com.robedson.workshopmongo.dto.AuthorDTO;
import com.robedson.workshopmongo.repository.PostRepository;
import com.robedson.workshopmongo.repository.UserRepository;

/*
 * classe Instantiation - cria a carga inicial de dados no banco de dados
 */

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		// 1. Limpa as coleções para não duplicar dados
		userRepository.deleteAll();
		postRepository.deleteAll();

		// 2. Cria os Usuários
		User robedson = new User(null, "Robedson", "robedson@gmail.com");
		User gesse    = new User(null, "Gesse", "gesse@gmail.com");
		User madsen   = new User(null, "Madsen", "madsen@gmail.com");

		// 3. Salva os Usuários (Fundamental salvar ANTES de criar os posts)
		userRepository.saveAll(Arrays.asList(robedson, gesse, madsen));

		// 4. Cria os Posts associando os autores (variáveis user)
		Post post1 = new Post(null, sdf.parse("2018-03-21T00:00:00Z"), "Título do Post 1", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(robedson));
		Post post2 = new Post(null, sdf.parse("2018-03-23T00:00:00Z"), "Título do Post 2", "Acordei feliz hoje!", new AuthorDTO(robedson));
		Post post3 = new Post(null, sdf.parse("2018-03-25T00:00:00Z"), "Título do Post 3", "Estudando Spring Boot com MongoDB!", new AuthorDTO(gesse));

		// 5. Salva os Posts
		postRepository.saveAll(Arrays.asList(post1, post2, post3));
		
		System.out.println("------------------------------------------------------");
		System.out.println("CARGA DE DADOS CONCLUÍDA COM SUCESSO!");
		System.out.println("Usuários: " + userRepository.count());
		System.out.println("Posts: " + postRepository.count());
		System.out.println("------------------------------------------------------");
	}
}