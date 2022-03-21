package com.norberttalpos.apigw.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import springfox.documentation.swagger.web.SwaggerResource
import springfox.documentation.swagger.web.SwaggerResourcesProvider
import java.util.*

@Primary
@Configuration
class SwaggerConfig : SwaggerResourcesProvider {

    @Autowired
    private lateinit var routeLocator: RouteLocator

    override fun get(): List<SwaggerResource> {
        val resources: MutableList<SwaggerResource> = ArrayList()

        routeLocator.routes.subscribe { route: Route ->
            val name = route.id.split("_".toRegex()).toTypedArray()[1]
            resources.add(swaggerResource(name, "/" + name.lowercase(Locale.getDefault()) + "/api/v3/api-docs", "1.0"))
        }
        return resources
    }

    private fun swaggerResource(name: String, location: String, version: String): SwaggerResource {
        val swaggerResource = SwaggerResource()
        swaggerResource.name = name
        swaggerResource.location = location
        swaggerResource.swaggerVersion = version
        return swaggerResource
    }
}