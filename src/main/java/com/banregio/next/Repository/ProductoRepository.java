package com.banregio.next.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banregio.next.entity.Producto;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}