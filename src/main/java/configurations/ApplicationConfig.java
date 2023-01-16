package configurations;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
//@ComponentScan("configurations")
@ComponentScans({@ComponentScan("configurations"), @ComponentScan("daos"), @ComponentScan("services"), @ComponentScan("controllers")})
public class ApplicationConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        return builder.build(Resources.getResourceAsReader("mybatis/mybatis-config.xml"));
    }
}
