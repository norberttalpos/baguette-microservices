package com.norberttalpos.customer.core.repository

import com.norberttalpos.common.abstracts.repository.AbstractRepository
import com.norberttalpos.customer.core.entity.Address
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository : AbstractRepository<Address> {

    fun existsByStreet(street: String): Boolean
}