package com.banregio.next.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "productos")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Column(name = "nombre")
	private String nombre;
	
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "precio")
	private Double precio;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	@JsonBackReference
	private Categoria categoria;
}
