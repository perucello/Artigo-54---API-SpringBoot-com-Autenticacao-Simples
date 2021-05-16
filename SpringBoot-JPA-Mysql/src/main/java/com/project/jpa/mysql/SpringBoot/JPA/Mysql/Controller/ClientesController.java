package com.project.jpa.mysql.SpringBoot.JPA.Mysql.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

import com.project.jpa.mysql.SpringBoot.JPA.Mysql.Model.Cliente;
import com.project.jpa.mysql.SpringBoot.JPA.Mysql.Repository.Clientes;


@RestController
@RequestMapping("api/JPA/Mysql/clientes")
public class ClientesController {
	
	@Autowired
	private Clientes clientes;
	
	@GetMapping
	public List<Cliente> listar(){
		System.out.println("Quantidade de Registros de Clientes : " + clientes.count());
		return (clientes.findAll());
	}
		
	@PostMapping("/add")
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {	
		return clientes.save(cliente);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Cliente>> buscar(@PathVariable Long id){
		Optional<Cliente> cliente = clientes.findById(id);
		if (clientes == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente)
	{
		Object atualizar = clientes.findById(id);
		if (atualizar == null) {
			return ResponseEntity.notFound().build();
		}		
		BeanUtils.copyProperties(cliente, atualizar, "id");		
		atualizar = clientes.save(cliente);	
		return ResponseEntity.ok(atualizar);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		Optional<Cliente> cliente = clientes.findById(id);
		if(cliente != null) {
			clientes.deleteById(id);
		}
		return ResponseEntity.noContent().build();
	}
	
	
	
}