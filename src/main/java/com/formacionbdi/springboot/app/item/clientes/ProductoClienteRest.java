package com.formacionbdi.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.formacionbdi.springboot.app.item.models.Producto;

// Consumir servicio mediante FEING

@FeignClient(name = "servicio-productos",url="localhost:8001/api/productos")
public interface ProductoClienteRest {

	@GetMapping("/listar")
	public List<Producto> listar();

	@GetMapping("/buscarProducto/{id}")
	public Producto buscarProducto(@PathVariable Long id);

}
