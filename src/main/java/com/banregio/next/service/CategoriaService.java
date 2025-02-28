package com.banregio.next.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banregio.next.Repository.CategoriaRepository;
import com.banregio.next.entity.Categoria;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoriaService {
	
	private final CategoriaRepository categoriaRepository;

	public Categoria crearCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public List<Categoria> listarCategorias() {
		return categoriaRepository.findAll();
	}
	
	public Optional<Categoria> obtenerCategoriaPorId(Long id){
		return categoriaRepository.findById(id);
	}
	
	public Categoria actualizarCategoria(Long id, Categoria categoriaActualizada) {
		return categoriaRepository.findById(id)
				.map(categoria -> {
					categoria.setNombre(categoriaActualizada.getNombre());
					categoria.setProductos(categoriaActualizada.getProductos());
					return categoriaRepository.save(categoria);
				})
				.orElseThrow(() -> new RuntimeException("Categoria no encontrada con ID: " + id));
	}
	
	public void eliminarCategoria(Long id) {
		categoriaRepository.deleteById(id);
	}
	
}