package ua.pride.dao;

import ua.pride.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> allProducts();
    List<Product> ascByPrice();
    List<Product> descByPrice();
    void save(Product product);
    void update(Product product);
    void delete(Long id);
    Product findProductById(Long id);
}
