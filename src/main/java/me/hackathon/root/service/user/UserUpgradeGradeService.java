package me.hackathon.root.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.hackathon.root.model.user.User;
import me.hackathon.root.model.user.UserBoard;
import me.hackathon.root.model.user.UserGrade;
import me.hackathon.root.model.user.UserOrder;

@Service
public class UserUpgradeGradeService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private UserBoardService userBoardService;

    private static final int GOLD_UPGRADE = 1;
    private static final int PLATINUM_UPGRADE = 2;
    private static final int MASTER_UPGRADE = 3;
    private static final int CHALLENGER_UPGRADE = 4;


    public boolean upgradeUserGrade(int userId) {
        User user = userService.getUserAndCoinById(userId);
        List<UserOrder> userOrders = userOrderService.getUserOrdersByUserId(userId);
        List<UserBoard> userBoards = userBoardService.getUserBoardsByUserId(userId);

        switch(user.getGrade()) {
            case SILVER:
                    if(userOrders.size() > GOLD_UPGRADE && userBoards.size() > GOLD_UPGRADE) {
                        userService.updateUserGrade(UserGrade.GOLD, userId);
                    }
                break;
            case GOLD:
                if(userOrders.size() > PLATINUM_UPGRADE && userBoards.size() > PLATINUM_UPGRADE) {
                    userService.updateUserGrade(UserGrade.PLATINUM, userId);
                }
                break;
            case PLATINUM:
                if(userOrders.size() > MASTER_UPGRADE && userBoards.size() > MASTER_UPGRADE) {
                    userService.updateUserGrade(UserGrade.MASTER, userId);
                }
                break;
            case MASTER:
                if(userOrders.size() > CHALLENGER_UPGRADE && userBoards.size() > CHALLENGER_UPGRADE) {
                    userService.updateUserGrade(UserGrade.CHALLENGER, userId);
                }
                break;
                default:
                    throw new IllegalArgumentException();
        }

        return true;
    }
}
