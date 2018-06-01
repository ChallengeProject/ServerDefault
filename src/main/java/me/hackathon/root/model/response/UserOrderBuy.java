package me.hackathon.root.model.response;

import lombok.Builder;
import lombok.Data;
import me.hackathon.root.model.product.Product;
import me.hackathon.root.model.user.User;
import me.hackathon.root.model.user.UserOrder;

@Data
@Builder
public class UserOrderBuy {
    private Product product;
    private User user;
    private UserOrder userOrder;
}
