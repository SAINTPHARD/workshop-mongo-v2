package com.robedson.workshopmongo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.robedson.workshopmongo.domain.User;
import com.robedson.workshopmongo.dto.UserDTO;
import com.robedson.workshopmongo.services.UserService;

@RestController 			// Indicar que essa classe e um Controller
@RequestMapping("/users") 	// Endpoints do Controller
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
                .stream()
                .map(UserDTO::new)
                .toList();

        return ResponseEntity.ok(list);
    }

    // ===============================
    // READ BY ID
    // GET /users/{id}
    // ===============================
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User user = service.findById(id);
        return ResponseEntity.ok(new UserDTO(user));
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
        		.created(uri)					// Status 201	
        		.body(new UserDTO(user));		// Retorna o usuario criado
    }

    // ===============================
    // UPDATE
    // PUT /users/{id}
    // ===============================
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(
            @PathVariable String id,			// pega o id da requisição
            @RequestBody UserDTO dto) {			// pega o corpo da requisição

        User user = service.update(service.fromDTO(dto));
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
