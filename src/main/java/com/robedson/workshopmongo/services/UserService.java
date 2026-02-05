package com.robedson.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robedson.workshopmongo.domain.User;
import com.robedson.workshopmongo.dto.UserDTO;
import com.robedson.workshopmongo.repository.UserRepository;
import com.robedson.workshopmongo.services.exception.ObjectNotFoundException;

/**
 * Camada de serviço (regras de negócio).
 * O Service contém a lógica de negócio da aplicação.
 * O Controller chama o Service e o Service chama o Repository.
 * @Service indica que essa classe é um serviço do Spring.
 */

// SERVICE (regras de negócio  = Segurança, validações, etc)
@Service	// Indica que essa classe é um serviço do Spring.
public class UserService {

	// 0. Injeção de dependência do UserRepository para acessar os dados dos usuários no MongoDB.
	@Autowired
	private UserRepository userRepository;

	// 1. Busca todos os usuários no banco de dados.
	// READ - findAll(Todos) 
	// GET /users, Implemntado na classe UserReource	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	// 2. Busca um usuário por ID.
	// READ - por ID
	// GET /usera/{id}, implementado na classe UserResource
	public User findById(String id) {
		Optional<User> user = userRepository.findById(id);
		
		// Se o Optional tiver valor, retorna o usuário
		if (user.isPresent()) {
			return user.get();
		} else {
			// Se não tiver valor, lança a exceção personalizada.
			throw new ObjectNotFoundException("Usuário não encontrado; Id: " + id);
		}
	}
	
	// 3. Insere um novo usuário no banco de dados.
	// CREATE - INSERT
	// POST /users (impl na classe UserResource)
	public User insert(User user) {
		return userRepository.insert(user);
	}

	// 3.1 Converte um UserDTO para um objeto User.
	// fromDTO = 
	public User fromDTO(UserDTO objDto) {
		// Cria um novo objeto User a partir do UserDTO
		User user = new User();					// cria um objeto vazio da entidade.
		user.setId(objDto.getId());
		user.setName(objDto.getName());
		user.setEmail(objDto.getEmail());
		return user;
	}
	
	// UPDATE
	// PUT /users/{id}
	public User update(User user) {
		User newUser = findById(user.getId());
		updateData(newUser, user);
		return userRepository.save(newUser);
		
	}

	// método auxiliar para atualiza o novo usuario
	private void updateData(User newUser, User user) {
		newUser.setEmail(user.getName());
		newUser.setEmail(user.getEmail());
		
		
		/**
		 * User newUser é o usuário que veio do Banco de Dados (versão antiga)
		 * User user é o usuário com os dados novos que você enviou no Postman
		 * newUser: É o usuário que veio do Banco de Dados (versão antiga)
		 * É o usuário com os dados novos que você enviou no Postman
		 * O método pega o Nome e Email do obj e joga dentro do newUser
		 */
	}
	
	// DELETE
	// DELETE /users/{id}, implementado na userRessource
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
}