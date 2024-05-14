package br.com.gerenciaautoeletrica.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.gerenciaautoeletrica.adapter.entrypoint.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Gerencia AutoEletrica")
                .description("Api de Estudos Baseado no Gerênciamento de uma AutoElétrica")
                .version("1.0.0")
                .contact(this.contact())
                .build();
    }

    private Contact contact() {
        return new Contact("Renan", "https://github.com/Renan2411", "renan.monteiroad@gmail.com");
    }
}
