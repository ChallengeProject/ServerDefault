package me.hackathon.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.hackathon.root.model.response.UserOrderBuysView;
import me.hackathon.root.model.response.UserOrderSellsView;
import me.hackathon.root.model.supoort.ResultContainer;
import me.hackathon.root.service.user.UserOrderService;
import me.hackathon.root.support.Constant;

@RestController
@RequestMapping(Constant.USER_ORDER_URI)
public class UserOrderController {

    @Autowired
    private UserOrderService userOrderService;

    @GetMapping("/buy/{userId}")
    public ResultContainer<UserOrderBuysView> getUserOrderBuyView(@PathVariable("userId") int userId) {
        return new ResultContainer<>(userOrderService.getUserOrderBuyView(userId));
    }
    @GetMapping("/sell/{userId}")
    public ResultContainer<UserOrderSellsView> getUserOrderSellView(@PathVariable("userId") int userId) {
        return new ResultContainer<>(userOrderService.getUserOrderSellView(userId));
    }
}
