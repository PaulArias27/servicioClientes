package com.krakedev.clientes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.clientes.entidades.Cliente;
import com.krakedev.clientes.servicios.ServicioClientes;

public class ServicioClientesTest {

	private ServicioClientes servicio;

	@BeforeEach
	public void inicializar() {

		servicio = new ServicioClientes();

		Cliente c1 = new Cliente();
		c1.setCedula("123");
		c1.setNombre("Juan");
		c1.setApellido("Perez");
		c1.setEmail("juan@gmail.com");

		Cliente c2 = new Cliente();
		c2.setCedula("456");
		c2.setNombre("Maria");
		c2.setApellido("Lopez");
		c2.setEmail("maria@gmail.com");

		servicio.Crear(c1);
		servicio.Crear(c2);
	}

	@Test
	public void testBuscarPorCedulaExistente() {

		Cliente cliente = servicio.buscarPorCedula("123");

		assertNotNull(cliente);
		assertEquals("Juan", cliente.getNombre());
		assertEquals("Perez", cliente.getApellido());
		assertEquals("juan@gmail.com", cliente.getEmail());
	}

	@Test
	public void testBuscarPorCedulaNoExistente() {

		Cliente cliente = servicio.buscarPorCedula("999");

		assertNull(cliente);
	}

	@Test
	public void testCrearClienteNuevo() {

		Cliente nuevo = new Cliente();
		nuevo.setCedula("789");
		nuevo.setNombre("Carlos");
		nuevo.setApellido("Mendoza");
		nuevo.setEmail("carlos@gmail.com");

		Cliente resultado = servicio.Crear(nuevo);

		assertNotNull(resultado);
		assertEquals("789", resultado.getCedula());
		assertEquals("Carlos", resultado.getNombre());
		assertEquals("Mendoza", resultado.getApellido());
		assertEquals("carlos@gmail.com", resultado.getEmail());

		assertEquals(3, servicio.listar().size());
	}

	@Test
	public void testCrearClienteDuplicado() {

		Cliente duplicado = new Cliente();
		duplicado.setCedula("123");
		duplicado.setNombre("Pedro");
		duplicado.setApellido("Ramirez");
		duplicado.setEmail("pedro@gmail.com");

		Cliente resultado = servicio.Crear(duplicado);

		assertNull(resultado);
		assertEquals(2, servicio.listar().size());
	}

	@Test
	public void testListarClientes() {

		List<Cliente> clientes = servicio.listar();

		assertNotNull(clientes);
		assertEquals(2, clientes.size());

		assertEquals("Juan", clientes.get(0).getNombre());
		assertEquals("juan@gmail.com", clientes.get(0).getEmail());

		assertEquals("Maria", clientes.get(1).getNombre());
		assertEquals("maria@gmail.com", clientes.get(1).getEmail());
	}

	@Test
	public void testActualizarClienteExistente() {

		Cliente actualizado = new Cliente();
		actualizado.setNombre("Juan Actualizado");
		actualizado.setApellido("Perez Actualizado");
		actualizado.setEmail("nuevojuan@gmail.com");

		Cliente resultado = servicio.actualizar("123", actualizado);

		assertNotNull(resultado);
		assertEquals("Juan Actualizado", resultado.getNombre());
		assertEquals("Perez Actualizado", resultado.getApellido());
		assertEquals("nuevojuan@gmail.com", resultado.getEmail());
	}

	@Test
	public void testActualizarClienteNoExistente() {

		Cliente actualizado = new Cliente();
		actualizado.setNombre("NoExiste");
		actualizado.setApellido("NoExiste");
		actualizado.setEmail("noexiste@gmail.com");

		Cliente resultado = servicio.actualizar("999", actualizado);

		assertNull(resultado);
	}

	@Test
	public void testEliminarClienteExistente() {

		boolean eliminado = servicio.eliminar("123");

		assertTrue(eliminado);
		assertEquals(1, servicio.listar().size());
	}

	@Test
	public void testEliminarClienteNoExistente() {

		boolean eliminado = servicio.eliminar("999");

		assertFalse(eliminado);
		assertEquals(2, servicio.listar().size());
	}
}