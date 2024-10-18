package bitcamp.myapp;

import bitcamp.myapp.interceptor.AdminInterceptor;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@PropertySource({
    "classpath:config/jdbc.properties",
    "file:${user.home}/config/ncp.properties"})
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("bitcamp.myapp.dao")
@EnableWebMvc
@Controller
public class ServerApp implements WebMvcConfigurer {

  ApplicationContext appCtx;

  @Autowired
  public ServerApp(ApplicationContext appCtx) {
    System.getProperties().setProperty("aws.java.v1.disableDeprecationAnnouncement", "true");
    this.appCtx = appCtx;
  }

  public static void main(String[] args) {
    SpringApplication.run(ServerApp.class, args);
  }

  @GetMapping("/hello")
  @ResponseBody
  public String hello() {
    return "Hello World!";
  }


  @Bean
  public DataSource dataSource(
      @Value("${jdbc.driver}") String jdbcDriver,
      @Value("${jdbc.url}") String jdbcUrl,
      @Value("${jdbc.username}") String jdbcUsername,
      @Value("${jdbc.password}") String jdbcPassword) {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(jdbcDriver);
    ds.setUrl(jdbcUrl);
    ds.setUsername(jdbcUsername);
    ds.setPassword(jdbcPassword);
    return ds;
  }

  @Bean
  public PlatformTransactionManager transactionManager(DataSource ds) {
    return new DataSourceTransactionManager(ds);
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(ds);
    factoryBean.setTypeAliasesPackage("bitcamp.myapp.vo");
    factoryBean.setMapperLocations(appCtx.getResources("classpath:mappers/*Mapper.xml"));
    return factoryBean.getObject();
  }

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver vr = new InternalResourceViewResolver();
    vr.setPrefix("/WEB-INF/jsp/");
    vr.setSuffix(".jsp");
    return vr;
  }

  @Bean
  public MultipartResolver multipartResolver() {
    return new StandardServletMultipartResolver();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new AdminInterceptor())
        .addPathPatterns("/users*");
  }
}
