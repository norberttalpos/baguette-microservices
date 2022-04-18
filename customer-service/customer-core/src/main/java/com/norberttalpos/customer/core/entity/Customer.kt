package com.norberttalpos.customer.core.entity

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import java.util.*
import javax.persistence.*

@Entity(name = "Customer")
@Table(name = "customer")
class Customer : AbstractEntity {

    @Id
    @Column(name = "id", nullable = false)
    override var id: Long? = null

    @Column(name = "name", nullable = false)
    var name: String = ""

    @Column(name = "email", nullable = false, unique = true)
    var email: String = ""

    @Column(name = "phone_number")
    var phoneNumber: String? = ""

    @Column(name = "image_url")
    var imageUrl: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    var address: Address? = null
}