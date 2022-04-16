package com.norberttalpos.common.abstracts.entity

import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
interface AbstractEntity {
    var id: UUID?
}