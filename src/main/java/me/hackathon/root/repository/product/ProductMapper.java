package me.hackathon.root.repository.product;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import me.hackathon.root.model.product.Product;

@Mapper
public interface ProductMapper {
    Product selectProductByProductId(@Param("productId") int productId);

    void insertProduct(Product product);

    List<Product> selectProductListAll();

    List<Product> selectProductListByUserId(@Param("userId") int userId);

    List<Product> selectProductListByIds(@Param("productIds") Collection<Integer> productIds);

}
