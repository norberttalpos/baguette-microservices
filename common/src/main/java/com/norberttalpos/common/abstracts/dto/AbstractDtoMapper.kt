package com.norberttalpos.common.abstracts.dto

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import com.norberttalpos.common.abstracts.service.AbstractGettableService
import org.mapstruct.*
import org.springframework.beans.factory.annotation.Autowired
import javax.persistence.EntityNotFoundException
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty0

abstract class AbstractDtoMapper<ENTITY : AbstractEntity, DTO : AbstractDto> {

    abstract fun toDto(entity: ENTITY): DTO

    abstract fun fromDto(dto: DTO): ENTITY

    @InheritConfiguration(name = "fromDto")
    abstract fun updateFromDto(dto: DTO, @MappingTarget entity: ENTITY)

    inline fun <reified T : AbstractEntity, S : AbstractDto> referenceResolver(
        dtoField: S?,
        entityField: KMutableProperty0<T>,
        dtoFieldGetter: (Long) -> T?,
    ) {
        val id = dtoField?.id

        if(id != null) {
            entityField::set.invoke(dtoFieldGetter.invoke(id)
                ?: throw EntityNotFoundException("field ${T::class.simpleName} with id $id not found"))
        } else {
            entityField::set.invoke(entityField.get())
        }
    }
}