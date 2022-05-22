package com.norberttalpos.common.abstracts.service

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import com.norberttalpos.common.abstracts.repository.AbstractRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
abstract class AbstractGettableService<
        ENTITY : AbstractEntity,
        REPOSITORY : AbstractRepository<ENTITY>
        > {

    protected val logger = KotlinLogging.logger {}

    @Autowired
    protected lateinit var repository: REPOSITORY

    @Transactional(readOnly = true)
    fun getEntities(): List<ENTITY> = this.repository.findAll()

    @Transactional(readOnly = true)
    fun getById(id: Long): ENTITY? = this.repository.findById(id).unwrap()
}

fun <T> Optional<T>.unwrap(): T? = orElse(null)