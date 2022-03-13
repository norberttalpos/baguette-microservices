package com.norberttalpos.common.abstracts.service

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import com.norberttalpos.common.abstracts.repository.AbstractRepository

abstract class AbstractDeletableService<ENTITY : AbstractEntity, FILTER : AbstractFilter, REPOSITORY : AbstractRepository<ENTITY>>
    : AbstractModifiableService<ENTITY, FILTER, REPOSITORY>() {

    fun deleteById(id: Long) = this.repository.deleteById(id)
}