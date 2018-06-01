package me.hackathon.root.model.response;

import lombok.Builder;
import lombok.Data;
import me.hackathon.root.model.product.Product;

@Data
@Builder
public class UserOrderSell {
    private Product product;
    private Integer totalSellCount;
}
