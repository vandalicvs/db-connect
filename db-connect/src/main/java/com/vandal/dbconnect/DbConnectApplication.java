package com.vandal.dbconnect;

import com.vandal.dbconnect.stuff.Product;
import com.vandal.dbconnect.stuff.ProductDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/products")
@ComponentScan("com.vandal.dbconnect.stuff")
public class DbConnectApplication {

	private final ProductDAO productDAO;

	public DbConnectApplication(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public static void main(String[] args) {
		SpringApplication.run(DbConnectApplication.class, args);
	}

	@PostMapping
	public ResponseEntity<Void> saveProduct(@RequestBody Product product) {
		productDAO.saveProduct(product);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<List<Product>> loadAllAvailableItems() {
		List<Product> products = productDAO.loadAllAvailableItems();
		return ResponseEntity.ok().body(products);
	}

	@PostMapping("/{productId}/price")
	public ResponseEntity<String> updateProductPrice(@PathVariable int productId, @RequestParam double price) {
		productDAO.updateProductPrice(productId, price);
		return ResponseEntity.ok("Product price updated.");
	}

	@DeleteMapping("/not-for-sale")
	public ResponseEntity<String> deleteNotForSaleProducts() {
		productDAO.deleteNotForSaleProducts();
		return ResponseEntity.ok("Not-for-sale products deleted.");
	}
}