package me.hackathon.root.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.hackathon.root.model.product.Product;
import me.hackathon.root.model.response.user.UserOrderBuy;
import me.hackathon.root.model.response.user.UserOrderBuysView;
import me.hackathon.root.model.response.user.UserOrderSell;
import me.hackathon.root.model.response.user.UserOrderSellsView;
import me.hackathon.root.model.user.UserOrder;
import me.hackathon.root.model.user.UserOrderStatus;
import me.hackathon.root.repository.user.UserOrderRepository;
import me.hackathon.root.service.product.ProductService;

@Service
public class UserOrderService {
    @Autowired
    private UserOrderRepository userOrderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    public void insertUserOrder(UserOrder userOrder) {
        userOrder.setStatus(UserOrderStatus.SHIPPING);
        userOrderRepository.insertUserOrder(userOrder);
    }

    public UserOrderBuysView getUserOrderBuyView(int userId) {
        List<UserOrderBuy> userOrderBuys = new ArrayList<>();
        List<UserOrder> userOrders = userOrderRepository.selectUserOrderByUserId(userId);
        for (UserOrder userOrder : userOrders) {
            userOrderBuys.add(UserOrderBuy.builder()
                                          .product(productService
                                                           .getProductByProductId(userOrder.getProductId()))
                                          .user(userService.getUserAndCoinById(userOrder.getUserId()))
                                          .userOrder(userOrder)
                                          .build());
        }

        return UserOrderBuysView.builder()
                                .userOrderBuys(userOrderBuys)
                                .build();
    }

    public UserOrderSellsView getUserOrderSellView(int userId) {
        List<UserOrderSell> userOrderSells = new ArrayList<>();
        List<Product> products = productService.getProductListByUserId(userId);
        for (Product product : products) {
            userOrderSells.add(UserOrderSell.builder()
                                            .product(product)
                                            .totalSellCount(userOrderRepository.selectUserOrderCountByProductId(
                                                    product.getId()))
                                            .build());
        }

        return UserOrderSellsView.builder().userOrderSells(userOrderSells).build();
    }

    public List<UserOrder> getUserOrdersByUserId(int userId) {
        return userOrderRepository.selectUserOrderByUserId(userId);
    }
}
