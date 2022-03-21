package com.norberttalpos.apigw.config


//@Configuration
//@EnableWebSecurity
//security:
//oauth2:
//resourceserver:
//jwt:
//issuer-uri: http://localhost:9000/oauth2/jwks
//class GatewayWebSecurityConfig : WebSecurityConfigurerAdapter() {
//
//    override fun configure(http: HttpSecurity) {
//        http
//            .authorizeRequests()
//            .antMatchers(
//                "/oauth2/**",
//                "/swagger**",
//                "/swagger-resources/**",
//                "/swagger-ui/**",
//                "/webjars/springfox-swagger-ui/**",
//                "/v2/api-docs**",
//                "/**/v2/api-docs"
//            ).permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .oauth2ResourceServer().jwt()
//    }
//
//    @Bean
//    fun corsConfigurationSource(): CorsConfigurationSource {
//        val configuration = CorsConfiguration()
//        configuration.allowedOriginPatterns = listOf("*")
//        configuration.allowCredentials = true
//        configuration.allowedMethods = arrayListOf("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
//        configuration.allowedHeaders = arrayListOf("authorization", "content-type", "x-auth-token")
//        configuration.exposedHeaders = listOf("x-auth-token")
//        val source = UrlBasedCorsConfigurationSource()
//        source.registerCorsConfiguration("/**", configuration)
//        return source
//    }
//}