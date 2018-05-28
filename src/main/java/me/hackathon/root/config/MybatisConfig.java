package me.hackathon.root.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import me.hackathon.root.model.user.User;
import me.hackathon.root.model.user.UserStatus;
import me.hackathon.root.support.typehandler.DateLongTypeHandler;
import me.hackathon.root.support.typehandler.ValueEnumTypeHandler;

@Configuration
@MapperScan("me.hackathon.root.repository")
public class MybatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setAutoMappingBehavior(AutoMappingBehavior.FULL);
        TypeAliasRegistry typeAliasRegistry = configuration.getTypeAliasRegistry();
        typeAliasRegistry.registerAlias("User", User.class);

        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        typeHandlerRegistry.register(DateLongTypeHandler.class);
        typeHandlerRegistry.register(UserStatus.class, ValueEnumTypeHandler.class);
//        typeHandlerRegistry.register(OptionData.class, OptionDataTypeHandler.class);
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setMapperLocations(new Resource[] {
                new ClassPathResource("mapper/UserMapper.xml"),
//                new ClassPathResource("mapper/UserOrderMapper.xml"),
//                new ClassPathResource("mapper/BoardMapper.xml"),
//                new ClassPathResource("mapper/UserMapper.xml"),
//                new ClassPathResource("mapper/CafeMenuMapper.xml")
                });
        return sqlSessionFactoryBean.getObject();
    }
}
