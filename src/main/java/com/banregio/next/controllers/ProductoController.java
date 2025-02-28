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

import com.banregio.next.entity.Producto;
import com.banregio.next.service.ProductoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/producto")
public class ProductoController {

	private final ProductoService productoService;
	
	@PostMapping()
	public Producto crearProducto(@RequestBody Producto producto) {
		return productoService.crearProducto(producto);
	}   
	
	
	@GetMapping
	public ResponseEntity<List<Producto>> obtenerTodosLosProductos(){
		return ResponseEntity.ok(productoService.listarProductos());
	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id){
		return productoService.obtenerProductoPorId(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	} 
	
	@PutMapping("/{id}")
	public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto){
		return ResponseEntity.ok(productoService.actualizarProducto(id, producto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
		productoService.eliminarProducto(id);
		return ResponseEntity.noContent().build();
	}
}
