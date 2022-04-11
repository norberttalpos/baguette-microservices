package com.norberttalpos.customer.core.service

import com.norberttalpos.common.abstracts.filter.QueryBuilder
import com.norberttalpos.common.abstracts.filter.WhereMode
import com.norberttalpos.common.abstracts.service.AbstractDeletableService
import com.norberttalpos.customer.api.filter.CustomerFilter
import com.norberttalpos.customer.core.entity.Address
import com.norberttalpos.customer.core.entity.Customer
import com.norberttalpos.customer.core.entity.QCustomer
import org.springframework.stereotype.Service

@Service
class CustomerService : AbstractDeletableService<Customer, CustomerFilter>() {

    override fun filter(filter: CustomerFilter, whereMode: WhereMode): List<Customer> {
        val customer: QCustomer = QCustomer.customer
        val where = QueryBuilder(whereMode)

        filter.id?.let {
            where.add(customer.id.eq(filter.id))
        }
        filter.email?.let {
            where.addUniqueStringPred(customer.email, filter.email)
        }
        filter.name?.let {
            where.add(customer.name.containsIgnoreCase(filter.name))
        }

        return this.repository.findAll(where.getBuilder()).toList()
    }

    override fun provideUniquenessCheckFilter(entity: Customer) = CustomerFilter(email = entity.email)

    override fun validateEntity(entity: Customer) = true

    fun addAddressInfo(address: Address) {

    }
}