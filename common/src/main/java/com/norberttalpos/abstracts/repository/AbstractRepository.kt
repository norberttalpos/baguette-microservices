package com.norberttalpos.abstracts.repository

import com.norberttalpos.abstracts.entity.AbstractEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AbstractRepository<ENTITY : AbstractEntity> : JpaRepository<ENTITY, Long>