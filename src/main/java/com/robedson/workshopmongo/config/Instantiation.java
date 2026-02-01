package com.robedson.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.robedson.workshopmongo.domain.Post;
import com.robedson.workshopmongo.domain.User;
import com.robedson.workshopmongo.dto.AuthorDTO;
import com.robedson.workshopmongo.repository.PostRepository;
import com.robedson.workshopmongo.repository.UserRepository;

/**
 * Classe de configuração responsável por executar
 * a carga inicial de dados no MongoDB assim que a aplicação sobe.
 */
@Configuration
public class Instantiation implements CommandLineRunner {

    // Logger profissional (substitui System.out.println)
    private static final Logger LOGGER =
            LoggerFactory.getLogger(Instantiation.class);

    // Injeção de dependencia
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

    	// 0. Mensagem de Inicialização
        LOGGER.info("Iniciando carga inicial de dados no MongoDB...");

        // Configuração de datas no padrão ISO 8601 (UTC/GMT)
        SimpleDateFormat sdf =
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        // 1. Limpa as coleções para evitar duplicação a cada restart
        userRepository.deleteAll();
        postRepository.deleteAll();
        LOGGER.info("Coleções 'user' e 'post' limpas com sucesso.");

        // 2. Criação dos usuários
        User robedson = new User(null, "Robedson", "robedson@gmail.com");
        User gesse    = new User(null, "Gesse", "gesse@gmail.com");
        User madsen   = new User(null, "Madsen", "madsen@gmail.com");

        // 3. Persistência dos usuários
        userRepository.saveAll(Arrays.asList(robedson, gesse, madsen));
        LOGGER.info("Usuários salvos no banco de dados.");

        // 4. Criação dos posts com associação ao autor (AuthorDTO)
        Post post1 = new Post(
                null,
                sdf.parse("2018-03-21T00:00:00Z"),
                "The First Post",
                "I'm going to travel to São Paulo. Hugs.!",
                new AuthorDTO(robedson)
        );

        Post post2 = new Post(
                null,
                sdf.parse("2018-03-23T00:00:00Z"),
                "The Second Post",
                "I'm going to travel to Rio de Janeiro!",
                new AuthorDTO(robedson)
        );

        Post post3 = new Post(
                null,
                sdf.parse("2018-03-25T00:00:00Z"),
                "The Third Post",
                "I'm studing Spring Boot whit MongoDB!",
                new AuthorDTO(gesse)
        );

        // 5. Persistência dos posts
        postRepository.saveAll(Arrays.asList(post1, post2, post3));
        
        // 6. Associaççao dos posts aos usuarios
        robedson.getPosts().addAll(Arrays.asList(post1, post2));
        gesse.getPosts().addAll(Arrays.asList(post3));
        
        // Salva os usuários novamente para gravar essa referência no banco
        userRepository.save(robedson);
        userRepository.save(gesse);
        
        LOGGER.info("Referências entre Usuários e Posts atualizadas.");

        // Log final resumindo a carga
        LOGGER.info("------------------------------------------------------");
        LOGGER.info("CARGA DE DADOS CONCLUÍDA COM SUCESSO");
        LOGGER.info("Total de usuários criados: {}", userRepository.count());
        LOGGER.info("Total de posts criados: {}", postRepository.count());
        LOGGER.info("------------------------------------------------------");
        
    }
}
