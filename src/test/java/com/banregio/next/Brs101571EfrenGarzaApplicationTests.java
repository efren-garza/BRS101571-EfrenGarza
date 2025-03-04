package com.banregio.next;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.banregio.next.Repository.CategoriaRepository;
import com.banregio.next.Repository.ProductoRepository;
import com.banregio.next.entity.Categoria;
import com.banregio.next.entity.Producto;
import com.banregio.next.service.CategoriaService;
import com.banregio.next.service.ProductoService;

import lombok.extern.log4j.Log4j2;


@Log4j2
@SpringBootTest
class Brs101571EfrenGarzaApplicationTests {

	
		@Mock
		private CategoriaRepository categoriaRepository;
		
		@Mock
		private ProductoRepository productoRepository;
		
		@InjectMocks
		private ProductoService productoService;
		
		@InjectMocks 
		private CategoriaService categoriaService;
		
		private Producto producto;
		private Categoria categoria;
		private List<Categoria> categorias;
		private List<Producto> productos;
		
		@BeforeEach
		public void setup() throws Exception{
			
			producto = new Producto();
			categoria = new Categoria();
			
			categoria.setId(1L);
			categoria.setNombre("prueba");
			//categoria.setProductos(null);
			
			
			producto.setId(1L);
			producto.setNombre("prueba");
			producto.setDescripcion("prueba");
			producto.setPrecio(43.4);
			producto.setCategoria(categoria);
			
			categorias = new ArrayList<Categoria>();
			categorias.add(categoria);
		
			categoria.getProductos().add(producto);
			
			productos = new ArrayList<Producto>();
			productos.add(producto);
		
			
			Mockito.when(categoriaRepository.findAll()).thenReturn(categorias);
			Mockito.when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
			Mockito.when(categoriaRepository.getReferenceById(1L)).thenReturn(categoria);
			Mockito.when(categoriaRepository.save(categoria)).thenReturn(categoria);
			Mockito.when(categoriaRepository.existsById(1L)).thenReturn(true);
			
			Mockito.when(productoRepository.findAll()).thenReturn(productos);
			Mockito.when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
			Mockito.when(productoRepository.getReferenceById(1L)).thenReturn(producto);
			Mockito.when(productoRepository.save(producto)).thenReturn(producto);
			Mockito.when(productoRepository.existsById(1L)).thenReturn(true);
			
		}
		
		@Test
		public void testListarCategorias() throws Exception {
			Assertions.assertEquals(categorias, categoriaService.listarCategorias());
		}
		
		@Test 
		public void testCrearCategoria() throws Exception {
			Assertions.assertEquals(categoria, categoriaService.crearCategoria(categoria));
		}
		
		@Test 
		public void testObtenerCategoriaPorId() throws Exception {
			Optional<Categoria> resultado = categoriaService.obtenerCategoriaPorId(1L);
			Assertions.assertTrue(resultado.isPresent());
			Assertions.assertEquals(1L, resultado.get().getId());
			
		}
		
		@Test 
		public void testActualizarCategoria() throws Exception {
			Assertions.assertEquals(categoria, categoriaService.actualizarCategoria(1L, categoria));
		}
		
		@Test 
		public void testEliminarCategoria() throws Exception {
			categoriaService.eliminarCategoria(1L);
			
			Mockito.verify(categoriaRepository, Mockito.times(1)).deleteById(1L);
		}
		
		@Test 
		public void testListarProductos() throws Exception {
			Assertions.assertEquals(productos, productoService.listarProductos());
		}
		
		@Test
		public void testCrearProducto() {
			Assertions.assertEquals(producto, productoService.crearProducto(producto));
		}
		
		@Test
		public void testObtenerProductoPorId() throws Exception{
			Optional<Producto> resultado = productoService.obtenerProductoPorId(1L);
			Assertions.assertTrue(resultado.isPresent());
			Assertions.assertEquals(1L, resultado.get().getId());
		}
		
		@Test
		public void testActualizarProducto() throws Exception{
			Assertions.assertEquals(producto, productoService.actualizarProducto(1L, producto));
		}
		
		@Test 
		public void testEliminarProducto() throws Exception{
			productoService.eliminarProducto(1L);
			
			Mockito.verify(productoRepository, Mockito.times(1)).deleteById(1L);
		}
}
