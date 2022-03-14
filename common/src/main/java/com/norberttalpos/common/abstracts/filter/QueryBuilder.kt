package com.norberttalpos.common

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Predicate
import com.querydsl.core.types.dsl.StringPath

class QueryBuilder(
    private val whereMode: WhereMode,
    private val builder: BooleanBuilder = BooleanBuilder(),
) {

    fun add(predicate: Predicate) {
        if(this.whereMode == WhereMode.AND)
            this.builder.and(predicate)
        else
            this.builder.or(predicate)
    }

    fun and(predicate: Predicate) {
        this.builder.and(predicate)
    }

    fun or(predicate: Predicate) {
        this.builder.or(predicate)
    }

    fun addUniqueStringPred(path: StringPath, str: String?) {
        if(whereMode == WhereMode.AND) builder.and(path.containsIgnoreCase(str))
        else builder.or(path.eq(str))
    }

    fun getBooleanBuilder(): BooleanBuilder {
        return builder
    }
}

enum class WhereMode {
    AND, OR
}