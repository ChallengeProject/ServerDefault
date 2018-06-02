package me.hackathon.root.model.response.user;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserOrderBuysView {
    private List<UserOrderBuy> userOrderBuys;
}
