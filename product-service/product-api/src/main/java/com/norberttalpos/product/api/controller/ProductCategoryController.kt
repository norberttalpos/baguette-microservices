package com.norberttalpos.product.api.controller

import com.norberttalpos.common.abstracts.controller.interfaces.AbstractDeletableController
import com.norberttalpos.product.api.dto.ProductCategoryDto
import com.norberttalpos.product.api.filter.ProductCategoryFilter

interface ProductCategoryController : AbstractDeletableController<ProductCategoryDto, ProductCategoryFilter>