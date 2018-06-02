package me.hackathon.root.service.product;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.hackathon.root.model.comment.Comment;
import me.hackathon.root.model.product.Product;
import me.hackathon.root.model.response.product.ProductMainView;
import me.hackathon.root.model.response.product.ProductView;
import me.hackathon.root.model.user.User;
import me.hackathon.root.model.user.UserOrder;
import me.hackathon.root.repository.product.ProductRepository;
import me.hackathon.root.service.AmazonS3Service;
import me.hackathon.root.service.BlockChainService;
import me.hackathon.root.service.NHService;
import me.hackathon.root.service.comment.CommentService;
import me.hackathon.root.service.user.UserOrderService;
import me.hackathon.root.service.user.UserService;
import me.hackathon.root.service.user.UserUpgradeGradeService;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private AmazonS3Service amazonS3Service;
    @Autowired
    private UserUpgradeGradeService userUpgradeGradeService;
    @Autowired
    private BlockChainService blockChainService;
    @Autowired
    private NHService nhService;

    private static final String DEFAULT_THUMBNAIL =
            "https://hackathon.image.s3.ap-northeast-2.amazonaws.com/53e8c5bfe48d3c2e99282925363af241e9f9f8a66a560072cdc8cef1833a0f28?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20180602T003234Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604800&X-Amz-Credential=AKIAI7VBNUOJGTJ47BJQ%2F20180602%2Fap-northeast-2%2Fs3%2Faws4_request&X-Amz-Signature=5029cd47f1d1650e22bac173679754c39340ad17ee19ad93cdf32f20e8a8ccc8";

    private ObjectMapper objectMapper = new ObjectMapper();

    public ProductMainView getProductMainView() {
        return new ProductMainView(productRepository.selectProductListAll());
    }

    public Product getProductByProductId(Integer productId) {
        Product product = productRepository.selectProductByProductId(productId);

        return product;
    }

    public ProductView getProductView(Integer productId) {
        Product product = productRepository.selectProductByProductId(productId);
        User user = userService.getUserAndCoinById(product.getUserId());
        return ProductView.builder()
                          .comments(commentService.getCommentsByProductId(product.getId()))
                          .product(product)
                          .user(user)
                          .build();
    }

    public void insertProduct(Product product) {
        if (product.getThumbnail() == null) {
            product.setThumbnail(DEFAULT_THUMBNAIL);
        }
        productRepository.insertProduct(product);
    }

    public void insertComment(Comment comment) {
        commentService.insertComment(comment);
    }

    @Transactional
    public void buyProduct(UserOrder userOrder) throws IOException {
        // TODO
        // 농협API로 확인
        nhService.drawingTransfer();

        // 블록체인 API 확인
        //구매자가 coin을 이용해서 구매했는지 확인
        User buyUser = userService.getUserAndCoinById(userOrder.getUserId());

        if (userOrder.getCoin() > 0 ) {
            // 코인으로 물건 구매
            if (blockChainService.sendCoin(buyUser.getEmail(), -userOrder.getCoin()) == false) {
                throw new IllegalArgumentException("코인으로 물건 구매 실패!");
            }
        }

        // 판매하고 있는 유저에게 일정 코인 지급
        // 1. product id 로 판매 유저 알아오기
        // 2. product 가격의 10%를 판매유저에게 적립
        Product product = productRepository.selectProductByProductId(userOrder.getProductId());
        User sellUser = userService.getUserAndCoinById(product.getUserId());
        //판매자에게 코인 지급
        if (blockChainService.sendCoin(sellUser.getEmail(), product.getPrice() / (sellUser.getGrade().getValue()+1)) == false) {
            throw new IllegalArgumentException("물건 판매 후 판매자에게 코인 지급 실패!");
        }
        userOrderService.insertUserOrder(userOrder);
        userUpgradeGradeService.upgradeUserGrade(userOrder.getUserId());
    }

    public List<Product> getProductListByIds(Collection<Integer> ids) {
        return productRepository.selectProductListByIds(ids);
    }

    public List<Product> getProductListByUserId(int userId) {
        return productRepository.selectProductListByUserId(userId);
    }

    public void uploadThumbnail(MultipartFile multipartFile, int productId) {
        String thumbnail = amazonS3Service.uploadImage(multipartFile);
        productRepository.uploadThumbnail(thumbnail, productId);
    }
    public void updateProductThumbnail(Product product) {
        productRepository.uploadThumbnail(product.getThumbnail(), product.getId());
    }
}
