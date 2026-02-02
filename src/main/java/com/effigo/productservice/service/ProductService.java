package com.effigo.productservice.service;

import com.effigo.productservice.model.Product;
import com.effigo.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(int id, Product product) {
        Optional<Product> existing = repository.findById(id);
        if (existing.isPresent()) {
            Product p = existing.get();
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            return repository.save(p);
        }
        return null;
    }

    public boolean deleteProduct(int id) {
        Optional<Product> existing = repository.findById(id);
        if (existing.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
