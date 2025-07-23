package com.community.global.config;

import java.util.Arrays;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

import com.community.global.annotation.ApiResponseCode;
import com.community.global.annotation.ApiResponseCodes;
import com.community.global.enums.ErrorCode;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @Configuration
public class SwaggerConfig {
	@Bean
	public OperationCustomizer operationCustomizer() {
		return (operation, handlerMethod) -> {
			ApiResponses apiResponses = operation.getResponses();
			apiResponses.clear();

			apiResponses.put("200", new ApiResponse().description("OK"));

			ApiResponseCodes apiResponseCodesAnnotation = handlerMethod.getMethodAnnotation(ApiResponseCodes.class);
			if (apiResponseCodesAnnotation != null) {
				ApiResponseCode[] apiResponseCodes = apiResponseCodesAnnotation.value();
				Arrays.stream(apiResponseCodes).forEach(
					apiResponseCode -> {
						ErrorCode errorCode = apiResponseCode.value();
						apiResponses.put(errorCode.getErrorCode(),
							new ApiResponse().description(errorCode.getMessage()));
					}
				);
			}

			return operation;
		};
	}

	@Bean
	public GroupedOpenApi groupedOpenApi(OperationCustomizer operationCustomizer) {
		return GroupedOpenApi.builder()
			.group("community")
			.addOperationCustomizer(operationCustomizer)
			.addOpenApiCustomizer(openApi -> {
				openApi.info(new Info().title("Community API").version("v1"));
				openApi.getPaths().values().stream()
					.flatMap(pathItem -> pathItem.readOperations().stream())
					.forEach(operation -> {
						log.info("operation: {}", operation);
					});
			})
			.build();
	}
}
