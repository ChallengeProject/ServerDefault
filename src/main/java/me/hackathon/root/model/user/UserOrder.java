package me.hackathon.root.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOrder {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer count;
    private Integer totalPrice;
    private UserOrderStatus status;
    private Integer coin;
    private Long createdTime;
}
