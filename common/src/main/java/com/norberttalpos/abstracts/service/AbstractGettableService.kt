package com.norberttalpos.abstracts.service

import com.norberttalpos.abstracts.entity.AbstractEntity
import com.norberttalpos.abstracts.repository.AbstractRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
abstract class AbstractGettableService<ENTITY : AbstractEntity, FILTER, REPOSITORY : AbstractRepository<ENTITY>>(
    protected val repository: REPOSITORY
) {

    fun getEntities(): Collection<ENTITY> = this.repository.findAll()

    fun getById(id: Long): ENTITY? = this.repository.findByIdOrNull(id)

    abstract fun filter(filter: FILTER): Collection<ENTITY>
}