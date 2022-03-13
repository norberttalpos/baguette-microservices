package com.norberttalpos.common.abstracts.entity

import javax.persistence.*

@MappedSuperclass
abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}