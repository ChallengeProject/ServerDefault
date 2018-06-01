package me.hackathon.root.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import me.hackathon.root.model.board.Board;
import me.hackathon.root.model.board.BoardStatus;
import me.hackathon.root.model.board.VLTCategoryType;
import me.hackathon.root.model.comment.Comment;
import me.hackathon.root.model.comment.CommentGrade;
import me.hackathon.root.model.product.Product;
import me.hackathon.root.model.product.ProductCategoryType;
import me.hackathon.root.model.user.User;
import me.hackathon.root.model.user.UserBoard;
import me.hackathon.root.model.user.UserGrade;
import me.hackathon.root.model.user.UserOrder;
import me.hackathon.root.model.user.UserOrderStatus;
import me.hackathon.root.model.user.UserStatus;
import me.hackathon.root.support.typehandler.DateLongTypeHandler;
import me.hackathon.root.support.typehandler.ValueEnumTypeHandler;

@Configuration
@EnableTransactionManagement
public class MybatisConfig {
    public static final String MAPPER_LOCATIONS_PATH = "classpath:mapper/*Mapper.xml";

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        sqlSessionFactoryBean.setConfiguration(mybatisConfiguration());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATIONS_PATH));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    private org.apache.ibatis.session.Configuration mybatisConfiguration() {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setAutoMappingBehavior(AutoMappingBehavior.FULL);

        //typeAlias
        TypeAliasRegistry typeAliasRegistry = configuration.getTypeAliasRegistry();
        typeAliasRegistry.registerAlias("User", User.class);
        typeAliasRegistry.registerAlias("Product", Product.class);
        typeAliasRegistry.registerAlias("UserOrder", UserOrder.class);
        typeAliasRegistry.registerAlias("Board", Board.class);
        typeAliasRegistry.registerAlias("Comment", Comment.class);
        typeAliasRegistry.registerAlias("UserBoard", UserBoard.class);

        //typeHandler
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        typeHandlerRegistry.register(DateLongTypeHandler.class);
        typeHandlerRegistry.register(UserStatus.class, ValueEnumTypeHandler.class);
        typeHandlerRegistry.register(UserGrade.class, ValueEnumTypeHandler.class);
        typeHandlerRegistry.register(UserOrderStatus.class, ValueEnumTypeHandler.class);
        typeHandlerRegistry.register(ProductCategoryType.class, ValueEnumTypeHandler.class);
        typeHandlerRegistry.register(BoardStatus.class, ValueEnumTypeHandler.class);
        typeHandlerRegistry.register(VLTCategoryType.class, ValueEnumTypeHandler.class);
        typeHandlerRegistry.register(CommentGrade.class, ValueEnumTypeHandler.class);
//        typeHandlerRegistry.register(OptionData.class, OptionDataTypeHandler.class);

        return configuration;
    }
}
