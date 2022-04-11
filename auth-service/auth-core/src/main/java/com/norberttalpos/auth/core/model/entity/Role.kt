package com.norberttalpos.auth.core.model.entity

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import javax.persistence.*


@Entity(name = "Role")
@Table(name = "role")
class Role : AbstractEntity() {

    @Column(nullable = false, unique = true)
    var name: String? = null
}