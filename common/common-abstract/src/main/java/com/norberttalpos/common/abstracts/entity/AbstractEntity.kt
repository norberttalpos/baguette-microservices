package com.norberttalpos.common.abstracts.entity

import java.util.*
import javax.persistence.MappedSuperclass

@MappedSuperclass
interface AbstractEntity {
    var id: UUID?
}