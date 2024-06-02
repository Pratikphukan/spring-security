package com.usermanagement.services;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.usermanagement.dtos.Product;

import jakarta.annotation.PostConstruct;

@Service
public class ProductService {

	private List<Product> productList;

	public List<Product> getProducts() {
		return productList;
	}

	@PostConstruct
	public void loadProductsFromDB() {
		productList = IntStream.rangeClosed(1, 100)
				.mapToObj(i -> Product.getBuilder().setProductId(i).setName("product " + i)
						.setPrice(new Random().nextInt(1000)).setQty(new Random().nextInt(10)).build())
				.collect(Collectors.toList());
	}

	public Product getProduct(int id) {
		return productList.stream().filter(product -> product.getProductId() == id).findAny()
				.orElseThrow(() -> new RuntimeException("product " + id + " not found"));
	}

}
