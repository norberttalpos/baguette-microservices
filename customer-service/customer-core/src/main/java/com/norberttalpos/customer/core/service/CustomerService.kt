package com.norberttalpos.customer.core.service

import com.norberttalpos.auth.api.client.AuthClient
import com.norberttalpos.auth.api.dto.UserDto
import com.norberttalpos.cart.api.client.CartClient
import com.norberttalpos.cart.api.controller.payload.CreateCartRequest
import com.norberttalpos.common.abstracts.filter.QueryBuilder
import com.norberttalpos.common.abstracts.filter.WhereMode
import com.norberttalpos.common.abstracts.service.AbstractDeletableService
import com.norberttalpos.common.abstracts.service.jwtRequiredMethod
import com.norberttalpos.common.exception.NotValidUpdateException
import com.norberttalpos.customer.api.filter.CustomerFilter
import com.norberttalpos.customer.core.entity.Address
import com.norberttalpos.customer.core.entity.Customer
import com.norberttalpos.customer.core.entity.QCustomer
import com.norberttalpos.customer.core.repository.AddressRepository
import com.norberttalpos.customer.core.repository.CustomerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.validation.constraints.Email

@Service
class CustomerService(
    private val authClient: AuthClient,
    private val cartClient: CartClient,
    private val addressRepository: AddressRepository,
) : AbstractDeletableService<Customer, CustomerFilter, CustomerRepository>() {

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

        return this.repository.findAll(where.builder).toList()
    }

    override fun provideUniquenessCheckFilter(entity: Customer) = CustomerFilter(email = entity.email)

    override fun validateEntity(entity: Customer): Boolean {
        return this.authClient.userExists(entity.email)
    }

    override fun postCreation(entity: Customer) {
        jwtRequiredMethod { jwtToken: String ->
            this.cartClient.createCart(
                CreateCartRequest(entity.id!!),
                jwtToken
            )
        }
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = [Exception::class])
    fun addAddressInfo(address: Address, currentUser: UserDto) {
        val currentCustomer = this.repository.getByEmail(currentUser.email!!)

        if(currentCustomer != null) {
            if(!this.addressRepository.existsByStreet(address.street)) {
                this.addressRepository.save(address)
            }

            this.put(currentCustomer.apply {
                this.address = address
            })

            logger.info { "Added address info for customer ${currentUser.id}" }

        } else {
            logger.info { "Customer not found with id ${currentUser.id}" }
            throw NotValidUpdateException("Customer not found with id ${currentUser.id}")
        }
    }

    @Transactional(readOnly = true)
    fun getByEmail(email: @Email String): Customer? {
        return this.repository.getByEmail(email)
    }

    @Transactional(readOnly = true)
    fun userExistsById(id: Long): Boolean {
        return this.repository.existsById(id)
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = [Exception::class])
    override fun deleteById(id: Long) {
        super.deleteById(id)

        jwtRequiredMethod { jwtToken: String ->
            this.cartClient.deleteCustomerCarts(jwtToken)
        }
    }
}