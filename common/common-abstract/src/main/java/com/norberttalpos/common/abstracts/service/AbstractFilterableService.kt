package com.norberttalpos.common.abstracts.service

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import com.norberttalpos.common.abstracts.filter.WhereMode
import org.springframework.stereotype.Service

@Service
abstract class AbstractFilterableService<ENTITY : AbstractEntity, FILTER : AbstractFilter>()
    : AbstractGettableService<ENTITY>() {

    abstract fun filter(filter: FILTER, whereMode: WhereMode = WhereMode.AND): List<ENTITY>
}