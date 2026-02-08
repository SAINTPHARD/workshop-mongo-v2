package com.robedson.workshopmongo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.robedson.workshopmongo.domain.User;
import com.robedson.workshopmongo.dto.UserDTO;
import com.robedson.workshopmongo.services.UserService;

@RestController 			// Indicar que essa classe e um Controller
@RequestMapping("/users") 	// Endpoints do Controller

/**
 * UserResource é a camada de recursos (controladores REST).
 */
public class UserResource {

    @Autowired
    private UserService service;

    // ===============================
    // READ ALL
    // GET /users
    // ===============================
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> list = service.findAll()
                .stream() // para pegar cada user
                .map(UserDTO::new) // pega cada user e transforma em um UserDTO
                .toList();

        return ResponseEntity.ok(list);
    }

    // ===============================
    // READ BY ID
    // GET /users/{id}
    // ===============================
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable String id) {
        User user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }

    // ===============================
    // CREATE
    // POST /users
    // ===============================
    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO dto) {
        User user = service.insert(service.fromDTO(dto));	// converte DTO pra entidade user

        URI uri = ServletUriComponentsBuilder	// URI = uniform resource identifier( pega url da requisição atual: 
                .fromCurrentRequest()			// para criar a URI do novo recurso criado: http://localhost:8080/users
                .path("/{id}")					// adiciona o id ai final: http://localhost8080/users/123
                .buildAndExpand(user.getId())	// Pega o ID do novo usuário (ex: 123) e troca pelo {id}.
                .toUri();						// Transforma esse texto em um objeto URI oficial do Java

        return ResponseEntity					// Retorna HTTP seguindo o padrao REST
        		.created(uri)					// Status, código 201
        		.body(new UserDTO(user));		// Retorna o usuario criado em formato DTO (sem os posts)
    }

    // ===============================
    // UPDATE
    // PUT /users/{id}
    // ===============================
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody UserDTO dto) {
        User user = service.fromDTO(dto);
        
        //Garantir que o objeto user tenha o ID da URL
        user.setId(id);
        
        user = service.update(user);
        return ResponseEntity.ok(new UserDTO(user));
    }

    // ===============================
    // DELETE
    // DELETE /users/{id}
    // ===============================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
