package com.norberttalpos.common.abstracts.filter

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Predicate
import com.querydsl.core.types.dsl.StringPath

class QueryBuilder(
    private val whereMode: WhereMode,
    val builder: BooleanBuilder = BooleanBuilder(),
) {

    fun add(predicate: Predicate) =
        if(this.whereMode == WhereMode.AND)
            this.and(predicate)
        else
            this.or(predicate)

    fun and(predicate: Predicate): BooleanBuilder = this.builder.and(predicate)

    fun or(predicate: Predicate): BooleanBuilder = this.builder.or(predicate)

    fun addUniqueStringPred(path: StringPath, str: String?): BooleanBuilder =
        if(whereMode == WhereMode.AND)
            builder.and(path.containsIgnoreCase(str))
        else
            builder.or(path.eq(str))
}

enum class WhereMode {
    AND, OR
}