package com.gongjin.commom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2接口文档配置
 * @title 
 * @author 龚进
 * @date 2017年10月31日
 * @version 1.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gongjin"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("BTC区块数据分析系统 API")
                .description("BTC区块数据分析系统RESTful API")
                .termsOfServiceUrl("http://blog.didispace.com/")
                .version("2.0")
                .build();
    }
}
