package com.banregio.next.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banregio.next.entity.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}