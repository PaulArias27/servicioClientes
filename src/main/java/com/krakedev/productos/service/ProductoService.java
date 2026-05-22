package com.krakedev.productos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.krakedev.productos.entidades.Producto;

@Service
public class ProductoService {

    private ArrayList<Producto> productos = new ArrayList<Producto>();

    // Buscar producto por código
    public Producto buscarPorCodigo(int codigo) {

        for (Producto p : productos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }

        return null;
    }

    // Crear producto
    public Producto crear(Producto producto) {

        Producto existente = buscarPorCodigo(producto.getCodigo());

        if (existente != null) {
            return null;
        } else {
            productos.add(producto);
            return producto;
        }
    }

    // Listar productos
    public List<Producto> listar() {
        return productos;
    }

    // Actualizar producto
    public Producto actualizar(int codigo, Producto productoActualizado) {

        Producto producto = buscarPorCodigo(codigo);

        if (producto != null) {

            producto.setNombre(productoActualizado.getNombre());
            producto.setPrecio(productoActualizado.getPrecio());
            producto.setStock(productoActualizado.getStock());

            return producto;
        }

        return null;
    }

    // Eliminar producto
    public boolean eliminar(int codigo) {

        Producto producto = buscarPorCodigo(codigo);

        if (producto != null) {
            productos.remove(producto);
            return true;
        }

        return false;
    }
}
