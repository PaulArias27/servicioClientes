package com.krakedev.clientes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.clientes.entidades.Cliente;
import com.krakedev.clientes.servicios.ServicioClientes;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private final ServicioClientes servicioClientes = new ServicioClientes();
	
	@PostMapping
	public Cliente crear(@RequestBody Cliente cliente) {
		return servicioClientes.Crear(cliente);
	}
	
	@GetMapping
	public List<Cliente> listar(){
		return servicioClientes.listar();
	}
	
	@GetMapping("/{cedula}")
	public Cliente buscar(@PathVariable String cedula) {
		return servicioClientes.buscarPorCedula(cedula);
	}
	
	@PutMapping("/{cedula}")
	public Cliente actualizar(@PathVariable String cedula, @RequestBody Cliente clienteActualizado) {
		return servicioClientes.actualizar(cedula, clienteActualizado);
	}
	
	@DeleteMapping("/{cedula}")
	public boolean eliminar(@PathVariable String cedula) {
		return servicioClientes.eliminar(cedula);
	}
}
