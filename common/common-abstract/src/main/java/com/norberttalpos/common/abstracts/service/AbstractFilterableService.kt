package com.norberttalpos.common.abstracts.service

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import com.norberttalpos.common.abstracts.filter.WhereMode
import com.norberttalpos.common.abstracts.repository.AbstractRepository
import org.springframework.stereotype.Service

@Service
abstract class AbstractFilterableService<
        ENTITY : AbstractEntity,
        FILTER : AbstractFilter,
        REPOSITORY : AbstractRepository<ENTITY>
        >
    : AbstractGettableService<ENTITY, REPOSITORY>() {

    abstract fun filter(filter: FILTER, whereMode: WhereMode = WhereMode.AND): List<ENTITY>
}