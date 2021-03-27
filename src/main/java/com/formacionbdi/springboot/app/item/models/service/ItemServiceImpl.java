package com.formacionbdi.springboot.app.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private RestTemplate clienteRest; // Registro de componente http en appConfig -(Bean)

	@Override
	public List<Item> findAll() {
		List<Producto> productos = Arrays
				.asList(clienteRest.getForObject("http://localhost:8001/api/productos/listar", Producto[].class));
		return productos.stream().map(p -> new Item(p, 3)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());

		Producto producto = clienteRest.getForObject("http://localhost:8001/api/productos/buscarProducto/{id}",
				Producto.class, pathVariables);

		return new Item(producto, cantidad);
	}

}
