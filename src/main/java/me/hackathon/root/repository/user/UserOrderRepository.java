package me.hackathon.root.repository.user;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.hackathon.root.model.user.UserOrder;

@Repository
public class UserOrderRepository {
    @Autowired
    private UserOrderMapper userOrderMapper;

    public void insertUserOrder(UserOrder userOrder) {
        userOrderMapper.insertUserOrder(userOrder);
    }

    public List<UserOrder> selectUserOrderByUserId(int userId) {
        return userOrderMapper.selectUserOrderByUserId(userId);
    }

    public List<Integer> selectUserOrderCountByProductIds(Collection<Integer> productIds) {
        return userOrderMapper.selectUserOrderCountByProductIds(productIds);
    }

    public int selectUserOrderCountByProductId(int productId) {
        return userOrderMapper.selectUserOrderCountByProductId(productId);
    }
}
