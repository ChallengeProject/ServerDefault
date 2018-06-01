package me.hackathon.root.repository.user;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import me.hackathon.root.model.user.UserOrder;

@Mapper
public interface UserOrderMapper {

    void insertUserOrder(UserOrder userOrder);

    List<UserOrder> selectUserOrderByUserId(@Param("userId") int userId);

    List<Integer> selectUserOrderCountByProductIds(@Param("productIds") Collection<Integer> productIds);

    int selectUserOrderCountByProductId(@Param("productId") int productId);

}
