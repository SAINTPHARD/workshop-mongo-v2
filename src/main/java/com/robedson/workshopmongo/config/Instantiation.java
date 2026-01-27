package com.robedson.workshopmongo.config;

import java.text.ParseException;
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

@Configuration
public class Instantiation implements CommandLineRunner {

	// 1. Injeção de dependência dos repositórios
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Iniciando a instanciação de dados...");
		System.out.println("==========================================");
		System.out.println("CARGA INICIAL: RODANDO E CRIANDO O BANCO NOVO...");
		System.out.println("==========================================");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		// 1. Limpa o banco para garantir que não haja dados duplicados ou antigos
		postRepository.deleteAll();
		userRepository.deleteAll();

		// 2. Criar os usuários com os SEUS DADOS e IDs FIXOS
		// Ao passar o ID no construtor, o MongoDB vai forçar o uso deste ID.
		User robedson = new User("69703015104c1427a07ba3d5", "Robedson", "robedson@gmail.com");
		User gesse    = new User("69703015104c1427a07ba3d6", "Gesse", "gesse@gmail.com");
		User madsen   = new User("69703015104c1427a07ba3d7", "Madsen", "madsen@gmail.com");

		/*
		// Ou adicionar usuarios com IDs gerados automaticamente pelo MongoDB
		User user1 = new User(null, "User1", "user1@gmail.com");
		User user2 = new User(null, "User2", "user2@gmail.com");
		User user3 = new User(null, "User3", "user3@gmail.com");

		// Salva os usuários
		userRepository.saveAll(Arrays.asList(robedson, gesse, madsen, user1, user2, user3));
		*/
		
		// 3. Criar posts associados a esses usuários
		// Distribuí os posts entre eles para testar
		Post post1 = createPost(sdf, "2018-03-21T00:00:00Z", "Título do Post 1", "Vou viajar para São Paulo. Abraços!", robedson);
		Post post2 = createPost(sdf, "2018-03-23T00:00:00Z", "Título do Post 2", "Acordei feliz hoje!", robedson);
		Post post3 = createPost(sdf, "2018-03-25T00:00:00Z", "Título do Post 3", "Estudando Spring Boot com MongoDB!", gesse);

		// Salva os posts
		postRepository.saveAll(Arrays.asList(post1, post2, post3));
	}

	// Método auxiliar mantido igual
	private Post createPost(SimpleDateFormat sdf, String date, String title, String content, User author)
			throws ParseException {

		AuthorDTO authorDto = new AuthorDTO(author);

		return new Post(
				null, // ID do post continua null para ser gerado automático
				sdf.parse(date),
				title,
				content,
				authorDto
		);
	}
}