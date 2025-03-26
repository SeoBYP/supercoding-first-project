package www.supercoding.com.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = {"www.supercoding.com.repository.comment","www.supercoding.com.repository.like",
                "www.supercoding.com.repository.post","www.supercoding.com.repository.user"},
        entityManagerFactoryRef = "entityManagerFactoryBean",
        transactionManagerRef = "tmJpa"
)
public class JpaConfig {

    @Bean(name = "entityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("www.supercoding.com.repository.comment", "www.supercoding.com.repository.like"
                , "www.supercoding.com.repository.post", "www.supercoding.com.repository.user");

        // JPA에서 사용할 구현체를 Hibernate로 설정
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        // Hibernate 관련 설정을 위한 Map 생성
        Map<String, Object> properties = new HashMap<>();

        // Hibernate가 생성할 SQL 문법을 MySQL 8에 맞게 설정
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

        // SQL을 예쁘게 줄바꿈과 들여쓰기를 포함해 포맷팅해서 로그에 출력
        properties.put("hibernate.format_sql", "true");

        // 실행되는 SQL 위에 주석 형태로 JPA 내부 동작 정보를 함께 출력
        properties.put("hibernate.use_sql_comment", "true");

        // 위 설정들을 EntityManagerFactory에 적용
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory(dataSource).getObject());
        return transactionManager;
    }
}
