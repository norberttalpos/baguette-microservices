package com.norberttalpos.common.abstracts.dto

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import org.mapstruct.InheritConfiguration
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import java.util.*
import javax.persistence.EntityNotFoundException
import kotlin.reflect.KMutableProperty0

abstract class AbstractDtoMapper<ENTITY : AbstractEntity, DTO : AbstractDto> {

    abstract fun toDto(entity: ENTITY): DTO

    abstract fun fromDto(dto: DTO): ENTITY

    @InheritConfiguration
    @Mapping(target = "id", ignore = true)
    abstract fun updateFromDto(dto: DTO, @MappingTarget entity: ENTITY): ENTITY

    inline fun <reified T : AbstractEntity, S : AbstractDto> referenceResolver(
        dtoField: S?,
        entityField: KMutableProperty0<T>,
        dtoFieldGetter: (UUID) -> T?,
    ) {
        val id = dtoField?.id

        id.ifNotNullOrElse({
                entityField::set.invoke(dtoFieldGetter.invoke(it)
                    ?: throw EntityNotFoundException("field ${T::class.simpleName} with id $it not found"))
            }, {
                entityField::set.invoke(entityField.get())
            }
        )
    }
}

inline fun <T : Any, R> T?.ifNotNullOrElse(ifNotNullPath: (T) -> R, elsePath: () -> R)
        = let { if(it == null) elsePath() else ifNotNullPath(it) }
