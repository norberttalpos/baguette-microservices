package com.norberttalpos.auth.core.security

import com.norberttalpos.auth.api.util.asSimpleGrantedAuthority
import com.norberttalpos.auth.core.model.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.core.user.OAuth2User
import java.util.*

class UserPrincipal(
    val id: Long?,
    val email: String?,
    private val password: String?,
    private val authorities: Collection<GrantedAuthority>
) :
    OAuth2User, UserDetails {

    private var attributes: Map<String, Any>? = null

    override fun getPassword(): String? {
        return password
    }

    override fun getUsername(): String? {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun getAttributes(): Map<String, Any> {
        return attributes!!
    }

    fun setAttributes(attributes: Map<String, Any>?) {
        this.attributes = attributes
    }

    override fun getName(): String {
        return id.toString()
    }

    companion object {
        fun create(user: User): UserPrincipal {
            val authorities = user.roles?.map { asSimpleGrantedAuthority(it.name!!) } ?: emptyList()

            return UserPrincipal(
                user.id,
                user.email,
                user.password,
                authorities
            )
        }

        fun create(user: User, attributes: Map<String, Any>?): UserPrincipal {
            return create(user).apply {
                this.attributes = attributes
            }
        }
    }
}