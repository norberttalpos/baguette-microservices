package com.norberttalpos.common.abstracts.service

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import com.norberttalpos.common.abstracts.repository.AbstractRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
abstract class AbstractGettableService<ENTITY : AbstractEntity>() {

    @Autowired
    lateinit var repository: AbstractRepository<ENTITY>

    fun getEntities(): List<ENTITY> = this.repository.findAll()

    fun getById(id: UUID): ENTITY? = this.repository.findByIdOrNull(id)
}