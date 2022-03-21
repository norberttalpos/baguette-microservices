package com.norberttalpos.common.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.*
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.OperationContext
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.SecurityConfiguration
import springfox.documentation.swagger.web.SecurityConfigurationBuilder
import java.util.*

private const val authServerUrl = "http://localhost:9000/oauth2"
private const val CLIENT_ID = "baguette_frontend"
private const val CLIENT_SECRET = "ca8831eb-e504-46f1-a514-8be84ba892ed"

@Configuration
class SwaggerConfig {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.norberttalpos"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo())
            .securitySchemes(securitySchemes())
            .securityContexts(listOf(securityContext()))
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfo(
            "baguette microservices REST API", "baguette microservices API to perform CRUD operations", "1.0", "Terms of service",
            Contact("norberttalpos", "", ""), "License of API", "API license URL", emptyList()
        )
    }

    @Bean
    fun security(): SecurityConfiguration {
        return SecurityConfigurationBuilder.builder()
            .clientId(CLIENT_ID)
            .clientSecret(CLIENT_SECRET)
            .scopeSeparator(" ")
            .useBasicAuthenticationWithAccessCodeGrant(true)
            .build()
    }

    private fun securitySchemes(): List<SecurityScheme> {
        val grantType: GrantType = AuthorizationCodeGrantBuilder()
            .tokenEndpoint { b: TokenEndpointBuilder ->
                b.url("$authServerUrl/token").tokenName("oauthtoken")
            }
            .tokenRequestEndpoint { b: TokenRequestEndpointBuilder ->
                b.url(
                    "$authServerUrl/authorize"
                ).clientIdName(CLIENT_ID).clientSecretName(CLIENT_SECRET)
            }.build()

        return listOf(OAuthBuilder().name("spring_oauth")
            .grantTypes(listOf(grantType))
            .build())
    }

    private fun securityContext(): SecurityContext? {
        return SecurityContext.builder()
            .securityReferences(Collections.singletonList(SecurityReference("spring_oauth", arrayOf())))
            .operationSelector { s: OperationContext ->
                !s.requestMappingPattern().matches(".*/public.*".toRegex())
            }
            .build()
    }
}