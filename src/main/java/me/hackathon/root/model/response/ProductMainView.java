package me.hackathon.root.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.hackathon.root.model.product.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductMainView {
    List<Product> products;
}
