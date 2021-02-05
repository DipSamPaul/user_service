package com.daytonatec.userservice.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swaggerconfig {

	@Bean
	public Docket api() {
		ParameterBuilder paramBuilder = new ParameterBuilder();
		List<Parameter> params = new ArrayList<>();
		paramBuilder.name("Authorization").modelRef(new ModelRef("string")).parameterType("header").required(false).build();

		params.add(paramBuilder.build());

		return new Docket(DocumentationType.SWAGGER_2).globalOperationParameters(params).select()
				.apis(RequestHandlerSelectors.basePackage("com.daytonatec.userservice"))
				.paths(PathSelectors.regex("/.*")).paths(PathSelectors.regex("/.*")).build().apiInfo(apiEndPointsInfo())
				.consumes(new HashSet<String>(Arrays.asList("application/json")))
				.produces(new HashSet<String>(Arrays.asList("application/json")));

	}

	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("user Service API").description("user Service Rest Api")
				.contact(new Contact("sam", "", "paul.samya35.gmail.com")).version("1.0").build();

	}

}
