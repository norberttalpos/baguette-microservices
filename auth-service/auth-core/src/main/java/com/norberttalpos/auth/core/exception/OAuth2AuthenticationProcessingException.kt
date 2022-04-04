package com.norberttalpos.auth.core.exception

import org.springframework.security.core.AuthenticationException


class OAuth2AuthenticationProcessingException(msg: String?) : AuthenticationException(msg)