package com.norberttalpos.common.abstracts.service

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import java.util.*

abstract class AbstractDeletableService<ENTITY : AbstractEntity, FILTER : AbstractFilter>
    : AbstractModifiableService<ENTITY, FILTER>() {

    fun deleteById(id: UUID) = this.repository.deleteById(id)
}