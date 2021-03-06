package com.norberttalpos.auth.core.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app")
class AppProperties {
    val auth = Auth()
    val oauth2 = OAuth2()

    class Auth {
        var tokenSecret: String? = null
        var tokenExpirationMsec: Long = 0
    }

    class OAuth2 {
        var authorizedRedirectUris: List<String> = ArrayList()
            private set
    }
}