package com.norberttalpos.common.abstracts.service

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import com.norberttalpos.common.abstracts.repository.AbstractRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
abstract class AbstractGettableService<
        ENTITY : AbstractEntity,
        REPOSITORY : AbstractRepository<ENTITY>
        > {

    protected val logger = KotlinLogging.logger {}

    @Autowired
    protected lateinit var repository: REPOSITORY

    fun getEntities(): List<ENTITY> = this.repository.findAll()

    fun getById(id: UUID): ENTITY? = this.repository.findByIdOrNull(id)
}