package me.hackathon.root.model.response;

import lombok.Builder;
import lombok.Data;
import me.hackathon.root.model.user.User;

@Data
@Builder
public class UserMyPageView {
    private UserOrderBuysView userOrderBuysView;
    private User user;
}
