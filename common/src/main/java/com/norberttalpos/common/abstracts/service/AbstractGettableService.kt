package com.norberttalpos.common.abstracts.service

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import com.norberttalpos.common.abstracts.repository.AbstractRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
abstract class AbstractGettableService<ENTITY : AbstractEntity>() {

    @Autowired
    lateinit var repository: AbstractRepository<ENTITY>

    fun getEntities(): Collection<ENTITY> = this.repository.findAll()

    fun getById(id: Long): ENTITY? = this.repository.findByIdOrNull(id)
}