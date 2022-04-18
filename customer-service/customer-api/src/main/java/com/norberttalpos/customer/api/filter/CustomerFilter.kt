package com.norberttalpos.customer.api.filter

import com.norberttalpos.common.abstracts.filter.AbstractFilter
import java.util.*

data class CustomerFilter(
    override val id: Long? = null,
    val name: String? = null,
    val email: String? = null,
) : AbstractFilter