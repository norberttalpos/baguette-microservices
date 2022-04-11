package com.norberttalpos.auth.core.security.oauth2

import com.norberttalpos.auth.core.exception.OAuth2AuthenticationProcessingException
import com.norberttalpos.auth.core.model.entity.AuthProvider
import com.norberttalpos.auth.core.model.entity.User
import com.norberttalpos.auth.core.repository.UserRepository
import com.norberttalpos.auth.core.security.UserPrincipal
import com.norberttalpos.auth.core.security.oauth2.user.OAuth2UserInfo
import com.norberttalpos.auth.core.security.oauth2.user.OAuth2UserInfoFactory
import com.norberttalpos.auth.core.util.RoleDeterminerService
import com.norberttalpos.customer.api.client.CustomerClient
import com.norberttalpos.customer.api.dto.CustomerDto
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StringUtils

@Service
class CustomOAuth2UserService(
    private val userRepository: UserRepository,
    private val customerClient: CustomerClient,
    private val roleDeterminerService: RoleDeterminerService,
) : DefaultOAuth2UserService() {

    override fun loadUser(oAuth2UserRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User = super.loadUser(oAuth2UserRequest)

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User)
        } catch (ex: AuthenticationException) {
            throw ex
        } catch (ex: Exception) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw InternalAuthenticationServiceException(ex.message, ex.cause)
        }
    }

    private fun processOAuth2User(oAuth2UserRequest: OAuth2UserRequest, oAuth2User: OAuth2User): OAuth2User {
        val oAuth2UserInfo: OAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
            oAuth2UserRequest.clientRegistration.registrationId,
            oAuth2User.attributes
        )

        if (!StringUtils.hasText(oAuth2UserInfo.email)) {
            throw OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider")
        }

        var user = oAuth2UserInfo.email?.let { userRepository.findByEmail(it) }

        user = if (user != null) {
            if (!user.provider?.equals(AuthProvider.valueOf(oAuth2UserRequest.clientRegistration.registrationId))!!) {
                throw OAuth2AuthenticationProcessingException(
                    "Looks like you're signed up with ${user.provider.toString()} account. \n" +
                            "Please use your ${user.provider.toString()} account to login."
                )
            }
            updateExistingUser(user, oAuth2UserInfo)

        } else {
            registerNewUser(oAuth2UserRequest, oAuth2UserInfo)
        }

        return UserPrincipal.create(user, oAuth2User.attributes)
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = [Exception::class])
    fun registerNewUser(oAuth2UserRequest: OAuth2UserRequest, oAuth2UserInfo: OAuth2UserInfo): User {
        val user = User().apply {
            this.email = oAuth2UserInfo.email
            this.provider = AuthProvider.valueOf(oAuth2UserRequest.clientRegistration.registrationId)
            this.providerId = oAuth2UserInfo.id
            this.roles = roleDeterminerService.determineRoles(oAuth2UserInfo.email!!)
        }

        val result = userRepository.save(user)

        this.customerClient.registerCustomer(
            CustomerDto(
                id = result.id,
                name = oAuth2UserInfo.name ?: "",
                email = result.email,
                phoneNumber = null, // TODO
                imageUrl = oAuth2UserInfo.imageUrl,
                address = null
            )
        )

        return result
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = [Exception::class])
    fun updateExistingUser(existingUser: User, oAuth2UserInfo: OAuth2UserInfo): User {
/*        this.customerClient.registerCustomer(
            CustomerDto(
                id = result.id,
                name = oAuth2UserInfo.name,
                email = result.email,
                phoneNumber = null,
                imageUrl = oAuth2UserInfo.imageUrl,
                address = null
            )
        )*/

        // TODO

        return userRepository.save(existingUser)
    }
}