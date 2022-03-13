package com.norberttalpos.common.abstracts.service

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import com.norberttalpos.common.abstracts.repository.AbstractRepository

abstract class AbstractDeletableService<ENTITY : AbstractEntity, FILTER : AbstractFilter>
    : AbstractModifiableService<ENTITY, FILTER>() {

    fun deleteById(id: Long) = this.repository.deleteById(id)
}