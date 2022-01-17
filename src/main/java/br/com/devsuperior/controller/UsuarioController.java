package br.com.devsuperior.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.devsuperior.model.Usuario;
import br.com.devsuperior.repositories.UsuarioRepository;



@RestController
public class UsuarioController {

	@Autowired /* Injeção de dependência */
	private UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/salvaNome/{nome}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String retornaNome(@PathVariable String nome) {

		Usuario u1 = new Usuario();
		u1.setNome(nome);
		usuarioRepository.save(u1);/* Grava no bando de dados */

		return "Curso Spring Boot API " + nome;
	}

	@GetMapping(value = "listaTodos") /* Primeiro método de api/buscar */
	@ResponseBody /* Retorna os dados para o corpo da resposta */
	public ResponseEntity<List<Usuario>> listaUsuario() {
		List<Usuario> usuarios = usuarioRepository.findAll();/* Executa a consulta no banco de dados */
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);/* Retorna lista em JSON */

	}

	@PostMapping(value = "salvar") /* Mapeia a url */
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {/* Recebe os dados para salvar */
		Usuario user = usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);

	}

	@DeleteMapping(value = "delete") /* Mapeia a url */
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<String> delete(@RequestParam Long iduser) {/* Recebe os dados para deletar */
		usuarioRepository.deleteById(iduser);
		return new ResponseEntity<String>("Usuário deletado com sucesso! ", HttpStatus.OK);

	}

	@GetMapping(value = "buscarUserId") /* Mapeia a url */
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<Usuario> buscaruserid(
			@RequestParam(name = "iduser") Long iduser) {/* Recebe os dados para consultar */
		Usuario usuario = usuarioRepository.findById(iduser).get();
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

	}

	@PutMapping(value = "atualizar") /* Mapeia a url */
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) {/* Recebe os dados para atualizar */
		if (usuario.getId() == null) {
			return new ResponseEntity<String>("Id não foi informado para atualização!", HttpStatus.OK);
		}
		Usuario user = usuarioRepository.saveAndFlush(usuario);
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);

	}

	@GetMapping(value = "buscarPorNome") /* Mapeia a url */
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name) {/* Recebe os dados para buscar por nome */
		List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);

	}
	
	@PostMapping(value ="salvarr")
	@ResponseBody
	public ResponseEntity<Usuario> salvarr (@RequestBody Usuario usuario) {
		Usuario usarioSalvo = usuarioRepository.save(usuario);
	
		return new ResponseEntity<Usuario>(usarioSalvo,HttpStatus.OK);
		
	}

}