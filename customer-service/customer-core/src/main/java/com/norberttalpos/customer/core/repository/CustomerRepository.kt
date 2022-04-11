package com.norberttalpos.customer.core.repository

import com.norberttalpos.common.abstracts.repository.AbstractRepository
import com.norberttalpos.customer.core.entity.Customer
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : AbstractRepository<Customer>