package cn.njit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author dustdawn
 * @date 2019/11/26 22:55
 *
 * swagger-ui.html
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class Swagger2Configuration {
    /*private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("在线选课系统api文档")
                .description("API接口描述")
//                .termsOfServiceUrl("/")
                .version("1.0")
                .build();
    }*/

    //@Bean
    /*public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.basePackage("cn.njit.controller"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }*/

    private ApiInfo apiInfo(String name, String description, String version) {
        return new ApiInfoBuilder().title(name).description(description).version(version).build();
    }

    @Bean
    public Docket cs_api_course() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("course-api", "课程管理", "v1.0"))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/course/**"))
                .build()
                .groupName("课程管理")
                .pathMapping("/");
    }

    @Bean
    public Docket cs_api_test() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("test-api", "测试接口", "v1.0"))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/test/**"))
                .build()
                .groupName("测试接口")
                .pathMapping("/");
    }

    @Bean
    public Docket cs_api_dept() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("dept-api", "院系管理接口", "v1.0"))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/dept/**"))
                .build()
                .groupName("院系管理接口")
                .pathMapping("/");
    }
}
