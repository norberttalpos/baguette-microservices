package com.norberttalpos.product.api.controller

import com.norberttalpos.common.abstracts.controller.interfaces.AbstractDeletableController
import com.norberttalpos.product.api.dto.ProductBrandDto
import com.norberttalpos.product.api.filter.ProductBrandFilter

interface ProductBrandController : AbstractDeletableController<ProductBrandDto, ProductBrandFilter>