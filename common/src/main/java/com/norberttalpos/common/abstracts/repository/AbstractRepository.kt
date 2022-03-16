package com.norberttalpos.common.abstracts.repository

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AbstractRepository<ENTITY : AbstractEntity>
    : JpaRepository<ENTITY, UUID>, QuerydslPredicateExecutor<ENTITY>