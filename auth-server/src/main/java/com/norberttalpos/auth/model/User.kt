package com.norberttalpos.auth.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull


@Entity
@Table(name = "users")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @Column(nullable = false)
    var name: String? = null

    @Email
    @Column(nullable = false, unique = true)
    var email: String? = null

    var imageUrl: String? = null

    @Column(nullable = false)
    val emailVerified = false

    @JsonIgnore
    var password: String? = null

    @NotNull
    @Enumerated(EnumType.STRING)
    var provider: AuthProvider? = null

    var providerId: String? = null
}

enum class AuthProvider {
    LOCAL, GITHUB, GOOGLE
}