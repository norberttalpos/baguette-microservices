package com.norberttalpos.auth.exception

import org.springframework.security.core.AuthenticationException


class OAuth2AuthenticationProcessingException : AuthenticationException {
    constructor(msg: String?, t: Throwable?) : super(msg, t) {}
    constructor(msg: String?) : super(msg) {}
}