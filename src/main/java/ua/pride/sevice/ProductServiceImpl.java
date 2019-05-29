package ua.pride.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pride.dao.ProductDao;
import ua.pride.model.Product;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;

    @Override
    public List<Product> allProducts() {
        return productDao.allProducts();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void delete(Long id) {
        productDao.delete(id);
    }

    @Override
    public Product findProductById(Long id) {
        return productDao.findProductById(id);
    }

    @Override
    public List<Product> ascByPrice() {
        return productDao.ascByPrice();
    }

    @Override
    public List<Product> descByPrice() {
        return productDao.descByPrice();
    }
}
