package com.banregio.next.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banregio.next.entity.Categoria;
import com.banregio.next.service.CategoriaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
	
	private final CategoriaService categoriaService;
	
	@PostMapping
	public Categoria crearCategoria(@RequestBody Categoria categoria) {
		return categoriaService.crearCategoria(categoria);
	}
	
	@GetMapping
	public ResponseEntity<List<Categoria>> obtenerTodasLasCategorias(){
		return ResponseEntity.ok(categoriaService.listarCategorias());
	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> obtenerCategoria(@PathVariable Long id){
		return categoriaService.obtenerCategoriaPorId(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	} 
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria){
		return ResponseEntity.ok(categoriaService.actualizarCategoria(id, categoria));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id){
		categoriaService.eliminarCategoria(id);
		return ResponseEntity.noContent().build();
	}
	

}