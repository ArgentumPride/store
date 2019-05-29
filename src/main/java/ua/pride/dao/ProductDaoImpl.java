package ua.pride.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ua.pride.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    private SqlParameterSource getSqlParameterByModel(Product product) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        if(product != null) {
            parameterSource.addValue("id", product.getId());
            parameterSource.addValue("name", product.getName());
            parameterSource.addValue("description", product.getDescription());
            parameterSource.addValue("price", product.getPrice());
            parameterSource.addValue("picture", product.getPicture());
        }
        return parameterSource;
    }

    private static final class ProductMapper implements RowMapper<Product> {
        public Product mapRow(ResultSet rs, int row) throws SQLException {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getLong("price"));
            product.setDescription(rs.getString("description"));
            product.setPicture(rs.getBlob("picture"));
            return product;
        }
    }

    @Override
    public List<Product> allProducts() {
        String sql = "SELECT id, name, description, price, picture FROM product";
        List<Product> list = namedParameterJdbcTemplate.query(sql, getSqlParameterByModel(null), new ProductMapper());
        return list;
    }

    @Override
    public List<Product> ascByPrice() {
        String sql = "SELECT * FROM product ORDER BY price asc";
        List<Product> list = namedParameterJdbcTemplate.query(sql, getSqlParameterByModel(null), new ProductMapper());
        return list;
    }

    @Override
    public List<Product> descByPrice() {
        String sql = "SELECT * FROM product ORDER BY price desc";
        List<Product> list = namedParameterJdbcTemplate.query(sql, getSqlParameterByModel(null), new ProductMapper());
        return list;
    }

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO product(name, description, price, picture) VALUES(:name, :description, :price, :picture)";
        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(product));
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE product SET name =:name, description=:description, price=:price, picture=:picture where id=:id";
        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(product));
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM product where id=:id";
        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(new Product(id)));
    }

    @Override
    public Product findProductById(Long id) {
        String sql = "SELECT * FROM product where id=:id";
        return namedParameterJdbcTemplate.queryForObject(sql, getSqlParameterByModel(new Product(id)), new ProductMapper());
    }

}