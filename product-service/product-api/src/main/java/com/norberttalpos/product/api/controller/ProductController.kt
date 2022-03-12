package com.norberttalpos.product.api.controller

import com.norberttalpos.abstracts.controller.interfaces.AbstractDeletableController
import com.norberttalpos.product.api.dto.ProductDto
import com.norberttalpos.product.api.filter.ProductFilter

interface ProductController : AbstractDeletableController<ProductDto, ProductFilter>