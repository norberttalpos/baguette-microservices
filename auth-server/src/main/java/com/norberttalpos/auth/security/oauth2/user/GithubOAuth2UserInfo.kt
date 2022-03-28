package com.norberttalpos.auth.security.oauth2.user

class GithubOAuth2UserInfo(attributes: Map<String, Any>) :
    OAuth2UserInfo(attributes) {
    override val id: String
        get() = (attributes["id"] as Int?).toString()
    override val name: String?
        get() = attributes["login"] as String?
    override val email: String?
        get() = attributes["email"] as String?
    override val imageUrl: String?
        get() = attributes["avatar_url"] as String?
}