package com.norberttalpos.product.core.repository

import com.norberttalpos.common.abstracts.repository.AbstractRepository
import com.norberttalpos.product.core.entity.ProductBrand
import org.springframework.stereotype.Repository

@Repository
interface ProductBrandRepository : AbstractRepository<ProductBrand>