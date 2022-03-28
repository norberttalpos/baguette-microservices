package com.norberttalpos.auth.security.oauth2

import com.norberttalpos.auth.model.AuthProvider
import com.norberttalpos.auth.model.User
import com.norberttalpos.auth.repository.UserRepository
import com.norberttalpos.auth.security.UserPrincipal
import com.norberttalpos.auth.security.oauth2.user.OAuth2AuthenticationProcessingException
import com.norberttalpos.auth.security.oauth2.user.OAuth2UserInfo
import com.norberttalpos.auth.security.oauth2.user.OAuth2UserInfoFactory
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.OAuth2AuthenticationException
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils


@Service
class CustomOAuth2UserService(
    private val userRepository: UserRepository
) : DefaultOAuth2UserService() {

    @Throws(OAuth2AuthenticationException::class)
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
        if (StringUtils.isEmpty(oAuth2UserInfo.email)) {
            throw OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider")
        }
        var user: User? = oAuth2UserInfo.email?.let { userRepository.findByEmail(it) }

        if (user != null) {
            if (!user.provider?.equals(AuthProvider.valueOf(oAuth2UserRequest.clientRegistration.registrationId))!!) {
                throw OAuth2AuthenticationProcessingException(
                    "Looks like you're signed up with " +
                            user.provider.toString() + " account. Please use your " + user.provider
                        .toString() +
                            " account to login."
                )
            }
            user = updateExistingUser(user, oAuth2UserInfo)
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo)
        }
        return UserPrincipal.create(user, oAuth2User.attributes)
    }

    private fun registerNewUser(oAuth2UserRequest: OAuth2UserRequest, oAuth2UserInfo: OAuth2UserInfo): User {
        val user = User().apply {
            this.provider = AuthProvider.valueOf(oAuth2UserRequest.clientRegistration.registrationId)
            this.providerId = oAuth2UserInfo.id
            this.name = oAuth2UserInfo.name
            this.email = oAuth2UserInfo.email
            this.imageUrl = oAuth2UserInfo.imageUrl
        }

        return userRepository.save(user)
    }

    private fun updateExistingUser(existingUser: User, oAuth2UserInfo: OAuth2UserInfo): User {
        existingUser.name = oAuth2UserInfo.name
        existingUser.imageUrl = oAuth2UserInfo.imageUrl

        return userRepository.save(existingUser)
    }
}