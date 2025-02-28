package com.banregio.next.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banregio.next.Repository.ProductoRepository;
import com.banregio.next.entity.Producto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductoService {
	
	private final ProductoRepository productoRepository;
	
	public Producto crearProducto(Producto producto) {
		return productoRepository.save(producto);
	}
	
	public List<Producto> listarProductos() {
		return productoRepository.findAll();
	}
	
	public Optional<Producto> obtenerProductoPorId(Long id){
		return productoRepository.findById(id);
	}
	
	public Producto actualizarProducto(Long id, Producto productoActualizado) {
		return productoRepository.findById(id)
				.map(producto -> {
					producto.setNombre(productoActualizado.getNombre());
					producto.setDescripcion(productoActualizado.getDescripcion());
					producto.setPrecio(productoActualizado.getPrecio());
					producto.setCategoria(productoActualizado.getCategoria());
					return productoRepository.save(producto);
				})
				.orElseThrow(() -> new RuntimeException("Producto no encontrada con ID: " + id));
	}
	
	public void eliminarProducto(Long id) {
		productoRepository.deleteById(id);
	}


}
