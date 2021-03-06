package com.norberttalpos.auth.core.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.norberttalpos.common.abstracts.entity.AbstractAutoGeneratedIdEntity
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull

@Entity
@Table(name = "users")
class User : AbstractAutoGeneratedIdEntity() {

    @Email
    @Column(nullable = false, unique = true)
    var email: String? = null

    @Column(nullable = false)
    val emailVerified = false

    @JsonIgnore
    var password: String? = null

    @NotNull
    @Enumerated(EnumType.STRING)
    var provider: AuthProvider? = null

    var providerId: String? = null

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_role_join",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: List<Role>? = emptyList()
}

enum class AuthProvider {
    local, github, google
}