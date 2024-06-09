package gr.aueb.cf.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("gr.aueb.cf.project"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {

        Contact contact = new Contact("webtutsplus", "http://webtutsplus.com", "contact.webtutsplus@gmail.com");
        return new ApiInfoBuilder()
                .title("Project API")
                .description("Documentation Ecommerce api")
                .version("*")
                .license("*")
                .licenseUrl("*")
                .contact(contact)
                .build();
    }
}
