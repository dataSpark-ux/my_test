package com.wy.newblog.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @Date 2018/8/28 15:14
 * @Description TODO
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {
    @Bean
    public Docket buildDocket() {
        ParameterBuilder tokenParams = new ParameterBuilder();
        tokenParams.name("X-Token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        ParameterBuilder TimeParams = new ParameterBuilder();
        TimeParams.name("X-Timestamp").description("时间戳").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        ParameterBuilder signParams = new ParameterBuilder();
        signParams.name("X-Sign").description("签名").modelRef(new ModelRef("string")).parameterType("header").required(true).build();

        List<Parameter> headerParams = new ArrayList<Parameter>();
        headerParams.add(tokenParams.build());
        headerParams.add(TimeParams.build());
        headerParams.add(signParams.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .select()
                //要扫描的API(Controller)基础包
                .apis(RequestHandlerSelectors.basePackage("com.wy.newblog.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title("farbun API文档")
                .version("1.0")
                .build();
    }
}
