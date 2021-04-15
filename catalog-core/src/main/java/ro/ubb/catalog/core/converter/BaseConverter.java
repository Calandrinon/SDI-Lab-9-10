package ro.ubb.catalog.core.converter;

import ro.ubb.catalog.core.model.BaseEntity;
import ro.ubb.catalog.core.dto.BaseDto;
import ro.ubb.catalog.core.dto.BaseEntityDto;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseConverter<Model extends BaseEntity<Integer>, Dto extends BaseEntityDto>
        implements Converter<Model, Dto> {


    public Set<Integer> convertModelsToIDs(Set<Model> models) {
        return models.stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toSet());
    }

    public Set<Integer> convertDTOsToIDs(Set<Dto> dtos) {
        return dtos.stream()
                .map(BaseEntityDto::getId)
                .collect(Collectors.toSet());
    }

    public Set<Dto> convertModelsToDtos(Collection<Model> models) {
        return models.stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toSet());
    }
}
