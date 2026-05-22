package com.krakedev.productos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.krakedev.productos.entidades.Producto;
import com.krakedev.productos.service.ProductoService;

public class ProductoServiceTest {

    // 1. Crear productos existentes
    @Test
    public void testCrearProducto() {

        ProductoService servicio = new ProductoService();

        Producto producto = new Producto(
                "Laptop",
                1,
                2500.0,
                10);

        Producto resultado = servicio.crear(producto);

        // Se espera que el producto se cree correctamente
        assertEquals("Laptop", resultado.getNombre());
        assertEquals(1, resultado.getCodigo());
        assertEquals(2500.0, resultado.getPrecio());
        assertEquals(10, resultado.getStock());
    }

    // 2. Crear productos duplicados
    @Test
    public void testCrearProductoDuplicado() {

        ProductoService servicio = new ProductoService();

        Producto producto1 = new Producto(
                "Laptop",
                1,
                2500.0,
                10);

        Producto producto2 = new Producto(
                "Mouse",
                1,
                50.0,
                20);

        servicio.crear(producto1);

        Producto resultado = servicio.crear(producto2);

        // Se espera null porque el código ya existe
        assertNull(resultado);
    }

    // 3. Buscar producto existente
    @Test
    public void testBuscarProductoExistente() {

        ProductoService servicio = new ProductoService();

        Producto producto = new Producto(
                "Teclado",
                2,
                80.0,
                15);

        servicio.crear(producto);

        Producto encontrado = servicio.buscarPorCodigo(2);

        // Se espera encontrar el producto
        assertEquals("Teclado", encontrado.getNombre());
        assertEquals(80.0, encontrado.getPrecio());
    }

    // 4. Buscar producto no existente
    @Test
    public void testBuscarProductoNoExistente() {

        ProductoService servicio = new ProductoService();

        Producto encontrado = servicio.buscarPorCodigo(99);

        // Se espera null porque el producto no existe
        assertNull(encontrado);
    }

    // 5. Listar productos
    @Test
    public void testListarProductos() {

        ProductoService servicio = new ProductoService();

        Producto producto1 = new Producto(
                "Monitor",
                3,
                500.0,
                5);

        Producto producto2 = new Producto(
                "Impresora",
                4,
                300.0,
                8);

        servicio.crear(producto1);
        servicio.crear(producto2);

        List<Producto> productos = servicio.listar();

        // Se espera que existan 2 productos en la lista
        assertEquals(2, productos.size());
    }

    // 6. Actualizar producto existente
    @Test
    public void testActualizarProductoExistente() {

        ProductoService servicio = new ProductoService();

        Producto producto = new Producto(
                "Tablet",
                5,
                700.0,
                12);

        servicio.crear(producto);

        Producto actualizado = new Producto(
                "Tablet Pro",
                5,
                900.0,
                20);

        Producto resultado = servicio.actualizar(5, actualizado);

        // Se espera que los datos se actualicen correctamente
        assertEquals("Tablet Pro", resultado.getNombre());
        assertEquals(900.0, resultado.getPrecio());
        assertEquals(20, resultado.getStock());
    }

    // 7. Actualizar producto no existente
    @Test
    public void testActualizarProductoNoExistente() {

        ProductoService servicio = new ProductoService();

        Producto actualizado = new Producto(
                "Celular",
                10,
                1200.0,
                30);

        Producto resultado = servicio.actualizar(10, actualizado);

        // Se espera null porque el producto no existe
        assertNull(resultado);
    }

    // 8. Eliminar producto existente
    @Test
    public void testEliminarProductoExistente() {

        ProductoService servicio = new ProductoService();

        Producto producto = new Producto(
                "Audifonos",
                6,
                150.0,
                25);

        servicio.crear(producto);

        boolean eliminado = servicio.eliminar(6);

        // Se espera true porque el producto fue eliminado
        assertTrue(eliminado);
    }

    // 9. Eliminar producto no existente
    @Test
    public void testEliminarProductoNoExistente() {

        ProductoService servicio = new ProductoService();

        boolean eliminado = servicio.eliminar(100);

        // Se espera false porque el producto no existe
        assertFalse(eliminado);
    }
}