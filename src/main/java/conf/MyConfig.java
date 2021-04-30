package conf;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan()
@EnableWebMvc
@EnableTransactionManagement
public class MyConfig {

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try{
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/my_db?useSSL=false");
            dataSource.setUser("bestuser");
            dataSource.setPassword("bestuser");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean () {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        try{

            sessionFactory.setDataSource(dataSource());
            sessionFactory.setPackagesToScan("org.example.respApi");
            Properties hibernateProp = new Properties();
            hibernateProp.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
            hibernateProp.setProperty("hibernate.show_sql","true");
            sessionFactory.setHibernateProperties(hibernateProp);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;

    }

    @Bean
    public HibernateTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(localSessionFactoryBean().getObject());
        return hibernateTransactionManager;
    }
    
}
