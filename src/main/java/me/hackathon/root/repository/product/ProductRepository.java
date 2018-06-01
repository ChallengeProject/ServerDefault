package me.hackathon.root.repository.product;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.hackathon.root.model.product.Product;

@Repository
public class ProductRepository {
    @Autowired
    private ProductMapper productMapper;

    public Product selectProductByProductId(int productId) {
        return productMapper.selectProductByProductId(productId);
    }

    public void insertProduct(Product product) {
        productMapper.insertProduct(product);
    }

    public List<Product> selectProductListAll() {
        return productMapper.selectProductListAll();
    }

    public List<Product> selectProductListByUserId(int userId) {
        return productMapper.selectProductListByUserId(userId);
    }

    public List<Product> selectProductListByIds(Collection<Integer> ids) {
        return productMapper.selectProductListByIds(ids);
    }
}
