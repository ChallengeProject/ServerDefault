package me.hackathon.root.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String content;
    private ProductCategoryType category;
    private String title;
    private String thumbnail;
    private Integer userId;
    private String address;
    private Integer price;
}
