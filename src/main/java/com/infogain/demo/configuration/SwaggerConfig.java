package com.infogain.demo.configuration;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket projectManagementApi() {

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.infogain.demo.controller"))
				.paths(PathSelectors.regex("/project.*")).build().apiInfo(projectApiEndPointsInfo())
				.groupName("Project Management");

	}

	@Bean
	public Docket userManagementApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.infogain.demo.controller")).paths(regex("/user.*"))
				.build().apiInfo(userApiEndPointsInfo()).useDefaultResponseMessages(false).groupName("User Management")
				.globalResponseMessage(RequestMethod.GET, getCustomizedResponseMessages());

	}

	private ApiInfo userApiEndPointsInfo() {
		return new ApiInfoBuilder().title("User Management").description("This Api is used to manage Users")
				.version("0.0.1").contact(companyContact()).build();
	}

	private ApiInfo projectApiEndPointsInfo() {
		return new ApiInfoBuilder().title("Project Management").description("This Api is used to manage Projects")
				.version("0.0.1").contact(companyContact()).build();
	}

	private Contact companyContact() {
		return new Contact("Infogain India Private Limited", "https://www.infogain.com", "rudhra.koul@infogain.com");
	}

	private List<ResponseMessage> getCustomizedResponseMessages() {
		List<ResponseMessage> responseMessages = new ArrayList<>();

		/*
		 * Response constant 400 is used when bad request is encountered due to wrong
		 * input
		 */
		responseMessages
				.add(new ResponseMessageBuilder().code(400).message("Bad Request Sent.Please correct request").build());

		/*
		 * Response constant 404 is used when a searched URL or information is not
		 * available.
		 */
		responseMessages.add(new ResponseMessageBuilder().code(404).message("Requested resource not found").build());

		/*
		 * Response constant 500 is used for internal server error i.e. something went
		 * wrong which is not handled by application.
		 */
		responseMessages.add(
				new ResponseMessageBuilder().code(500).message("The service is current under reapir.Please try again")
						.responseModel(new ModelRef("string")).build());
		return responseMessages;
	}

}
